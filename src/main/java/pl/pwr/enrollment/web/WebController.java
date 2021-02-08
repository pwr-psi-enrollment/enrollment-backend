package pl.pwr.enrollment.web;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pl.pwr.enrollment.data.model.CoursesData;
import pl.pwr.enrollment.studentregistration.StudentRegistration;
import pl.pwr.enrollment.studentregistration.StudentRegistrationService;
import pl.pwr.enrollment.web.model.StudentDetailsDto;
import pl.pwr.enrollment.web.model.StudentRegistrationDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class WebController {

	private final WebService webService;
	private final StudentRegistrationService studentRegistrationService;

	public WebController(WebService webService, StudentRegistrationService studentRegistrationService) {
		this.webService = webService;
		this.studentRegistrationService = studentRegistrationService;
	}

	/**
	 * @return student details with fields of study and semesters
	 */
	@GetMapping("/student-details")
	public StudentDetailsDto getStudentDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return webService.getStudentDetails(authorization);
	}

	@GetMapping("/student-registrations")
	public List<StudentRegistrationDto> getStudentRegistrations(
			@RequestParam("registeredId") Long registeredId,
			@RequestParam("semesterId") Long semesterId) {

		List<StudentRegistration> studentRegistrations = studentRegistrationService.findRegistrationsForSemester(registeredId, semesterId);
		return studentRegistrations.stream()
				.map(reg -> new StudentRegistrationDto(
						reg.getId(),
						reg.getRegistration().getName(),
						reg.getRegistration().getDestination(),
						reg.getRegistration().getKind(),
						reg.getRegistration().getStatus(),
						reg.getRegistration().getStartTime(),
						reg.getRegistration().getEndTime(),
						reg.getStartTime()
				))
				.collect(toList());
	}

	@GetMapping("/student-registrations/{studentRegistrationId}/courses")
	public CoursesData getCoursesForRegistration(@PathVariable("studentRegistrationId") Long studentRegistrationId) {
		return webService.findCoursesForRegistration(studentRegistrationId);
	}

}
