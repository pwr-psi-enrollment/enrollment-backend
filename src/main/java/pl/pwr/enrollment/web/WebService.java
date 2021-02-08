package pl.pwr.enrollment.web;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.data.ExternalDataService;
import pl.pwr.enrollment.data.model.CoursesData;
import pl.pwr.enrollment.data.model.StudentDetailsData;
import pl.pwr.enrollment.semester.SemesterDetailsDto;
import pl.pwr.enrollment.semester.model.CourseDto;
import pl.pwr.enrollment.semester.model.GroupDto;
import pl.pwr.enrollment.semester.model.SemesterData;
import pl.pwr.enrollment.semester.model.SemestersData;
import pl.pwr.enrollment.studentregistration.StudentRegistration;
import pl.pwr.enrollment.studentregistration.StudentRegistrationService;
import pl.pwr.enrollment.web.model.FieldOfStudyDto;
import pl.pwr.enrollment.web.model.StudentDetailsDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
public class WebService {

	private final ExternalDataService externalDataService;
	private final StudentRegistrationService studentRegistrationService;

	public WebService(ExternalDataService externalDataService, StudentRegistrationService studentRegistrationService) {
		this.externalDataService = externalDataService;
		this.studentRegistrationService = studentRegistrationService;
	}

	public StudentDetailsDto getStudentDetails(String authorization) {
		StudentDetailsData studentDetailsData = externalDataService.queryStudentDetails(authorization);

		return new StudentDetailsDto(
				studentDetailsData.getId(),
				studentDetailsData.getName(),
				studentDetailsData.getSurname(),
				studentDetailsData.getIndexNumber(),
				studentDetailsData.getFieldsOfStudy().stream()
						.map(field -> new FieldOfStudyDto(
								field.getId(),
								field.getFaculty(),
								field.getName(),
								field.getStudyDegree(),
								field.getSpecialization(),
								field.getRegisteredId(),
								querySemesters(field.getRegisteredId())
						))
						.collect(toList())
		);
	}

	@Transactional
	public CoursesData findCoursesForRegistration(Long studentRegistrationId) {
		StudentRegistration studentRegistration = studentRegistrationService.findById(studentRegistrationId);
		Long registrationId = studentRegistration.getRegistration().getId();

		CoursesData coursesData = externalDataService.queryCourses(registrationId);
		Set<Long> lectureGroupIds = studentRegistration.getLectureGroupIds();

		coursesData.getCourses().forEach(course -> {
					course.getGroups().forEach(group ->
							group.setEnrolled(
									lectureGroupIds.contains(group.getId())
							)
					);

					course.setEnrolled(
							course.getGroups().stream().anyMatch(GroupDto::getEnrolled)
					);
				}
		);

		return coursesData;
	}

	private List<SemesterDetailsDto> querySemesters(Long registeredId) {
		SemestersData semestersData = externalDataService.querySemesters(registeredId);
		return semestersData.getSemesters().stream()
				.map(semester ->
						new SemesterDetailsDto(
								semester.getId(),
								semester.getAcademicYear(),
								semester.getSemesterType(),
								semester.getYear(),
								semester.getSemesterNumber(),
								calculateEcts(registeredId, semester),
								0 // TODO: ZZU
						)
				)
				.collect(toList());
	}

	private Integer calculateEcts(Long registeredId, SemesterData semester) {
		List<StudentRegistration> registrations = studentRegistrationService.findRegistrationsForSemester(registeredId, semester.getId());
		return registrations.stream()
				.flatMap(reg -> reg.getLectureGroupIds().stream())
				.mapToInt(lectureGroupId ->
						semester.getCourses().stream()
								.filter(course -> course.getGroups().stream().anyMatch(g -> g.getId().equals(lectureGroupId)))
								.mapToInt(CourseDto::getEcts)
								.findAny()
								.orElse(0)
				)
				.sum();
	}
}
