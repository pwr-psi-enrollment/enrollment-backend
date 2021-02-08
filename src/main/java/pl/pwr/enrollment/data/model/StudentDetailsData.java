package pl.pwr.enrollment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StudentDetailsData {

	private final Long id;
	private final String name;
	private final String surname;
	private final String indexNumber;
	private final List<FieldOfStudyData> fieldsOfStudy;

	@JsonCreator
	public StudentDetailsData(
			@JsonProperty("id") Long id,
			@JsonProperty("name") String name,
			@JsonProperty("surname") String surname,
			@JsonProperty("indexNumber") String indexNumber,
			@JsonProperty("fieldsOfStudy") List<FieldOfStudyData> fieldsOfStudy) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.indexNumber = indexNumber;
		this.fieldsOfStudy = fieldsOfStudy;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public List<FieldOfStudyData> getFieldsOfStudy() {
		return fieldsOfStudy;
	}
}
