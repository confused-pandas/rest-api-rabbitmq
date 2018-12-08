package eu.telecomnancy.championnat.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.telecomnancy.championnat.exception.CompetitionNotFoundException;

@ControllerAdvice
class CompetitionNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(CompetitionNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String competitionNotFoundHandler(CompetitionNotFoundException ex) {
		return ex.getMessage();
	}
}