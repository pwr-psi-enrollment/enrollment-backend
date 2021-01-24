package pl.pwr.enrollment.registration;

import org.springframework.web.bind.annotation.*;
import pl.pwr.enrollment.registration.dto.RegistrationCreationDto;
import pl.pwr.enrollment.registration.dto.RegistrationDetailsDto;
import pl.pwr.enrollment.studentregistration.StudentRegistrationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/registrations")
class RegistrationController {

	private final RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@GetMapping
	public List<RegistrationDetailsDto> getRegistrations(@RequestParam Long semesterId, @RequestParam Long programmeId) {
		// TODO: 24.01.2021 auth info
		Long studentId = 0L;
		// programmeId from another microservice based on studentId?
		return registrationService.findStudentRegistrations(studentId, semesterId, programmeId);
	}

	@PostMapping
	public RegistrationDetailsDto createRegistration(@RequestBody @Valid RegistrationCreationDto registrationCreationDto) {
		return registrationService.createRegistration(registrationCreationDto);
	}

	@DeleteMapping("/{registrationId}")
	public void deleteRegistration(@PathVariable Long registrationId) {
		registrationService.deleteRegistration(registrationId);
	}

}
