package pl.pwr.enrollment.studentregistration.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LectureGroupIdDto {

	public final Long lectureGroupId;

	@JsonCreator
	public LectureGroupIdDto(@JsonProperty("lectureGroupId") Long lectureGroupId) {
		this.lectureGroupId = lectureGroupId;
	}
}
