package com.lyncwork.user.api.service;

import com.lyncwork.user.api.exceptions.ResourceNotFoundException;
import com.lyncwork.user.api.model.User;
import com.lyncwork.user.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User userData) throws ResourceNotFoundException {
        User user = findUserById(userId);
        user.setRoles(userData.getRoles());
        user.setLastName(userData.getLastName());
        user.setFirstName(userData.getFirstName());
        return userRepository.save(user);
    }

    public void deleteById(Long userId)  throws ResourceNotFoundException  {
        User user = findUserById(userId);
        if(user != null){
            userRepository.deleteById(userId);
        }
    }

}
