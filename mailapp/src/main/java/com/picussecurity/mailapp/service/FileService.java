package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    List<User> convertFileToList(MultipartFile file);
}
