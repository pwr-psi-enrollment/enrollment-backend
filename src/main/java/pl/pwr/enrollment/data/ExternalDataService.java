package pl.pwr.enrollment.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pwr.enrollment.data.model.CoursesData;
import pl.pwr.enrollment.data.model.StudentDetailsData;
import pl.pwr.enrollment.data.model.SemestersData;

@Service
public class ExternalDataService {

	private final RestTemplate restTemplate;
	private final String mockHost;

	public ExternalDataService(
			RestTemplate restTemplate,
			@Value("${mock.host:mock-service}") String mockHost) {
		this.restTemplate = restTemplate;
		this.mockHost = mockHost;
	}

	public StudentDetailsData queryStudentDetails(String authorization) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.AUTHORIZATION, authorization);
		HttpEntity<?> entity = new HttpEntity<>(null, httpHeaders);

		ResponseEntity<StudentDetailsData> studentDetailsData
				= restTemplate.exchange("http://" + mockHost + "/mock-service/student-details", HttpMethod.GET, entity, StudentDetailsData.class);

		return studentDetailsData.getBody();
	}

	public SemestersData querySemesters(Long registeredId) {
		ResponseEntity<SemestersData> response = restTemplate.exchange(
				"http://" + mockHost + "/mock-service/semesters?registeredId=" + registeredId,
				HttpMethod.GET,
				null,
				SemestersData.class
		);

		return response.getBody();
	}

	public CoursesData queryCourses(Long registrationId) {
		ResponseEntity<CoursesData> response = restTemplate.exchange(
				"http://" + mockHost + "/mock-service/courses?registrationId=" + registrationId,
				HttpMethod.GET,
				null,
				CoursesData.class
		);

		return response.getBody();
	}

}
