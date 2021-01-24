package pl.pwr.enrollment.studentregistration;

import pl.pwr.enrollment.registration.Registration;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "StudentRegistrations")
public class StudentRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentRegistrationID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "registrationID", nullable = false)
	private Registration registration;

	@Column(name = "registrationStart", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "registrationEnd", nullable = false)
	private LocalDateTime endTime;

	@Column(name = "registeredID", nullable = false)
	private Long registeredId;

	@ElementCollection
	@CollectionTable(
			name = "RegisteredLectureGroups",
			joinColumns = @JoinColumn(name = "studentRegistrationID")
	)
	@Column(name = "lectureGroupID")
	private Set<Long> lectureGroupIds;

	@Deprecated
	protected StudentRegistration() {
	}

	public StudentRegistration(Registration registration, LocalDateTime startTime, LocalDateTime endTime, Long registeredId) {
		this.registration = registration;
		this.startTime = startTime;
		this.endTime = endTime;
		this.registeredId = registeredId;
	}

	public Long getId() {
		return id;
	}

	public Registration getRegistration() {
		return registration;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public Long getRegisteredId() {
		return registeredId;
	}

	public Set<Long> getLectureGroupIds() {
		return lectureGroupIds;
	}

	public void enroll(Long lectureGroupId) {
		lectureGroupIds.add(lectureGroupId);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		StudentRegistration that = (StudentRegistration) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
