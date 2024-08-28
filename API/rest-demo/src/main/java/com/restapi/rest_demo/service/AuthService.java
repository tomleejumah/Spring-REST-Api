package com.restapi.rest_demo.service;

import com.restapi.rest_demo.dto.LoginDto;
import com.restapi.rest_demo.dto.RegisterDto;

public interface AuthService {
    public String register(RegisterDto registerDto);
    public String login(LoginDto loginDto);
}
