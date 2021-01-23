package pl.pwr.enrollment.studentregistration;

import org.springframework.stereotype.Service;

@Service
public class StudentRegistrationService {

	private StudentRegistrationRepository studentRegistrationRepository;

	public StudentRegistrationService(StudentRegistrationRepository studentRegistrationRepository) {
		this.studentRegistrationRepository = studentRegistrationRepository;
	}
}
