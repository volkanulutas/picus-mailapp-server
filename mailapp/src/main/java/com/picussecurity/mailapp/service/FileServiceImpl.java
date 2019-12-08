package com.picussecurity.mailapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.picussecurity.mailapp.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    public List<User> convertFileToList(MultipartFile file) {
        BufferedReader br;
        List<User> result = new ArrayList<>();
        try {
            String line;
            InputStream is = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                String resultName = "";
                String resultSurname = "";
                String[] splitterNameMail = line.split("<");
                String name = splitterNameMail[0];
                String[] nameSplit = name.split(" ");
                if (nameSplit.length > 2) { // two name check
                    resultName = nameSplit[0] + " " + nameSplit[1];
                    resultSurname = nameSplit[2];
                } else {
                    resultName = nameSplit[0];
                    resultSurname = nameSplit[1];
                }
                String mail = splitterNameMail[1];
                mail = mail.replace(">", "");
                result.add(new User(resultName, resultSurname, mail, true));
            }
        } catch (IOException ex) {
            LOGGER.error("Error is occurred while reading file! Detail: ", ex);
        }
        return result;
    }
}