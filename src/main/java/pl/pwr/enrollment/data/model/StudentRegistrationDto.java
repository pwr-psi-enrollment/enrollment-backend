package pl.pwr.enrollment.data.model;

import pl.pwr.enrollment.registration.RegistrationDestination;
import pl.pwr.enrollment.registration.RegistrationKind;
import pl.pwr.enrollment.registration.RegistrationStatus;

import java.time.LocalDateTime;

public class StudentRegistrationDto {

	private final Long id;
	private final String name;
	private final RegistrationDestination destination;
	private final RegistrationKind kind;
	private final RegistrationStatus status;
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	private final LocalDateTime studentStartTime;

	public StudentRegistrationDto(Long id, String name, RegistrationDestination destination, RegistrationKind kind, RegistrationStatus status, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime studentStartTime) {
		this.id = id;
		this.name = name;
		this.destination = destination;
		this.kind = kind;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.studentStartTime = studentStartTime;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public RegistrationDestination getDestination() {
		return destination;
	}

	public RegistrationKind getKind() {
		return kind;
	}

	public RegistrationStatus getStatus() {
		return status;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public LocalDateTime getStudentStartTime() {
		return studentStartTime;
	}
}
