package pl.pwr.enrollment.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupDto {

	private final Long id;
	private final String code;
	private final String lecturer;
	private final Integer dayOfWeek;
	private final String start;
	private final String end;
	private final String room;
	private final Integer takenSeats;
	private final Integer allSeats;
	private Boolean enrolled;

	public GroupDto(
			@JsonProperty("id") Long id,
			@JsonProperty("code") String code,
			@JsonProperty("lecturer") String lecturer,
			@JsonProperty("dayOfWeek") Integer dayOfWeek,
			@JsonProperty("start") String start,
			@JsonProperty("end") String end,
			@JsonProperty("room") String room,
			@JsonProperty("takenSeats") Integer takenSeats,
			@JsonProperty("allSeats") Integer allSeats) {
		this.id = id;
		this.code = code;
		this.lecturer = lecturer;
		this.dayOfWeek = dayOfWeek;
		this.start = start;
		this.end = end;
		this.room = room;
		this.takenSeats = takenSeats;
		this.allSeats = allSeats;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getLecturer() {
		return lecturer;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getRoom() {
		return room;
	}

	public Integer getTakenSeats() {
		return takenSeats;
	}

	public Integer getAllSeats() {
		return allSeats;
	}

	public Boolean getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Boolean enrolled) {
		this.enrolled = enrolled;
	}
}
