package com.restapi.rest_demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class CloudVendorSecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                .requestMatchers("/cloudvendor/auth/**").permitAll()
//                                .requestMatchers(HttpMethod.POST, "/cloudvendor/auth/login").permitAll()  // Permit login
//                                .requestMatchers(HttpMethod.POST, "/cloudvendor/auth/register").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/cloudvendor/").hasAnyRole("USER", "ADMIN") // Get all
//                                .requestMatchers(HttpMethod.POST, "/cloudvendor/**").hasAnyRole("USER", "ADMIN") // Create
//                                .requestMatchers(HttpMethod.GET, "/cloudvendor/{id}").hasRole("ADMIN") // Get by ID
//                                .requestMatchers(HttpMethod.PUT, "/cloudvendor/**").hasRole("ADMIN") // Update
//                                .requestMatchers(HttpMethod.PATCH, "/cloudvendor/**").hasRole("ADMIN") // Partial Update
//                                .requestMatchers(HttpMethod.DELETE, "/cloudvendor/**").hasRole("ADMIN") // Delete
                                .anyRequest().authenticated())

                // Require authentication for other requests
//                .httpBasic(Customizer.withDefaults());

//        httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());

                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .successHandler((request, response, authentication) -> {
                            System.out.println("Login successful");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("Login failed: " + exception.getMessage());
                        })
                )
                .logout(logout -> logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            System.out.println("Logout successful");
                        })
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            System.out.println("Access denied: " + accessDeniedException.getMessage());
                        })
                        .authenticationEntryPoint((request, response, authException) -> {
                            System.out.println("Authentication failed: " + authException.getMessage());
                        })
                );

        return httpSecurity.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // React app origin
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH"));
//        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        configuration.setAllowCredentials(true); // If you're sending cookies or auth headers
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

 /*   @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        UserDetails userOne = userBuilder
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        UserDetails adminUser = userBuilder
                .username("admin")
                .password("admin")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(userOne, adminUser);
    }
*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 /*   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from your user repository
        // Convert user roles to authorities
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

  */
}
