package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.ssmp.Exceptions.ApplicationError;
import org.ssmp.Utils.JwtTokenUtils;
import org.ssmp.dtos.JwtResponse;
import org.ssmp.dtos.LogInDTO;
import org.ssmp.service.StaffService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final StaffService staffService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody LogInDTO logInDTO){
        System.out.println(logInDTO);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInDTO.getLogin(), logInDTO.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = staffService.loadUserByUsername(logInDTO.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, jwtTokenUtils.getUserRoles(token).getFirst()));

    }

}
