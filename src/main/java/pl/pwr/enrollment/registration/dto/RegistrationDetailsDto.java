package pl.pwr.enrollment.registration.dto;

import pl.pwr.enrollment.registration.Registration;
import pl.pwr.enrollment.registration.RegistrationDestination;
import pl.pwr.enrollment.registration.RegistrationKind;
import pl.pwr.enrollment.registration.RegistrationStatus;

import java.time.LocalDateTime;

public class RegistrationDetailsDto {

	private final Long id;
	private final Long programmeId;
	private final Long fieldOfStudyId;
	private final Long semesterId;
	private final String name;
	private final RegistrationKind kind;
	private final RegistrationDestination destination;
	private final RegistrationStatus status;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;

	public static RegistrationDetailsDto from(Registration registration) {
		return new RegistrationDetailsDto(
				registration.getId(),
				registration.getProgrammeId(),
				registration.getFieldOfStudyId(),
				registration.getSemesterId(),
				registration.getName(),
				registration.getKind(),
				registration.getDestination(),
				registration.getStatus(),
				registration.getStartTime(),
				registration.getEndTime()
		);
	}

	public RegistrationDetailsDto(
			Long id,
			Long programmeId,
			Long fieldOfStudyId,
			Long semesterId,
			String name,
			RegistrationKind kind,
			RegistrationDestination destination,
			RegistrationStatus status,
			LocalDateTime startDate,
			LocalDateTime endDate) {
		this.id = id;
		this.programmeId = programmeId;
		this.fieldOfStudyId = fieldOfStudyId;
		this.semesterId = semesterId;
		this.name = name;
		this.kind = kind;
		this.destination = destination;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
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
}
