package pl.pwr.enrollment.registration;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.registration.dto.RegistrationCreationDto;
import pl.pwr.enrollment.registration.dto.RegistrationDetailsDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RegistrationService {

	private final RegistrationRepository registrationRepository;

	public RegistrationService(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}

	public RegistrationDetailsDto createRegistration(RegistrationCreationDto registrationCreationDto) {
		Registration registration = registrationCreationDto.toEntity();
		registration = registrationRepository.save(registration);
		return RegistrationDetailsDto.from(registration);
	}

	public void deleteRegistration(Long registrationId) {
		registrationRepository.deleteById(registrationId);
	}

	public List<RegistrationDetailsDto> findStudentRegistrations(Long studentId, Long semesterId, Long programmeId) {
		return registrationRepository.findAllByProgrammeIdAndSemesterId(programmeId, semesterId).stream()
				.map(RegistrationDetailsDto::from)
				.collect(toList());
	}
}
