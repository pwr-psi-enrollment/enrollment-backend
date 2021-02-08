//package pl.pwr.enrollment.semester;
//
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import pl.pwr.enrollment.semester.model.CourseDto;
//import pl.pwr.enrollment.semester.model.SemesterData;
//import pl.pwr.enrollment.semester.model.SemestersData;
//import pl.pwr.enrollment.studentregistration.StudentRegistration;
//import pl.pwr.enrollment.studentregistration.StudentRegistrationService;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static java.util.stream.Collectors.toList;
//
//@Service
//public class SemesterService {
//
//	private final RestTemplate restTemplate;
//	private final StudentRegistrationService studentRegistrationService;
//
//	public SemesterService(RestTemplate restTemplate, StudentRegistrationService studentRegistrationService) {
//		this.restTemplate = restTemplate;
//		this.studentRegistrationService = studentRegistrationService;
//	}
//
//	public List<SemesterDetailsDto> getSemesterDetails(Long studentId, Long registeredId) {
//		Map<String, String> params = new HashMap<>();
//		params.put("registeredId", registeredId.toString());
//		ResponseEntity<SemestersData> response =
//				restTemplate.exchange("http://mock-service/semesters", HttpMethod.GET, null, new ParameterizedTypeReference<>() {}, params);
//
//		SemestersData semestersData = response.getBody();
//		if (semestersData == null) {
//			throw new IllegalStateException("Null semesters data");
//		}
//
//		return createSemestersDto(registeredId, semestersData);
//	}
//
//	private List<SemesterDetailsDto> createSemestersDto(Long registeredId, SemestersData semestersData) {
//		return semestersData.getSemesters().stream()
//				.map(semester ->
//						new SemesterDetailsDto(
//								semester.getId(),
//								semester.getAcademicYear(),
//								semester.getSemesterType(),
//								semester.getYear(),
//								semester.getSemesterNumber(),
//								calculateEcts(registeredId, semester),
//								0 // TODO: ZZU
//						)
//				)
//				.collect(toList());
//	}
//
//	private Integer calculateEcts(Long registeredId, SemesterData semester) {
//		List<StudentRegistration> registrations = studentRegistrationService.findRegistrationsForSemester(registeredId, semester.getId());
//		return registrations.stream()
//				.flatMap(reg -> reg.getLectureGroupIds().stream())
//				.mapToInt(lectureGroupId ->
//						semester.getCourses().stream()
//								.filter(course -> course.getGroups().stream().anyMatch(g -> g.getId().equals(lectureGroupId)))
//								.mapToInt(CourseDto::getEcts)
//								.findAny()
//								.orElse(0)
//				)
//				.sum();
//	}
//}
