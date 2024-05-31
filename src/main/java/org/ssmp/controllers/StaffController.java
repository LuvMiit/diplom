package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.*;
import org.ssmp.model.Staff;
import org.ssmp.service.StaffService;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping("/all")
    public List<Staff> staffList(){
        return staffService.getStaffList();
    }

    @GetMapping("/bosses")
    public List<BossDTO> getBosses(){
        return staffService.getBosses();
    }

    @GetMapping("/drivers")
    public List<DriverDTO> getDrivers(){
        return staffService.getDrivers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterDTO registerDTO){


        if(staffService.registerNewUser(registerDTO)){
            return new ResponseEntity<>((HttpStatus.OK));
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
