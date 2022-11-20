package com.example.LatSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LatSpring.model.dto.PeminjamanBukuDto;
import com.example.LatSpring.model.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
Book findByTitle (String title);
}
