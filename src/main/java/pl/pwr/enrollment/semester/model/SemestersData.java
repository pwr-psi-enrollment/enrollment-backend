package pl.pwr.enrollment.semester.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SemestersData {

	private final Long registeredId;
	private final List<SemesterData> semesters;

	@JsonCreator
	public SemestersData(
			@JsonProperty("registeredId") Long registeredId,
			@JsonProperty("semesters") List<SemesterData> semesters) {
		this.registeredId = registeredId;
		this.semesters = semesters;
	}

	public Long getRegisteredId() {
		return registeredId;
	}

	public List<SemesterData> getSemesters() {
		return semesters;
	}

}
