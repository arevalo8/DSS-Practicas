package es.uca.dss.UCAravana.apirest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CaravanNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(CaravanNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String caravanNotFoundHandler(CaravanNotFoundException ex) {
    return ex.getMessage();
  }
}