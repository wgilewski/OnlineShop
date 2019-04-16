package com.app.service;

import com.app.model.dto.mappers.ConverterDto;
import com.app.model.phone.Phone;
import com.app.repository.PhoneRepository;
import com.app.utils.FileManager;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService {


    private ConverterDto converterDto;
    private PhoneRepository phoneRepository;
    private FileManager fileManager;

    public PhoneService(ConverterDto converterDto, PhoneRepository phoneRepository, FileManager fileManager) {
        this.converterDto = converterDto;
        this.phoneRepository = phoneRepository;
        this.fileManager = fileManager;
    }

    public void addPhone(Phone phone) {
        String filename = fileManager.addFile(phone);
        phone.setFilename(filename);

        phone.setProducer(phone.getProducer().toUpperCase());
        phone.setModel(phone.getModel().toUpperCase());
        phone.setColor(phone.getColor().toUpperCase());

        phone.setHide(false);
        phoneRepository.save(phone);
    }

    public void updatePhone(Phone phone) {
        deleteFIle(phone.getId());
        String filename = fileManager.addFile(phone);
        phone.setFilename(filename);

        if (phone.getAvailable() > 0)
        {
            phone.setHide(false);
        }

        phoneRepository.save(phone);
    }

    public void deletePhone(Long id) {
        deleteFIle(id);
        phoneRepository.deleteById(id);
    }

    public List<Phone> findAllPhonesAdmin()
    {
        return phoneRepository.findAll();
    }

    public List<Phone> findAllPhonesUser()
    {
        return phoneRepository.findAll()
                .stream()
                .filter(phone -> phone.getHide() == false)
                .collect(Collectors.toList());
    }

    public Phone findOnePhone(Long id) {
        return phoneRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void deleteFIle(Long id) {
        File file = new File("C:\\Users\\wojci\\Desktop\\sklep_04\\sklep_internetowy\\src\\main\\resources\\static\\img\\phones\\" + phoneRepository.findById(id).orElseThrow(NullPointerException::new).getFilename());
        System.out.println(file);
        file.delete();
    }


}
