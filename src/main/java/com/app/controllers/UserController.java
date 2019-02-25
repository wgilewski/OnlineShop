package com.app.controllers;


import com.app.model.dto.UserDto;
import com.app.model.phone.Profile;
import com.app.model.user.Address;
import com.app.model.user.Role;
import com.app.model.user.User;
import com.app.model.VerificationToken;
import com.app.service.AddressService;
import com.app.service.PhoneProfileService;
import com.app.service.PhoneService;
import com.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {


    private PhoneProfileService phoneProfileService;
    private PhoneService phoneService;
    private UserService userService;
    private AddressService addressService;


    public UserController(PhoneProfileService phoneProfileService, PhoneService phoneService, UserService userService, AddressService addressService) {
        this.phoneProfileService = phoneProfileService;
        this.phoneService = phoneService;
        this.userService = userService;
        this.addressService = addressService;

    }


    @GetMapping
    public String userPage() {
        return "roles/user";
    }


    @GetMapping("/comparePhones")
    public String comparePhonesGet() {
        return "phones/comparePhones";
    }


    @GetMapping("/selectAll")
    public String selectAllPhonesGet(Model model) {
        model.addAttribute("phones", phoneService.findAllPhonesUser());
        return "phones/phonesListUser";
    }

    @GetMapping("/groupFilter")
    public String groupFilterGet(Model model) {
        model.addAttribute("profiles", phoneProfileService.findAllProfilesNames());
        model.addAttribute("profile", new Profile());
        return "filters/groupFilter";
    }

    @PostMapping("/groupFilter")
    public String groupFilterPost(@ModelAttribute Profile profile, Model model)
    {
        model.addAttribute("phones", phoneProfileService.findPhonesByProfile(profile));
        return "filters/filtered";
    }

    @GetMapping("/details/{id}")
    public String selectOnePhone(@PathVariable Long id, Model model) {
        model.addAttribute("phone", phoneService.findOnePhone(id));
        return "phones/phoneDetailsUser";
    }


    @GetMapping("/contactDetails")
    public String ContactDetailsGet(Model model)
    {

        if (userService.getLoggedUser().getAddress() == null) {

            model.addAttribute("address", new Address());
            model.addAttribute("errors", new HashMap<>());
            return "userDetails/contactDetails";
        } else {

            model.addAttribute("address", addressService.findAddress(userService.getLoggedUserId()));
            model.addAttribute("errors", new HashMap<>());
            return "userDetails/contactDetails";
        }
    }

    @PostMapping("/contactDetails")
    public String contactDetailsPost(@Valid @ModelAttribute Address address, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("address", address);
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (m1, m2) -> m1 + ", " + m2)));
            return "userDetails/contactDetails";
        }


        if (address.getUser() == null) {
            addressService.addAddress(address);
        } else {
            addressService.updateAddress(address);
        }
        return "roles/user";
    }


    @GetMapping("/registration")
    public String userRegistration(Model model) {
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", Role.values());
        model.addAttribute("errors", new HashMap<>());
        return "unLogged/registration";
    }

    @PostMapping("/registration")
    public String userRegistrationPost(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (m1, m2) -> m1 + ", " + m2
                    )));
            model.addAttribute("user", userDto);
            model.addAttribute("roles", Role.values());
            return "unLogged/registration";
        }
        userService.registerNewUserAccount(userDto, request);
        return "redirect:/";
    }

    //UPDATING USER ACTIVE FIELD
    @GetMapping("/registrationConfirmation")
    public String registrationConfirmation(@RequestParam String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            throw new IllegalArgumentException("BLAD AKTYWACJI UZYTKOWNIKA");
        }
        User user = verificationToken.getUser();
        if (verificationToken.getExpirationDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("CZAS AKTYWACJI UPLYNAL");
        }

        user.setActive(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login";
    }


}