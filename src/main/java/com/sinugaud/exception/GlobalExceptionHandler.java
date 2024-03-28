package com.sinugaud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ItemNotFoundException.class})
  public ResponseEntity<ErrorMessage> resourceNotFoundExceptionHandler(
      ItemNotFoundException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = {ValidationException.class})
  public ResponseEntity<ErrorMessage> resourceAlreadyExistExceptionHandler(
      ValidationException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(UserAuthenticationException.class)
  public ResponseEntity<ErrorMessage> unAuthenticatedUserExceptionHandler(
      UserAuthenticationException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.UNAUTHORIZED.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
  }

  /*
  @ExceptionHandler(AuthorizationServiceException.class)
  public ResponseEntity<ErrorMessage> unAuthorizedUserExceptionHandler(AuthorizationServiceException ex,
  		WebRequest request) {
  	ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), new Date(), ex.getMessage(),
  			request.getDescription(false));

  	return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
  }
  */
  @ExceptionHandler(ServerException.class)
  public ResponseEntity<ErrorMessage> serverExceptionHandler(
      ServerException ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message =
        new ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
