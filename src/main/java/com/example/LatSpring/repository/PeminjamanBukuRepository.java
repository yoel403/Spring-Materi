package com.example.LatSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LatSpring.model.entity.PeminjamanBuku;

public interface PeminjamanBukuRepository extends JpaRepository<PeminjamanBuku , Long> {
    
}
