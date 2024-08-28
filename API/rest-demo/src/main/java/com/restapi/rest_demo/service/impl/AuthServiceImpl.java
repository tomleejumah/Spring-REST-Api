package com.restapi.rest_demo.service.impl;

import com.restapi.rest_demo.dto.LoginDto;
import com.restapi.rest_demo.dto.RegisterDto;
import com.restapi.rest_demo.exception.AuthException.AuthenticationException;
import com.restapi.rest_demo.model.Role;
import com.restapi.rest_demo.model.VendorAuthDetails;
import com.restapi.rest_demo.repository.RoleRepository;
import com.restapi.rest_demo.repository.VendorAuthRepository;
import com.restapi.rest_demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private VendorAuthRepository vendorAuthRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    @Override
    public String register(RegisterDto registerDto) {
        boolean usernameExists = vendorAuthRepository.existsByusername(registerDto.getUsername());
        boolean emailExists = vendorAuthRepository.existsByEmail(registerDto.getEmail());

        if (usernameExists && emailExists) {
            throw new AuthenticationException("Username and Email already exist");
        } else if (usernameExists) {
            throw new AuthenticationException("Username already exists");
        } else if (emailExists) {
            throw new AuthenticationException("Email already exists");
        }

        VendorAuthDetails vendorAuthDetails = new VendorAuthDetails();
        vendorAuthDetails.setName(registerDto.getName());
        vendorAuthDetails.setUsername(registerDto.getUsername());
        vendorAuthDetails.setEmail(registerDto.getEmail());
        vendorAuthDetails.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        vendorAuthDetails.setRoles(roles);

        vendorAuthRepository.save(vendorAuthDetails);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginDto loginDto) {

        try {
            // Attempt authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsernameOrEmail(),
                            loginDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "User Logged in Successfully";

        } catch (AuthenticationException ex) {
            // Handle authentication failure (e.g., incorrect credentials)
            throw new AuthenticationException("Login failed: Invalid username or password");
//            return "Login failed: Invalid username or password";
        }
    }
}
