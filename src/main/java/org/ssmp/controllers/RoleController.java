package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Role;
import org.ssmp.service.RoleService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/all")
    public List<Role> getAllRoles(){
        return roleService.getRoleList();
    }

}
