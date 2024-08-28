package com.restapi.rest_demo.repository;

import com.restapi.rest_demo.model.VendorAuthDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorAuthRepository extends JpaRepository<VendorAuthDetails,Long> {

    Optional<VendorAuthDetails> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByusername(String username);
    Optional<VendorAuthDetails> findByUsernameOrEmail(String username, String email);
}
