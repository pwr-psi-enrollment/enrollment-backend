package pl.pwr.enrollment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FieldOfStudyData {

	private final Long id;
	private final FacultyData faculty;
	private final String name;
	private final String studyDegree;
	private final String specialization;
	private final Long registeredId;

	@JsonCreator
	public FieldOfStudyData(
			@JsonProperty("id") Long id,
			@JsonProperty("faculty") FacultyData faculty,
			@JsonProperty("name") String name,
			@JsonProperty("studyDegree") String studyDegree,
			@JsonProperty("specialization") String specialization,
			@JsonProperty("registeredId") Long registeredId) {
		this.id = id;
		this.faculty = faculty;
		this.name = name;
		this.studyDegree = studyDegree;
		this.specialization = specialization;
		this.registeredId = registeredId;
	}

	public Long getId() {
		return id;
	}

	public FacultyData getFaculty() {
		return faculty;
	}

	public String getName() {
		return name;
	}

	public String getStudyDegree() {
		return studyDegree;
	}

	public String getSpecialization() {
		return specialization;
	}

	public Long getRegisteredId() {
		return registeredId;
	}
}
