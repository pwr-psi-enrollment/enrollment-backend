package pl.pwr.enrollment.web.model;

import java.util.List;

public class StudentDetailsDto {

	private final Long id;
	private final String name;
	private final String surname;
	private final String indexNumber;
	private final List<FieldOfStudyDto> fieldsOfStudy;

	public StudentDetailsDto(Long id, String name, String surname, String indexNumber, List<FieldOfStudyDto> fieldsOfStudy) {
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

	public List<FieldOfStudyDto> getFieldsOfStudy() {
		return fieldsOfStudy;
	}
}
