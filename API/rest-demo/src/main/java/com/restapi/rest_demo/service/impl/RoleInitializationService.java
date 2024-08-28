package com.restapi.rest_demo.service.impl;

import com.restapi.rest_demo.model.Role;
import com.restapi.rest_demo.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleInitializationService {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        createRoleIfNotFound("USER");
        createRoleIfNotFound("ADMIN");
        // Add more roles if needed
    }

    private void createRoleIfNotFound(String roleName) {
        Optional<Role> role = Optional.ofNullable(roleRepository.findByName(roleName));
        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(roleName);
            roleRepository.save(newRole);
        }
    }
}
