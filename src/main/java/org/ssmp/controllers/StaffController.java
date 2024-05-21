package org.ssmp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.CreateUserDTO;
import org.ssmp.model.Staff;
import org.ssmp.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;


    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/all")
    public List<Staff> staffList(){
        return staffService.getStaffList();
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createdUser){
        if(staffService.loadUserByUsername(createdUser.getLogin())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        staffService.createNewUser(createdUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
