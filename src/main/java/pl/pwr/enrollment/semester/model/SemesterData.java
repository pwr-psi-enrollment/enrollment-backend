package pl.pwr.enrollment.semester.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SemesterData {

	private final Long id;
	private final String academicYear;
	private final String semesterType;
	private final Integer year;
	private final Integer semesterNumber;
	private final List<CourseDto> courses;

	@JsonCreator
	public SemesterData(
			@JsonProperty("id") Long id,
			@JsonProperty("academicYear") String academicYear,
			@JsonProperty("semesterType") String semesterType,
			@JsonProperty("year") Integer year,
			@JsonProperty("semesterNumber") Integer semesterNumber,
			@JsonProperty("courses") List<CourseDto> courses) {
		this.id = id;
		this.academicYear = academicYear;
		this.semesterType = semesterType;
		this.year = year;
		this.semesterNumber = semesterNumber;
		this.courses = courses;
	}

	public Long getId() {
		return id;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public String getSemesterType() {
		return semesterType;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getSemesterNumber() {
		return semesterNumber;
	}

	public List<CourseDto> getCourses() {
		return courses;
	}
}
