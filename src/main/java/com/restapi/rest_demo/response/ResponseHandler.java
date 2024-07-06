package com.restapi.rest_demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
public static ResponseEntity<Object> ResponseBuilder(
  String message,HttpStatus httpStatus,Object responseObject){
    
    Map<String,Object>responseMap = new HashMap<>();
    responseMap.put("message", message);
    responseMap.put("status", httpStatus);
    responseMap.put("data", responseObject);

    return new ResponseEntity<>(responseMap, httpStatus);
  }

}
