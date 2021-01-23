package pl.pwr.enrollment.registration.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.pwr.enrollment.registration.Registration;
import pl.pwr.enrollment.registration.RegistrationDestination;
import pl.pwr.enrollment.registration.RegistrationKind;
import pl.pwr.enrollment.registration.RegistrationStatus;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class RegistrationCreationDto {

	@NotNull
	@Positive
	private final Long programmeId;

	@NotNull
	@Positive
	private final Long fieldOfStudyId;

	@NotNull
	@Positive
	private final Long semesterId;

	@NotBlank
	private final String name;

	@NotBlank
	private final RegistrationKind kind;

	@NotBlank
	private final RegistrationDestination destination;

	@NotBlank
	private final RegistrationStatus status;

	@NotNull
	@Future
	private final LocalDateTime startDate;

	@NotNull
	@Future
	private final LocalDateTime endDate;

	@NotNull
	@Positive
	private final Long adminId;

	@JsonCreator
	public RegistrationCreationDto(
			@JsonProperty("programmeId") Long programmeId,
			@JsonProperty("fieldOfStudyId") Long fieldOfStudyId,
			@JsonProperty("semesterId") Long semesterId,
			@JsonProperty("name") String name,
			@JsonProperty("kind") RegistrationKind kind,
			@JsonProperty("destination") RegistrationDestination destination,
			@JsonProperty("status") RegistrationStatus status,
			@JsonProperty("startDate") LocalDateTime startDate,
			@JsonProperty("endDate") LocalDateTime endDate,
			@JsonProperty("adminId") Long adminId) {
		this.programmeId = programmeId;
		this.fieldOfStudyId = fieldOfStudyId;
		this.semesterId = semesterId;
		this.name = name;
		this.kind = kind;
		this.destination = destination;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adminId = adminId;
	}

	public Long getProgrammeId() {
		return programmeId;
	}

	public Long getFieldOfStudyId() {
		return fieldOfStudyId;
	}

	public Long getSemesterId() {
		return semesterId;
	}

	public String getName() {
		return name;
	}

	public RegistrationKind getKind() {
		return kind;
	}

	public RegistrationDestination getDestination() {
		return destination;
	}

	public RegistrationStatus getStatus() {
		return status;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public Long getAdminId() {
		return adminId;
	}

	public Registration toEntity() {
		return new Registration(
				programmeId,
				fieldOfStudyId,
				semesterId,
				name,
				kind,
				destination,
				status,
				startDate,
				endDate,
				adminId
		);
	}
}
