package com.javabackend.Backend.Interface;

import com.javabackend.Backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by email
     *
     * @param email the email to search for
     * @return the user with the given email, or null if not found
     */
    User findByEmail(String email);

    /**
     * Check if a user with the given email already exists
     *
     * @param email the email to check
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
}