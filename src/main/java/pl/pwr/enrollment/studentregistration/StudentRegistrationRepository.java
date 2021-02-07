package pl.pwr.enrollment.studentregistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, Long> {

	@Query("SELECT sr FROM StudentRegistration sr WHERE sr.registeredId = :registeredId AND sr.registration.semesterId = :semesterId")
	List<StudentRegistration> findRegistrationsForSemester(@Param("registeredId") Long registeredId, @Param("semesterId") Long semesterId);

}
