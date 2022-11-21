package com.example.LatSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.LatSpring.model.entity.DetailUser;
import com.example.LatSpring.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);

    void save(DetailUser detailUser);

    @Query(value = "SELECT * FROM `users` WHERE email = ?1", nativeQuery = true)
    User findBorrowByEmail(String email);
}
