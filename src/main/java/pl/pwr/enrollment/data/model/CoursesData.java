package pl.pwr.enrollment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CoursesData {

	private final Long registrationId;
	private final List<CourseDto> courses;

	@JsonCreator
	public CoursesData(
			@JsonProperty("registrationId") Long registrationId,
			@JsonProperty("courses") List<CourseDto> courses) {
		this.registrationId = registrationId;
		this.courses = courses;
	}

	public Long getRegistrationId() {
		return registrationId;
	}

	public List<CourseDto> getCourses() {
		return courses;
	}
}
