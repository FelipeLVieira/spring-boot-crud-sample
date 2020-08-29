package com.lyncwork.user.api.controller;

import com.lyncwork.user.api.exceptions.ResourceNotFoundException;
import com.lyncwork.user.api.model.User;
import com.lyncwork.user.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/user")
    public User createUser( @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity < User > updateUser(@PathVariable(value = "id") Long userId,
                                                      @RequestBody User userData) throws ResourceNotFoundException {
        final User updatedUser = userService.updateUser(userId, userData);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public Map< String, Boolean > deleteUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        userService.deleteById(userId);
        Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
