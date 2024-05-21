package org.ssmp.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateUserDTO {
    private String post;

    private String surname;

    private String firstname;

    private String patronymic;

    private String login;

    private String password;
    private String role;
}
