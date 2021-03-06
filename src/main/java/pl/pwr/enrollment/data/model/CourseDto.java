package pl.pwr.enrollment.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CourseDto {

	private final Long id;
	private final String name;
	private final String type;
	private final Integer ects;
	private final Integer zzu;
	private final List<GroupDto> groups;
	private Boolean enrolled = false;

	@JsonCreator
	public CourseDto(
			@JsonProperty("id") Long id,
			@JsonProperty("name") String name,
			@JsonProperty("type") String type,
			@JsonProperty("ects") Integer ects,
			@JsonProperty("zzu") Integer zzu,
			@JsonProperty("groups") List<GroupDto> groups) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.ects = ects;
		this.zzu = zzu;
		this.groups = groups;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Integer getEcts() {
		return ects;
	}

	public Integer getZzu() {
		return zzu;
	}

	public List<GroupDto> getGroups() {
		return groups;
	}

	public Boolean getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Boolean enrolled) {
		this.enrolled = enrolled;
	}
}
