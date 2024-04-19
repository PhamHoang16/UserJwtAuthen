package com.example.testjwt.service;

import com.example.testjwt.model.Role;
import com.example.testjwt.model.RoleName;
import com.example.testjwt.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
