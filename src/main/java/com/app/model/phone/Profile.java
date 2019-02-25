package com.app.model.phone;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @Pattern(regexp = "[A-Z|a-z]+")
    private String name;
    @NotNull
    @Min(0)
    private BigDecimal maxPrice;
    private double minScreenDiagonal;
    private int minCameraResolution;
    private int minInternalStorage;
    private int minRam;
    @NotNull
    @Min(0)
    private int minBatteryCapacity;
    @ElementCollection
    @CollectionTable
    private List<String> dataTransmissions;
    @ElementCollection
    @CollectionTable
    private List<String> displays;
    @ElementCollection
    @CollectionTable
    private List<String> typesOfMemoryCards;
    private String dualSim;
    private String bluetooth;
    private String wifi;
    private String jack;
    private String usb;
}
