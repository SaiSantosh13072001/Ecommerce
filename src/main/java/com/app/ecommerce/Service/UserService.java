package com.app.ecommerce.Service;

import org.springframework.stereotype.Service;

import com.app.ecommerce.entity.User;
import com.app.ecommerce.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user){
        user.setRole("USER");
        return userRepository.save(user);
    }
}