package pl.pwr.enrollment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EnrollmentDto {

	@NotNull
	@Positive
	private final Long groupId;

	@JsonCreator
	public EnrollmentDto(@JsonProperty("groupId") Long groupId) {
		this.groupId = groupId;
	}

	public Long getGroupId() {
		return groupId;
	}
}
