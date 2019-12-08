package com.picussecurity.mailapp.controller;

import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.service.FileService;
import com.picussecurity.mailapp.service.FileServiceImpl;
import com.picussecurity.mailapp.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<User> userList = fileService.convertFileToList(file);
            userService.saveUsers(userList);
        } catch (Exception ex) {
            System.out.println("Error is occurred while uploading file. Detail: " + ex);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
