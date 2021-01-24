package pl.pwr.enrollment.studentregistration;

import org.springframework.web.bind.annotation.*;
import pl.pwr.enrollment.studentregistration.dto.LectureGroupIdDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/student-registrations")
class StudentRegistrationController {

	private StudentRegistrationService studentRegistrationService;

	public StudentRegistrationController(StudentRegistrationService studentRegistrationService) {
		this.studentRegistrationService = studentRegistrationService;
	}

	@PostMapping("/{registrationId}/enroll")
	public void enrollToCourse(@PathVariable Long registrationId, @RequestBody @Valid LectureGroupIdDto lectureGroupIdDto) {
		studentRegistrationService.enroll(registrationId, lectureGroupIdDto);
	}
}
