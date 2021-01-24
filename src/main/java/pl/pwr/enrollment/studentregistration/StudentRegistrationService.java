package pl.pwr.enrollment.studentregistration;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.studentregistration.dto.LectureGroupIdDto;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class StudentRegistrationService {

	private StudentRegistrationRepository studentRegistrationRepository;

	public StudentRegistrationService(StudentRegistrationRepository studentRegistrationRepository) {
		this.studentRegistrationRepository = studentRegistrationRepository;
	}

	// TODO: 24.01.2021 validation for multiple lecture groups
	@Transactional
	public void enroll(Long registrationId, LectureGroupIdDto lectureGroupIdDto) {
		StudentRegistration studentRegistration = studentRegistrationRepository.findById(registrationId)
				.orElseThrow(EntityNotFoundException::new);

		studentRegistration.enroll(lectureGroupIdDto.lectureGroupId);
	}
}
