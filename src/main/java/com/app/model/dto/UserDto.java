package com.app.model.dto;


import com.app.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Niepoprawna nazwa użytkownika")
    private String username;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Imię powinno składać się z samych liter")
    private String name;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Nazwisko powinno składać się z samych liter")
    private String surname;

    @Email(message = "Niepoprawny format email")
    private String email;
    private String passwordConfirmation;
    private String password;
    private Role role;
    private boolean isActive;


}
