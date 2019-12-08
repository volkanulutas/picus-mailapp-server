package com.picussecurity.mailapp.controller;

import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created volkanulutas on 07.12.2019.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers() {
        try {
            return userService.findAll();
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting all users. Detail: ", ex);
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
        try {
            User user = userService.findById(userId);
            if (user != null) {
                return ResponseEntity.ok().body(user);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting user by id. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while creating user. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addToMailGroup/{name}")
    public ResponseEntity<User> addUserToMailGroup2(@Valid @RequestBody User user,
                                                    @PathVariable("name") String mailGroupName) {
        try {
            return ResponseEntity.ok(userService.addUserToMailGroup(user, mailGroupName));
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while creating user. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userParam) {
        try {
            User user = userService.findById(userId);
            if (user != null) {
                user.setEmailId(userParam.getEmailId());
                user.setLastName(userParam.getLastName());
                user.setFirstName(userParam.getFirstName());
                final User updatedUser = userService.saveUser(user);
                return ResponseEntity.ok(updatedUser);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while updating user. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) {
        try {
            User user = userService.findById(userId);
            if (user != null) {
                userService.deleteUser(userId);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return response;
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while deleting user. Detail: ", ex);
        }
        return Collections.emptyMap();
    }
}