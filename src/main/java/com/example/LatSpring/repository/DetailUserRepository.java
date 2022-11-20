package com.example.LatSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LatSpring.model.entity.DetailUser;
import com.example.LatSpring.model.entity.User;

@Repository
public interface DetailUserRepository extends JpaRepository<DetailUser, Long> {
    Optional<DetailUser> findByUserEmail(User user);
}
