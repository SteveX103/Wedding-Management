package com.javabackend.Backend.Controller;

import com.javabackend.Backend.Entity.User;
import com.javabackend.Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        String email = userData.get("email");
        String name = userData.get("name");
        String password = userData.get("password");
        String productName = userData.get("ProductName");
        String productUrl = userData.get("ProductUrl");
        String productAbout = userData.get("ProductAbout");
        String productPrice = userData.get("ProductPrice");
        try {
            User user = userService.createUser(email, name, password,productName,productUrl,productPrice,productAbout);
            // Return user data in response
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = userService.getUserByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        // If login successful, return the user data
        return ResponseEntity.ok(user);
    }
    @PutMapping("/add-product")
    public ResponseEntity<?> addProductToUser(@RequestBody Map< String,String> credentials) {
        // Check if the user exists
        String email = credentials.get("email");
        String productName = credentials.get("ProductName");
        String productAbout = credentials.get("ProductAbout");
        String productPrice = credentials.get("ProductPrice");
        String productUrl = credentials.get("ProductUrl");
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email " + email + " not found");
        }

        try {
            // Update the user's product
            userService.updateUserProduct(email, productName,productAbout,productPrice,productUrl);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
