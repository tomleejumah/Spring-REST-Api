package com.restapi.rest_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CloudVendorSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorize->
                        authorize.requestMatchers(HttpMethod.GET, "/cloudvendor/**").hasRole("ADMIN")
                                .anyRequest().permitAll()).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//you can add more users
        UserDetails userOne = userBuilder
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails adminUser = userBuilder
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        return  new InMemoryUserDetailsManager(userOne,adminUser);
    }
}
