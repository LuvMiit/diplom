package org.ssmp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private String Role;
}
