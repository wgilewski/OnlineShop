package com.app.controllers;


import com.app.model.dto.PhoneDto;
import com.app.service.PhoneService;
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
@RequestMapping("/admin")
public class AdminController {

    private PhoneService phoneService;

    public AdminController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public String adminPage() {
        return "roles/admin";
    }

    @GetMapping("/newPhone")
    public String phoneInsertGet(Model model) {
        model.addAttribute("phone", new PhoneDto());
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute("screenDiagonals", Arrays.asList("4 cale", "4.7 cala", "5 cali", "5.2 cala", "5.5 cala"));
        model.addAttribute("operatingSystems", Arrays.asList("Android", "Windows Phone", "Apple iOS"));
        model.addAttribute("cameraResolutions", Arrays.asList("5 Mpix", "8 Mpix", "12 Mpix", "13 Mpix", "16 Mpix"));
        model.addAttribute("internalStorages", Arrays.asList("4 GB", "8 GB", "16 GB", "32 GB", "64 GB"));
        model.addAttribute("rams", Arrays.asList("1 GB", "2 GB", "3 GB", "4 GB", "8 GB"));

        model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G"));
        model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD"));
        model.addAttribute("typeOfMemoryCards", Arrays.asList("microSD", "microSDHC", "microSDXC"));
        model.addAttribute("dualSimb", Arrays.asList("true", "false"));
        model.addAttribute("bluetoothb", Arrays.asList("true", "false"));
        model.addAttribute("wifib", Arrays.asList("true", "false"));
        model.addAttribute("jackb", Arrays.asList("true", "false"));
        model.addAttribute("usbb", Arrays.asList("true", "false"));

        return "phones/newPhone";
    }

    @PostMapping("/newPhone")
    public String phoneInsertPost(@Valid @ModelAttribute PhoneDto phoneDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("phone", phoneDto);
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (m1, m2) -> m1 + ", " + m2)));
            model.addAttribute("screenDiagonals", Arrays.asList("4 cale", "4.7 cala", "5 cali", "5.2 cala", "5.5 cala"));
            model.addAttribute("operatingSystems", Arrays.asList("Android", "Windows Phone", "Apple iOS"));
            model.addAttribute("cameraResolutions", Arrays.asList("5 Mpix", "8 Mpix", "12 Mpix", "13 Mpix", "16 Mpix"));
            model.addAttribute("internalStorages", Arrays.asList("4 GB", "8 GB", "16 GB", "32 GB", "64 GB"));
            model.addAttribute("rams", Arrays.asList("1 GB", "2 GB", "3 GB", "4 GB", "8 GB"));
            model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G"));
            model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD"));
            model.addAttribute("typeOfMemoryCards", Arrays.asList("microSD", "microSDHC", "microSDXC"));
            model.addAttribute("dualSimb", Arrays.asList("true", "false"));
            model.addAttribute("bluetoothb", Arrays.asList("true", "false"));
            model.addAttribute("wifib", Arrays.asList("true", "false"));
            model.addAttribute("jackb", Arrays.asList("true", "false"));
            model.addAttribute("usbb", Arrays.asList("true", "false"));

            return "phones/newPhone";
        }

        phoneService.addPhone(phoneDto);
        return "redirect:/admin/selectAll";
    }


    @GetMapping("/selectAll")
    public String selectAllPhones(Model model) {
        model.addAttribute("phones", phoneService.findAllPhonesAdmin());
        return "phones/phonesList";
    }

    @GetMapping("/details/{id}")
    public String selectOnePhone(@PathVariable Long id, Model model) {
        model.addAttribute("phone", phoneService.findOnePhone(id));
        return "phones/phoneDetails";
    }

    @GetMapping("/update/{id}")
    public String phoneUpdateGet(@PathVariable Long id, Model model) {
        model.addAttribute("errors", new HashMap<>());
        model.addAttribute("phone", phoneService.findOnePhone(id));
        model.addAttribute("screenDiagonals", Arrays.asList("4 cale", "4.7 cala", "5 cali", "5.2 cala", "5.5 cala"));
        model.addAttribute("operatingSystems", Arrays.asList("Android", "Windows Phone", "Apple iOS"));
        model.addAttribute("cameraResolutions", Arrays.asList("5 Mpix", "8 Mpix", "12 Mpix", "13 Mpix", "16 Mpix"));
        model.addAttribute("internalStorages", Arrays.asList("4 GB", "8 GB", "16 GB", "32 GB", "64 GB"));
        model.addAttribute("rams", Arrays.asList("1 GB", "2 GB", "3 GB", "4 GB", "8 GB"));
        model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G"));
        model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD"));
        model.addAttribute("typeOfMemoryCards", Arrays.asList("microSD", "microSDHC", "microSDXC"));
        model.addAttribute("dualSimb", Arrays.asList("true", "false"));
        model.addAttribute("bluetoothb", Arrays.asList("true", "false"));
        model.addAttribute("wifib", Arrays.asList("true", "false"));
        model.addAttribute("jackb", Arrays.asList("true", "false"));
        model.addAttribute("usbb", Arrays.asList("true", "false"));

        return "phones/phoneUpdate";
    }

    @PostMapping("/phoneUpdate")
    public String phoneUpdatePost(@Valid @ModelAttribute PhoneDto phoneDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage,
                            (m1, m2) -> m1 + ", " + m2)));
            model.addAttribute("phone", phoneDto);
            model.addAttribute("screenDiagonals", Arrays.asList("4 cale", "4.7 cala", "5 cali", "5.2 cala", "5.5 cala"));
            model.addAttribute("operatingSystems", Arrays.asList("Android", "Windows Phone", "Apple iOS"));
            model.addAttribute("cameraResolutions", Arrays.asList("5 Mpix", "8 Mpix", "12 Mpix", "13 Mpix", "16 Mpix"));
            model.addAttribute("internalStorages", Arrays.asList("4 GB", "8 GB", "16 GB", "32 GB", "64 GB"));
            model.addAttribute("rams", Arrays.asList("1 GB", "2 GB", "3 GB", "4 GB", "8 GB"));
            model.addAttribute("dataTransmissions", Arrays.asList("LTE", "3G", "2G"));
            model.addAttribute("displays", Arrays.asList("IPS TFT", "TFT", "Super AMOLED", "AMOLED", "Super LCD"));
            model.addAttribute("typeOfMemoryCards", Arrays.asList("microSD", "microSDHC", "microSDXC"));
            model.addAttribute("dualSimb", Arrays.asList("true", "false"));
            model.addAttribute("bluetoothb", Arrays.asList("true", "false"));
            model.addAttribute("wifib", Arrays.asList("true", "false"));
            model.addAttribute("jackb", Arrays.asList("true", "false"));
            model.addAttribute("usbb", Arrays.asList("true", "false"));
            return "phones/phoneUpdate";
        }
        phoneService.updatePhone(phoneDto);
        return "redirect:/admin/selectAll";
    }

    @GetMapping("/delete/{id}")
    public String deletePhoneGet(@PathVariable Long id) {
        phoneService.deletePhone(id);
        return "redirect:/admin/selectAll";
    }


}
