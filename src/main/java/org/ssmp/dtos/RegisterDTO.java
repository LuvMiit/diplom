package org.ssmp.dtos;

import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String surname;
    private String patronimyc;
    private String post;
    private String login;
    private String pass;
    private String role;
}
