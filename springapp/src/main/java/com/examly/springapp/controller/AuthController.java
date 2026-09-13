package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

    private UserService userService;
    private JwtUtils jwtUtils;
    private BCryptPasswordEncoder encoder;

    public AuthController(UserService userService,JwtUtils jwtUtils,BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.jwtUtils=jwtUtils;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        User registerUser = userService.createUser(user);
        if(registerUser!=null){
            return ResponseEntity.status(201).body(registerUser);
        }
        return ResponseEntity.status(409).body("User already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        User authenticatedUser = userService.loginUser(loginDTO);
        if (authenticatedUser!= null) {
            UserPrinciple userDetails = new UserPrinciple(authenticatedUser);
            String token = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        // return ResponseEntity.status(401).body("User not found");//an exception need to be created for this.
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            return ResponseEntity.status(200).body(users);
        }
        return ResponseEntity.status(404).body("No list of users present.");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email){    
        User user = userService.getUserByEmail(email);
        if(user!=null){
            return ResponseEntity.status(200).body(user);
        }
        return ResponseEntity.status(404).body(String.format("User is not found for email %s",email));
    }
    
}
