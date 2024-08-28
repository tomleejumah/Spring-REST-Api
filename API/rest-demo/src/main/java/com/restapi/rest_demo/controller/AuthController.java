package com.restapi.rest_demo.controller;


import com.restapi.rest_demo.dto.LoginDto;
import com.restapi.rest_demo.dto.RegisterDto;
import com.restapi.rest_demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/cloudvendor/auth")
public class AuthController {
    private AuthService authService;

    //build register endpoint
    @PostMapping("/register/")
    public ResponseEntity<String>register (@RequestBody RegisterDto registerDto){
        String response =  authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //build login endpoint
    @PostMapping("/login/")
    public ResponseEntity<String>login (@RequestBody LoginDto loginDto){
        String response =  authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
