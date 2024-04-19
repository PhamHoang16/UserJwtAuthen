package com.example.testjwt.service.implement;

import com.example.testjwt.model.Role;
import com.example.testjwt.model.RoleName;
import com.example.testjwt.repositories.IRoleRepository;
import com.example.testjwt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
