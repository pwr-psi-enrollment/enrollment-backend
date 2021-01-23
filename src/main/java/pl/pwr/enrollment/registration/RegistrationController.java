package pl.pwr.enrollment.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.enrollment.registration.dto.RegistrationCreationDto;
import pl.pwr.enrollment.registration.dto.RegistrationDetailsDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/registrations")
class RegistrationController {

	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping
	public RegistrationDetailsDto createRegistration(@RequestBody @Valid RegistrationCreationDto registrationCreationDto) {
		return registrationService.createRegistration(registrationCreationDto);
	}

}
