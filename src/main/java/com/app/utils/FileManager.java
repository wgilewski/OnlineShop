package com.app.utils;

import com.app.exceptions.MyException;
import com.app.model.phone.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Component
public class FileManager {
    @Value("${img.path}")
    private String imgPath;

    private Logger logger = LoggerFactory.getLogger(FileManager.class);

    private String createFilename(Phone phone) {
        try {
            final String filename = phone.getProducer() + "_" + phone.getModel() + "_" + phone.getColor();
            logger.info("FILENAME CREATED");

            String originalFilename = phone.getMultipartFile().getOriginalFilename();
            String[] arr = originalFilename.split("\\.");
            String extension = arr[arr.length - 1];
            logger.info("EXTENSION GOT");

            return filename + "." + extension;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("FILE MANAGER CREATE FILENAME EXCEPTION", LocalDateTime.now());
        }
    }

    public String addFile(Phone phoneDto) {
        try {
            MultipartFile file = phoneDto.getMultipartFile();
            logger.info("FILE GOT FROM MULTIPART");

            if (file == null || file.getBytes().length == 0) {
                throw new NullPointerException("FILE IS NOT CORRECT");
            }

            String filename = createFilename(phoneDto);
            String fullPath = imgPath + filename;
            logger.info("FULL PATH CREATED");

            FileCopyUtils.copy(file.getBytes(), new File(fullPath));
            logger.info("FILE SAVED");

            return filename;

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new MyException("FILE MANAGER ADD FILE EXCEPTION", LocalDateTime.now());
        }
    }
}
