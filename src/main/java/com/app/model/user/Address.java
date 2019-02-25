package com.app.model.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Państwo powinno składać się z samych liter")
    private String country;
    @Pattern(regexp = "[A-Z|a-z]+", message = "Miasto powinno składać się z samych liter")
    private String town;
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}", message = "Niepoprawny kod pocztowy")
    private String zipCode;
    private String street;
    @Pattern(regexp = "[0-9]{1,3}|[0-9]{1,3}/[0-9]{1,2}", message = "Niepoprawny numer domu/mieszkania")
    private String houseNumber;
    @Pattern(regexp = "[0-9]{9}", message = "Numer telefonu powinien składać się z 9 cyfr")
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
