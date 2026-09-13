package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

public interface UserService {

    User createUser(User user);

    User loginUser(LoginDTO loginDTO);

    List<User> getAllUsers();

    User getUserByEmail(String email);

}
