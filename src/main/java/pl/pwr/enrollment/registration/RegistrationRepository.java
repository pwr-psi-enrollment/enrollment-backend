package pl.pwr.enrollment.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RegistrationRepository extends JpaRepository<Registration, Long> {

	List<Registration> findAllByProgrammeIdAndSemesterId(Long programmeId, Long semesterId);

}
