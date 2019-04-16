/*
package com.app.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PhoneDto {
    private Long id;
    private Boolean hide;
    @Pattern(regexp = "[A-Za-z0-9 ]+", message = "Niepoprawna nazwa producenta")
    private String producer;
    @Pattern(regexp = "[A-Za-z0-9 ]+", message = "Niepoprawna nazwa modelu")
    private String model;
    @NotNull
    @NotNull(message = "Niepoprawna cena")
    @Min(value = 1, message = "Cena nie powinna być mniejsza od 1")
    private BigDecimal price;
    @NotNull(message = "Niepoprawna dostępność")
    @Min(value = 1, message = "Dostępność nie powinna być mniejsza od 1")
    private Integer available;
    private String screenDiagonal;
    private String operatingSystem;
    private String cameraResolution;
    private String internalStorage;
    private String ram;
    private String dataTransmission;
    @NotNull(message = "Zaznacz true/false")
    private Boolean dualSim;
    @Pattern(regexp = "[A-Za-z]+", message = "Niepoprawna nazwa koloru")
    private String color;
    private String display;
    @NotNull(message = "Niepoprawna pojemność baterii")
    @Min(value = 1, message = "Pojemność nie powinna być mniejsza od 1")
    private Integer batteryCapacity;
    @NotNull(message = "Zaznacz true/false")
    private Boolean bluetooth;
    @NotNull(message = "Zaznacz true/false")
    private Boolean wifi;
    @NotNull(message = "Zaznacz true/false")
    private Boolean jack;
    @NotNull(message = "Zaznacz true/false")
    private Boolean usb;
    private String typeOfMemoryCard;
    private MultipartFile multipartFile;
    private String filename;

}
*/
