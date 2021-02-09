package pl.pwr.enrollment.web;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pl.pwr.enrollment.data.model.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WebController {

	private final WebService webService;

	public WebController(WebService webService) {
		this.webService = webService;
	}

	@GetMapping("/student-details")
	public StudentDetailsDto getStudentDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
		return webService.getStudentDetails(authorization);
	}

	@GetMapping("/student-registrations")
	public List<StudentRegistrationDto> getStudentRegistrations(
			@RequestParam("registeredId") Long registeredId,
			@RequestParam("semesterId") Long semesterId) {
		return webService.getStudentRegistrations(registeredId, semesterId);
	}

	@GetMapping("/student-registrations/{studentRegistrationId}/courses")
	public CoursesData getCoursesForRegistration(@PathVariable("studentRegistrationId") Long studentRegistrationId) {
		return webService.findCoursesForRegistration(studentRegistrationId);
	}

	@PostMapping("/student-registrations/{studentRegistrationId}/enroll")
	public TakenSeatsResponse enrollToGroup(
			@PathVariable("studentRegistrationId") Long studentRegistrationId,
			@RequestBody @Valid EnrollmentDto enrollmentDto) {
		return webService.enrollToGroup(studentRegistrationId, enrollmentDto);
	}

	@DeleteMapping("/student-registrations/{studentRegistrationId}/enrollment/{lectureGroupId}")
	public TakenSeatsResponse removeLectureEnrollment(
			@PathVariable("studentRegistrationId") Long studentRegistrationId,
			@PathVariable("lectureGroupId") Long lectureGroupId) {
		return webService.removeEnrollment(studentRegistrationId, lectureGroupId);
	}

}
