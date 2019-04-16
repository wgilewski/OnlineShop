package com.app.model.dto.mappers;

import com.app.model.dto.UserDto;
import com.app.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class ConverterDto {


    public User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .isActive(userDto.isActive())
                .role(userDto.getRole())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .isActive(user.isActive())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
    }


   /* public Phone fromPhoneDtoToPhone(PhoneDto phoneDto) {
        return Phone.builder()
                .id(phoneDto.getId())
                .hide(phoneDto.getHide())
                .producer(phoneDto.getProducer())
                .model(phoneDto.getModel())
                .price(phoneDto.getPrice())
                .available(phoneDto.getAvailable())
                .screenDiagonal(phoneDto.getScreenDiagonal())
                .operatingSystem(phoneDto.getOperatingSystem())
                .cameraResolution(phoneDto.getCameraResolution())
                .internalStorage(phoneDto.getInternalStorage())
                .ram(phoneDto.getRam())
                .dataTransmission(phoneDto.getDataTransmission())
                .dualSim(phoneDto.getDualSim())
                .color(phoneDto.getColor())
                .display(phoneDto.getDisplay())
                .bluetooth(phoneDto.getBluetooth())
                .wifi(phoneDto.getWifi())
                .jack(phoneDto.getJack())
                .usb(phoneDto.getUsb())
                .batteryCapacity(phoneDto.getBatteryCapacity())
                .typeOfMemoryCard(phoneDto.getTypeOfMemoryCard())
                .multipartFile(phoneDto.getMultipartFile())
                .filename(phoneDto.getFilename())
                .build();
    }

    public PhoneDto fromPhoneToPhoneDto(Phone phone) {
        return PhoneDto.builder()
                .id(phone.getId())
                .hide(phone.getHide())
                .producer(phone.getProducer())
                .model(phone.getModel())
                .price(phone.getPrice())
                .available(phone.getAvailable())
                .screenDiagonal(phone.getScreenDiagonal())
                .operatingSystem(phone.getOperatingSystem())
                .cameraResolution(phone.getCameraResolution())
                .internalStorage(phone.getInternalStorage())
                .ram(phone.getRam())
                .dataTransmission(phone.getDataTransmission())
                .dualSim(phone.getDualSim())
                .color(phone.getColor())
                .display(phone.getDisplay())
                .bluetooth(phone.getBluetooth())
                .wifi(phone.getWifi())
                .jack(phone.getJack())
                .usb(phone.getUsb())
                .batteryCapacity(phone.getBatteryCapacity())
                .typeOfMemoryCard(phone.getTypeOfMemoryCard())
                .multipartFile(phone.getMultipartFile())
                .filename(phone.getFilename())
                .build();
    }*/
}
