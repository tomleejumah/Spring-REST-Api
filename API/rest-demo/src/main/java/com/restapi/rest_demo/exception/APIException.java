package com.restapi.rest_demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

//@Getter
public record APIException(LocalDateTime dateTime ,String message,
                           Throwable cause, HttpStatus httpStatus,String details) {

  @Override
  public String toString() {
    return "CloudVendorException{" +
            "time='" + dateTime + '\'' +
            ", message='" + message + '\'' +
            ", cause='" + (cause != null ? cause.toString() : "N/A") + '\'' +
            ", httpStatus=" + httpStatus +
            ", details='" + details + '\'' +
            '}';
  }
}
