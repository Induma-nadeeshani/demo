package com.practice.library.repository;

import com.practice.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositiry extends JpaRepository<User, Long> {
    Optional<User> findUserByRegNo(String regno);
}
