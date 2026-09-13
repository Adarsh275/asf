package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;
/**
 * Repository interface for managing `Feedback` entity operations.
 * 
 * Extends `JpaRepository` to inherit basic CRUD and JPA-specific operations.
*/
@Repository
public interface UserRepo extends JpaRepository<User, Long>{

    User findByEmail(String email);
}
