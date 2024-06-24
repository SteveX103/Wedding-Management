package com.javabackend.Backend.Service;

import com.javabackend.Backend.Entity.User;
import com.javabackend.Backend.Interface.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email, String name, String password,String ProductName,String ProductUrl,String ProductPrice, String ProductAbout) {
        // Check if a user with the given email already exists
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User with email " + email + " already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setProductName(ProductName);
        user.setProductUrl(ProductUrl);
        user.setProductAbout(ProductAbout);
        user.setProductPrice(ProductPrice);

        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
    }
    public User updateUserProduct(String email, String newProductName,String newProductAbout,String newProductPrice,String newProductUrl) {
        // Retrieve the user by email
        User user = userRepository.findByEmail(email);

        // Check if the user exists
        if (user == null) {
            throw new RuntimeException("User with email " + email + " not found");
        }

        // Update the product
        user.setProductName(newProductName);
        user.setProductPrice(newProductPrice);
        user.setProductAbout(newProductAbout);
        user.setProductUrl(newProductUrl);

        // Save the updated user
        return userRepository.save(user);
    }
    // You can add more methods as per your requirements, like updating user details, etc.
}
