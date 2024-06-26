package es.uca.dss.UCAravana.apirest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class BookingNotFoundAdvice {

	 @ResponseBody
	 @ExceptionHandler(BookingNotFoundException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 String bookingNotFoundHandler(BookingNotFoundException ex) {
	   return ex.getMessage();
	 }
}