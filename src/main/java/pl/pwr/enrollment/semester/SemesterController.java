package pl.pwr.enrollment.semester;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.enrollment.security.resolver.CurrentUserId;

import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

	private final SemesterService semesterService;

	public SemesterController(SemesterService semesterService) {
		this.semesterService = semesterService;
	}

	@GetMapping
	public List<SemesterDetailsDto> getSemestersDetails(
			@CurrentUserId Long studentId,
			@RequestParam("registeredId") Long registeredId) {
		return semesterService.getSemesterDetails(studentId, registeredId);
	}

}
