package pl.pwr.enrollment.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class WebControllerAdvice {

	@ExceptionHandler
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception) {
		return ResponseEntity.notFound().build();
	}
}
