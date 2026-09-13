package com.examly.springapp.service;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;
    private BCryptPasswordEncoder encoder;
    
    public UserServiceImpl(UserRepo userRepo,BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    /*
     * Here the user is saved after registering.
     */
    @Override
    public User createUser(User user) {
        User existing = userRepo.findByEmail(user.getEmail());
        if(existing!=null){
            return null;
        }
        return userRepo.save(user);
        
    }

    /*
     * Here user needs to be authenticated based on the token,
     * then user will be logged in based on the authentication,
     * login part will be done after implementing security part.
     * 
     * For Now we will just save the user.
     */
    @Override
    public User loginUser(LoginDTO loginDTO) {
        User user = userRepo.findByEmail(loginDTO.getEmail());
        if (user != null && encoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers(){
        List<User> users = userRepo.findAll();
        if(!users.isEmpty()){
            return users;
        }
        return Collections.emptyList();
    }

    @Override
    public User getUserByEmail(String email){
        User user = userRepo.findByEmail(email);
        if(user!=null){
            return user;
        }
        return null;
    }
    
}

