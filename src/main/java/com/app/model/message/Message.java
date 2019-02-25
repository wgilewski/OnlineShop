package com.app.model.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Message
{

    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Imię powinno zawierać same litery")
    private String name;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Nazwisko powinno zawierać same litery")
    private String surname;

    @Email(message = "Niepoprawny format email")
    private String email;
    private String messageContent;
    private String replyMessage;
    private boolean status;
}
