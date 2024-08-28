package com.restapi.rest_demo.repository;

import com.restapi.rest_demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
//    Role findByUserName(String name);
}
