package pl.pwr.enrollment.registration;

import org.springframework.data.jpa.repository.JpaRepository;

interface RegistrationRepository extends JpaRepository<Registration, Long> {


}
