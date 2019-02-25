package com.app.service;

import com.app.exceptions.MyException;
import com.app.model.user.Address;
import com.app.model.user.User;
import com.app.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private UserService userService;

    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }



    public void addAddress(Address address)
    {

        if (address == null)
        {
            throw new MyException("NIEPOPRAWNY ADRES", LocalDateTime.now());
        }

        User user = userService.getLoggedUser();
        address.setCountry(address.getCountry().toUpperCase());
        address.setStreet(address.getStreet().toUpperCase());
        address.setTown(address.getTown().toUpperCase());
        user.setAddress(address);
        address.setUser(user);
        addressRepository.save(address);
    }

    public void updateAddress(Address address)
    {
        if (address == null)
        {
            throw new MyException("NIEPOPRAWNY ADRES", LocalDateTime.now());
        }

        User user = userService.getLoggedUser();
        Long addressId = user.getAddress().getId();
        user.setAddress(address);
        address.setUser(user);
        addressRepository.save(address);
        deleteAddress(addressId);

    }

    public void deleteAddress(Long id)
    {
        addressRepository.deleteById(id);
    }

    public Address findAddress(Long userId)
    {
        return addressRepository.findAddressByUserId(userId).orElseThrow(() -> new MyException("TEN UZYTKOWNIK NIE MA ADRESU",LocalDateTime.now()));
    }

}
