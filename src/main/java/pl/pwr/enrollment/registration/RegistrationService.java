package pl.pwr.enrollment.registration;

import org.springframework.stereotype.Service;
import pl.pwr.enrollment.registration.dto.RegistrationCreationDto;
import pl.pwr.enrollment.registration.dto.RegistrationDetailsDto;

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

}
