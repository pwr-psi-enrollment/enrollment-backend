package pl.pwr.enrollment.studentregistration;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StudentRegistrationService {

	private final StudentRegistrationRepository studentRegistrationRepository;

	public StudentRegistrationService(StudentRegistrationRepository studentRegistrationRepository) {
		this.studentRegistrationRepository = studentRegistrationRepository;
	}

	public List<StudentRegistration> findRegistrationsForSemester(Long registeredId, Long semesterId) {
		return studentRegistrationRepository.findRegistrationsForSemester(registeredId, semesterId);
	}

	public StudentRegistration findById(Long studentRegistrationId) {
		return studentRegistrationRepository.findById(studentRegistrationId)
				.orElseThrow(EntityNotFoundException::new);
	}

	public Integer countTakenSeats(Long lectureGroupId) {
		return studentRegistrationRepository.calculateTakenSeats(lectureGroupId);
	}
}
