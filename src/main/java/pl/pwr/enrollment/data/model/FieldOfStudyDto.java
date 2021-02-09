package pl.pwr.enrollment.data.model;

import java.util.List;

public class FieldOfStudyDto {

	private final Long id;
	private final FacultyData faculty;
	private final String name;
	private final String studyDegree;
	private final String specialization;
	private final Long registeredId;
	private final Integer startYear;
	private final List<SemesterDetailsDto> semesters;

	public FieldOfStudyDto(
			Long id,
			FacultyData faculty,
			String name,
			String studyDegree,
			String specialization,
			Long registeredId,
			Integer startYear,
			List<SemesterDetailsDto> semesters) {
		this.id = id;
		this.faculty = faculty;
		this.name = name;
		this.studyDegree = studyDegree;
		this.specialization = specialization;
		this.registeredId = registeredId;
		this.startYear = startYear;
		this.semesters = semesters;
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

	public List<SemesterDetailsDto> getSemesters() {
		return semesters;
	}

	public Integer getStartYear() {
		return startYear;
	}
}
