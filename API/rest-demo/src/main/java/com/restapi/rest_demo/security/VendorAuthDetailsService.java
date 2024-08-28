package com.restapi.rest_demo.security;

import com.restapi.rest_demo.model.VendorAuthDetails;
import com.restapi.rest_demo.repository.VendorAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendorAuthDetailsService implements UserDetailsService {

    private VendorAuthRepository vendorAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        VendorAuthDetails vendorAuthDetails = vendorAuthRepository.
                findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow( () -> new UsernameNotFoundException("User does not exist by Username or Email"));

        Set<GrantedAuthority> authorities = vendorAuthDetails.getRoles().stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());

        return new User(
                usernameOrEmail
                ,vendorAuthDetails.getPassword()
                ,authorities
        );
    }
}
