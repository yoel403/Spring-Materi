package com.example.LatSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LatSpring.model.dto.BookDto;
import com.example.LatSpring.model.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByIsDeleted(boolean deleted);

  Category findByName(String name);

  Optional<Category> findCategoryByName(String string);
}
