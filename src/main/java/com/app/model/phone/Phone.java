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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) &&
                Objects.equals(producer, phone.producer) &&
                Objects.equals(model, phone.model) &&
                Objects.equals(price, phone.price) &&
                Objects.equals(available, phone.available) &&
                Objects.equals(screenDiagonal, phone.screenDiagonal) &&
                Objects.equals(operatingSystem, phone.operatingSystem) &&
                Objects.equals(cameraResolution, phone.cameraResolution) &&
                Objects.equals(internalStorage, phone.internalStorage) &&
                Objects.equals(ram, phone.ram) &&
                Objects.equals(dataTransmission, phone.dataTransmission) &&
                Objects.equals(dualSim, phone.dualSim) &&
                Objects.equals(color, phone.color) &&
                Objects.equals(display, phone.display) &&
                Objects.equals(batteryCapacity, phone.batteryCapacity) &&
                Objects.equals(bluetooth, phone.bluetooth) &&
                Objects.equals(wifi, phone.wifi) &&
                Objects.equals(jack, phone.jack) &&
                Objects.equals(usb, phone.usb) &&
                Objects.equals(typeOfMemoryCard, phone.typeOfMemoryCard) &&
                Objects.equals(filename, phone.filename) &&
                Objects.equals(multipartFile, phone.multipartFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producer, model, price, available, screenDiagonal, operatingSystem, cameraResolution, internalStorage, ram, dataTransmission, dualSim, color, display, batteryCapacity, bluetooth, wifi, jack, usb, typeOfMemoryCard, filename, multipartFile);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", screenDiagonal='" + screenDiagonal + '\'' +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", cameraResolution='" + cameraResolution + '\'' +
                ", internalStorage='" + internalStorage + '\'' +
                ", ram='" + ram + '\'' +
                ", dataTransmission='" + dataTransmission + '\'' +
                ", dualSim=" + dualSim +
                ", color='" + color + '\'' +
                ", display='" + display + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                ", bluetooth=" + bluetooth +
                ", wifi=" + wifi +
                ", jack=" + jack +
                ", usb=" + usb +
                ", typeOfMemoryCard='" + typeOfMemoryCard + '\'' +
                ", filename='" + filename + '\'' +
                ", multipartFile=" + multipartFile +
                '}';
    }
}
