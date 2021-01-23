package pl.pwr.enrollment.registration;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Registrations")
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "registrationID")
	private Long id;

	@Column(name = "programmeID", nullable = false)
	private Long programmeId;

	@Column(name = "fieldOfStudy", nullable = false)
	private Long fieldOfStudyId;

	@Column(name = "semesterID", nullable = false)
	private Long semesterId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private RegistrationKind kind;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private RegistrationDestination destination;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private RegistrationStatus status;

	@Column(name = "startDate", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "endDate", nullable = false)
	private LocalDateTime endTime;

	@Column(name = "adminID", nullable = false)
	private Long adminId;

	@Deprecated
	protected Registration() {
	}

	public Registration(
			Long programmeId,
			Long fieldOfStudyId,
			Long semesterId,
			String name,
			RegistrationKind kind,
			RegistrationDestination destination,
			RegistrationStatus status,
			LocalDateTime startTime,
			LocalDateTime endTime,
			Long adminId) {
		this.programmeId = programmeId;
		this.fieldOfStudyId = fieldOfStudyId;
		this.semesterId = semesterId;
		this.name = name;
		this.kind = kind;
		this.destination = destination;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.adminId = adminId;
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Long getAdminId() {
		return adminId;
	}
}
