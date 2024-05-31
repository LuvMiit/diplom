package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.Role;
import org.ssmp.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getRoleList(){
        return roleRepository.findAll();
    }
}
