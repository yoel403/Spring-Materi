package com.example.LatSpring.services;

import com.example.LatSpring.model.dto.BookDto;
import com.example.LatSpring.model.dto.ResponseData;

public interface CategoryService {
  ResponseData<Object> addCategory(BookDto request);

  ResponseData<Object> getCategory(Boolean status);

  ResponseData<Object> getCategoryById(long id);

  ResponseData<Object> updateCategory(long id, BookDto request);

  ResponseData<Object> deleteCategory(long id);
}