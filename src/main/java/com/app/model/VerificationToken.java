package com.app.model;


import com.app.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VerificationToken {
    @Id
    @GeneratedValue
    private long id;
    private String token;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId", unique = true)
    private User user;
    private LocalDateTime expirationDateTime;
}
