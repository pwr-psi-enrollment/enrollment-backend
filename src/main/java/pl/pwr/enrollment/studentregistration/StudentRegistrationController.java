package pl.pwr.enrollment.studentregistration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-registrations")
class StudentRegistrationController {

	private StudentRegistrationService studentRegistrationService;

	public StudentRegistrationController(StudentRegistrationService studentRegistrationService) {
		this.studentRegistrationService = studentRegistrationService;
	}
}
