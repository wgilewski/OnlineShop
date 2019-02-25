package com.app.model.phone;


import com.app.model.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean hide;
    private String producer;
    private String model;
    private BigDecimal price;
    private Integer available;
    private String screenDiagonal;
    private String operatingSystem;
    private String cameraResolution;
    private String internalStorage;
    private String ram;
    private String dataTransmission;
    private Boolean dualSim;
    private String color;
    private String display;
    private Integer batteryCapacity;
    private Boolean bluetooth;
    private Boolean wifi;
    private Boolean jack;
    private Boolean usb;
    private String typeOfMemoryCard;
    private String filename;
    @Transient
    private MultipartFile multipartFile;

    @OneToMany(mappedBy = "phone", fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();


}
