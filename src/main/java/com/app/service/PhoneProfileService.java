package com.app.service;

import com.app.exceptions.MyException;
import com.app.model.phone.Phone;
import com.app.model.phone.Profile;
import com.app.repository.PhoneProfileRepository;
import com.app.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhoneProfileService {
    private PhoneRepository phoneRepository;
    private PhoneProfileRepository phoneProfileRepository;

    public PhoneProfileService(PhoneRepository phoneRepository, PhoneProfileRepository phoneProfileRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneProfileRepository = phoneProfileRepository;
    }


    public List<String> findAllProfilesNames()
    {
        return phoneProfileRepository.findAll()
                .stream()
                .map(profile -> profile.getName())
                .collect(Collectors.toList());
    }

    public Profile findProfileById(long id)
    {
        return phoneProfileRepository.findById(id).orElseThrow(() -> new MyException("NIE MA PROFILU O PODANYM ID", LocalDateTime.now()));
    }

    public List<Profile> findAllProfiles() {
        return phoneProfileRepository.findAll();
    }

    public void addOrUpdatePhoneProfile(Profile profile) {
        profile.setName(profile.getName().toUpperCase());
        phoneProfileRepository.save(profile);
    }



    public void deleteProfile(Long id) {
        phoneProfileRepository.deleteById(id);
    }

    public List<Phone> findPhonesByProfile(Profile profile)
    {

        Profile profile1 = phoneProfileRepository.findDistinctByName(profile.getName())
                .orElseThrow(() -> new MyException("NIE MA JESZCZE PROFILOW",LocalDateTime.now()));

        String[] screenDiagonal;
        String[] cameraResolution;
        String[] internalStorage;
        String[] ram;


        List<Phone> phones = new LinkedList<>();
        for (Phone p : phoneRepository.findAll()) {
            screenDiagonal = p.getScreenDiagonal().split(" ");
            cameraResolution = p.getCameraResolution().split(" ");
            internalStorage = p.getInternalStorage().split(" ");
            ram = p.getRam().split(" ");

            if (profile1.getMaxPrice().intValue() == 0.00 &&
                    screenDiagonalRange(screenDiagonal[0], profile1.getMinScreenDiagonal()) &&
                    cameraResolutionRange(cameraResolution[0], profile1.getMinCameraResolution()) &&
                    internalStorageRange(internalStorage[0], profile1.getMinInternalStorage()) &&
                    ramRange(ram[0],profile1.getMinRam()) &&
                    p.getBatteryCapacity() >= profile1.getMinBatteryCapacity() &&
                    dataFromList(p.getDataTransmission(), profile1.getDataTransmissions()) == true &&
                    dataFromList(p.getDisplay(), profile1.getDisplays()) == true &&
                    dataFromList(p.getTypeOfMemoryCard(), profile1.getTypesOfMemoryCards()) == true &&
                    booleanValues(p.getDualSim(), profile1.getDualSim()) == true &&
                    booleanValues(p.getJack(), profile1.getJack()) == true &&
                    booleanValues(p.getBluetooth(), profile1.getBluetooth()) == true &&
                    booleanValues(p.getWifi(), profile1.getWifi()) == true &&
                    booleanValues(p.getUsb(), profile1.getUsb()) == true) {
                phones.add(p);
            } else if (p.getPrice().intValue() <= profile1.getMaxPrice().intValue() &&
                    screenDiagonalRange(screenDiagonal[0], profile1.getMinScreenDiagonal()) &&
                    cameraResolutionRange(cameraResolution[0],profile1.getMinCameraResolution()) &&
                    internalStorageRange(internalStorage[0],profile1.getMinInternalStorage()) &&
                    ramRange(ram[0], profile1.getMinRam()) &&
                    p.getBatteryCapacity() >= profile1.getMinBatteryCapacity() &&
                    dataFromList(p.getDataTransmission(), profile1.getDataTransmissions()) == true &&
                    dataFromList(p.getDisplay(), profile1.getDisplays()) == true &&
                    dataFromList(p.getTypeOfMemoryCard(), profile1.getTypesOfMemoryCards()) == true &&
                    booleanValues(p.getDualSim(), profile1.getDualSim()) == true &&
                    booleanValues(p.getJack(), profile1.getJack()) == true &&
                    booleanValues(p.getBluetooth(), profile1.getBluetooth()) == true &&
                    booleanValues(p.getWifi(), profile1.getWifi()) == true &&
                    booleanValues(p.getUsb(), profile1.getUsb()) == true) {
                phones.add(p);
            }
        }
        return phones;
    }

    public boolean booleanValues(Boolean phone, String profile) {
        if (String.valueOf(phone) == profile || profile.equals("-")) {
            return true;
        } else return false;
    }

    public boolean dataFromList(String phone, List<String> profile) {

        if (profile.contains(phone) || profile.contains("-")) {
            return true;
        } else return false;
    }

    public boolean ramRange(String phone, int profile) {
        if (Integer.valueOf(phone) >= profile)
        {
            return true;
        } else
            return false;
    }

    public boolean internalStorageRange(String phone, int profile) {
        if (Integer.valueOf(phone) >= profile) {
            return true;
        } else return false;
    }

    public boolean screenDiagonalRange(String phone, double profile)
    {

        if (Double.valueOf(phone) >= profile){
            return true;
        } else return false;
    }

    public boolean cameraResolutionRange(String phone, int profile) {
        if (Integer.valueOf(phone) >= profile){
            return true;
        } else return false;
    }


}
