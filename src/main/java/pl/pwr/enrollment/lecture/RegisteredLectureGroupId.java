package pl.pwr.enrollment.lecture;

import pl.pwr.enrollment.studentregistration.StudentRegistration;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RegisteredLectureGroupId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "studentRegistrationID", nullable = false)
	private StudentRegistration studentRegistration;

	@Column(name = "lectureGroupID", nullable = false)
	private Long lectureGroupId;

	@Deprecated
	protected RegisteredLectureGroupId() {
	}

	public RegisteredLectureGroupId(StudentRegistration studentRegistration, Long lectureGroupId) {
		this.studentRegistration = studentRegistration;
		this.lectureGroupId = lectureGroupId;
	}

	public StudentRegistration getStudentRegistration() {
		return studentRegistration;
	}

	public Long getLectureGroupId() {
		return lectureGroupId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RegisteredLectureGroupId that = (RegisteredLectureGroupId) o;
		return Objects.equals(studentRegistration, that.studentRegistration) && Objects.equals(lectureGroupId, that.lectureGroupId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentRegistration, lectureGroupId);
	}
}
