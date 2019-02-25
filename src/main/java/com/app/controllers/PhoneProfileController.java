package com.app.controllers;

import com.app.model.phone.Profile;
import com.app.service.PhoneProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/phoneProfile")
public class PhoneProfileController {
    private PhoneProfileService phoneProfileService;

    public PhoneProfileController(PhoneProfileService phoneProfileService) {
        this.phoneProfileService = phoneProfileService;
    }

    @GetMapping("/profileManagement")
    public String phoneProfileManagementGet(Model model) {
        model.addAttribute("profiles", phoneProfileService.findAllProfiles());
        return "phoneProfile/profileManagement";
    }

    @GetMapping("/newProfile")
    public String newPhoneProfileGet(Model model) {

        model.addAttribute("profile", new Profile());
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute("screenDiagonals", Arrays.asList("4", "4.7", "5", "5.2", "5.5"));
        model.addAttribute("cameraResolutions", Arrays.asList("5", "8", "12", "13", "16"));
        model.addAttribute("internalStorages", Arrays.asList("4", "8", "16", "32", "64"));
        model.addAttribute("rams", Arrays.asList("1", "2", "3", "4", "8"));
        model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G", "-"));
        model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD", "-"));
        model.addAttribute("typesOfMemoryCard", Arrays.asList("microSD", "microSDHC", "microSDXC", "-"));
        model.addAttribute("dualSim", Arrays.asList("true", "-"));
        model.addAttribute("bluetooth", Arrays.asList("true", "-"));
        model.addAttribute("wifi", Arrays.asList("true", "-"));
        model.addAttribute("jack", Arrays.asList("true", "-"));
        model.addAttribute("usb", Arrays.asList("true", "-"));

        return "phoneProfile/newProfile";

    }

    @PostMapping("/newProfile")
    public String phoneProfileManagementPost(@Valid @ModelAttribute Profile profile, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("profile", profile);
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (m1, m2) -> m1 + ", " + m2)));
            model.addAttribute("screenDiagonals", Arrays.asList("4", "4.7", "5", "5.2", "5.5"));
            model.addAttribute("cameraResolutions", Arrays.asList("5", "8", "12", "13", "16"));
            model.addAttribute("internalStorages", Arrays.asList("4", "8", "16", "32", "64"));
            model.addAttribute("rams", Arrays.asList("1", "2", "3", "4", "8"));
            model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G", "-"));
            model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD", "-"));
            model.addAttribute("typesOfMemoryCard", Arrays.asList("microSD", "microSDHC", "microSDXC", "-"));
            model.addAttribute("dualSim", Arrays.asList("true", "-"));
            model.addAttribute("bluetooth", Arrays.asList("true", "-"));
            model.addAttribute("wifi", Arrays.asList("true", "-"));
            model.addAttribute("jack", Arrays.asList("true", "-"));
            model.addAttribute("usb", Arrays.asList("true", "-"));

            return "phoneProfile/newProfile";


        }

        phoneProfileService.addOrUpdatePhoneProfile(profile);
        return "redirect:/phoneProfile/profileManagement";
    }

    @GetMapping("/profileUpdate/{id}")
    public String profileUpdateGet(@PathVariable Long id, Model model) {

        model.addAttribute("profile", phoneProfileService.findProfileById(id));
        model.addAttribute("screenDiagonals", Arrays.asList("4", "4.7", "5", "5.2", "5.5"));
        model.addAttribute("cameraResolutions", Arrays.asList("5", "8", "12", "13", "16"));
        model.addAttribute("internalStorages", Arrays.asList("4", "8", "16", "32", "64"));
        model.addAttribute("rams", Arrays.asList("1", "2", "3", "4", "8"));
        model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G", "-"));
        model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD", "-"));
        model.addAttribute("typesOfMemoryCard", Arrays.asList("microSD", "microSDHC", "microSDXC", "-"));
        model.addAttribute("dualSim", Arrays.asList("true", "-"));
        model.addAttribute("bluetooth", Arrays.asList("true", "-"));
        model.addAttribute("wifi", Arrays.asList("true", "-"));
        model.addAttribute("jack", Arrays.asList("true", "-"));
        model.addAttribute("usb", Arrays.asList("true", "-"));
        return "phoneProfile/profileUpdate";
    }

    @PostMapping("/profileUpdate")
    public String profileUpdatePost(@ModelAttribute Profile profile) {
        phoneProfileService.addOrUpdatePhoneProfile(profile);
        return "redirect:/phoneProfile/profileManagement";
    }

    @GetMapping("profileDetails/{id}")
    public String phoneProfileDetails(@PathVariable Long id, Model model) {
        model.addAttribute("profile", phoneProfileService.findProfileById(id));
        return "phoneProfile/profileDetails";
    }

    @GetMapping("/deleteProfile/{id}")
    public String deletePhoneProfile(@PathVariable Long id) {
        phoneProfileService.deleteProfile(id);

        return "redirect:/phoneProfile/profileManagement";
    }


}
