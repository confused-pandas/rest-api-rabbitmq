package eu.telecomnancy.championnat.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.telecomnancy.championnat.exception.MatchNotFoundException;

@ControllerAdvice
class MatchNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(MatchNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String matchNotFoundHandler(MatchNotFoundException ex) {
		return ex.getMessage();
	}
}