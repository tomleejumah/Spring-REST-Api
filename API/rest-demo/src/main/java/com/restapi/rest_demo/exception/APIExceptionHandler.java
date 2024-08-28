package com.restapi.rest_demo.exception;

import com.restapi.rest_demo.exception.AuthException.AuthenticationException;
import com.restapi.rest_demo.exception.CloudVendorException.CloudVendorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class, CloudVendorNotFoundException.class})
    public ResponseEntity<APIException> handleCustomExceptions(RuntimeException exception, WebRequest webRequest) {
        HttpStatus status;

        if (exception instanceof AuthenticationException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof CloudVendorNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else status = HttpStatus.INTERNAL_SERVER_ERROR;

        APIException apiException = new APIException(
                LocalDateTime.now(),
                exception.getMessage(),
                exception.getCause(),
                status,
                webRequest.getDescription(false)
        ); return new ResponseEntity<>(apiException,status);
    }
}
/*public ResponseEntity<Object> handleCloudVendorNotFoundException(CloudVendorNotFoundException cloudVendorNotFoundException) {
      APIException exception = new APIException(
              cloudVendorNotFoundException.getMessage(),
              cloudVendorNotFoundException.getCause(),
              HttpStatus.NOT_FOUND
      );
      return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler (value = {AuthenticationException.class})
public ResponseEntity<Object> handleAuthErrorException(AuthenticationException authenticationException){
      APIException exception = new APIException(
              authenticationException.getMessage(),
              authenticationException.getCause(),
              HttpStatus.BAD_REQUEST
      );
      return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
  }
}
*/