package pl.pwr.enrollment.web;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.data.ExternalDataService;
import pl.pwr.enrollment.data.model.*;
import pl.pwr.enrollment.studentregistration.StudentRegistration;
import pl.pwr.enrollment.studentregistration.StudentRegistrationService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.function.ToIntFunction;

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

		return mapToStudentDetailsDto(studentDetailsData);
	}

	@Transactional
	public CoursesData findCoursesForRegistration(Long studentRegistrationId) {
		StudentRegistration studentRegistration = studentRegistrationService.findById(studentRegistrationId);
		Long registrationId = studentRegistration.getRegistration().getId();

		CoursesData coursesData = externalDataService.queryCourses(registrationId);
		Set<Long> lectureGroupIds = studentRegistration.getLectureGroupIds();

		coursesData.getCourses().forEach(course -> markEnrolledCoursesAndGroups(lectureGroupIds, course));
		coursesData.getCourses().stream()
				.flatMap(course -> course.getGroups().stream())
				.forEach(this::calculateTakenSeats);

		return coursesData;
	}

	@Transactional
	public TakenSeatsResponse enrollToGroup(Long studentRegistrationId, EnrollmentDto enrollmentDto) {
		Long lectureGroup = enrollmentDto.getGroupId();
		StudentRegistration studentRegistration = studentRegistrationService.findById(studentRegistrationId);
		studentRegistration.enrollToGroup(lectureGroup);

		return new TakenSeatsResponse(
				studentRegistrationService.countTakenSeats(lectureGroup)
		);
	}

	public List<StudentRegistrationDto> getStudentRegistrations(Long registeredId, Long semesterId) {
		List<StudentRegistration> studentRegistrations = studentRegistrationService.findRegistrationsForSemester(registeredId, semesterId);
		return studentRegistrations.stream()
				.map(this::getStudentRegistrationDto)
				.collect(toList());
	}

	@Transactional
	public TakenSeatsResponse removeEnrollment(Long studentRegistrationId, Long lectureGroupId) {
		StudentRegistration studentRegistration = studentRegistrationService.findById(studentRegistrationId);
		studentRegistration.getLectureGroupIds().remove(lectureGroupId);

		return new TakenSeatsResponse(
				studentRegistrationService.countTakenSeats(lectureGroupId)
		);
	}

	private StudentDetailsDto mapToStudentDetailsDto(StudentDetailsData studentDetailsData) {
		return new StudentDetailsDto(
				studentDetailsData.getId(),
				studentDetailsData.getName(),
				studentDetailsData.getSurname(),
				studentDetailsData.getIndexNumber(),
				studentDetailsData.getFieldsOfStudy().stream()
						.map(this::getFieldOfStudyDto)
						.collect(toList())
		);
	}

	private FieldOfStudyDto getFieldOfStudyDto(FieldOfStudyData field) {
		List<SemesterDetailsDto> semesters = querySemesters(field.getRegisteredId());
		return new FieldOfStudyDto(
				field.getId(),
				field.getFaculty(),
				field.getName(),
				field.getStudyDegree(),
				field.getSpecialization(),
				field.getRegisteredId(),
				semesters.size() > 0 ? semesters.get(0).getYear() : null,
				semesters
		);
	}

	private List<SemesterDetailsDto> querySemesters(Long registeredId) {
		SemestersData semestersData = externalDataService.querySemesters(registeredId);
		return semestersData.getSemesters().stream()
				.map(semester -> mapToSemesterDetailsDto(registeredId, semester))
				.collect(toList());
	}

	private SemesterDetailsDto mapToSemesterDetailsDto(Long registeredId, SemesterData semester) {
		List<StudentRegistration> registrations = studentRegistrationService.findRegistrationsForSemester(registeredId, semester.getId());
		return new SemesterDetailsDto(
				semester.getId(),
				semester.getAcademicYear(),
				semester.getSemesterType(),
				semester.getYear(),
				semester.getSemesterNumber(),
				calculateCourseData(semester, registrations, CourseDto::getEcts),
				calculateCourseData(semester, registrations, CourseDto::getZzu)
		);
	}

	private Integer calculateCourseData(SemesterData semester, List<StudentRegistration> registrations, ToIntFunction<CourseDto> calculationFunction) {
		List<CourseDto> courses = registrations.stream()
				.map(reg -> externalDataService.queryCourses(reg.getId()))
				.flatMap(coursesData -> coursesData.getCourses().stream())
				.collect(toList());

		return registrations.stream()
				.flatMap(reg -> reg.getLectureGroupIds().stream())
				.mapToInt(lectureGroupId ->
						courses.stream()
								.filter(course -> course.getGroups().stream().anyMatch(g -> g.getId().equals(lectureGroupId)))
								.mapToInt(calculationFunction)
								.findAny()
								.orElse(0)
				)
				.sum();
	}

	private void markEnrolledCoursesAndGroups(Set<Long> lectureGroupIds, CourseDto course) {
		course.getGroups().forEach(group ->
				group.setEnrolled(
						lectureGroupIds.contains(group.getId())
				)
		);

		course.setEnrolled(
				course.getGroups().stream().anyMatch(GroupDto::getEnrolled)
		);
	}

	private StudentRegistrationDto getStudentRegistrationDto(StudentRegistration reg) {
		return new StudentRegistrationDto(
				reg.getId(),
				reg.getRegistration().getName(),
				reg.getRegistration().getDestination(),
				reg.getRegistration().getKind(),
				reg.getRegistration().getStatus(),
				reg.getRegistration().getStartTime(),
				reg.getRegistration().getEndTime(),
				reg.getStartTime()
		);
	}

	private void calculateTakenSeats(GroupDto group) {
		Integer takenSeats = studentRegistrationService.countTakenSeats(group.getId());
		group.setTakenSeats(takenSeats);
	}
}
