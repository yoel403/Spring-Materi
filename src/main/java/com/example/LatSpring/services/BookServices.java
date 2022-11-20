package com.example.LatSpring.services;

import com.example.LatSpring.model.dto.BookDto;
import com.example.LatSpring.model.dto.ResponseData;

public interface BookServices {
  // Proses CRUD
  ResponseData<Object> createBook(BookDto requesDto);

  ResponseData<Object> getAll();

  ResponseData<Object> getById(long id);

  ResponseData<Object> updateBook(long id, BookDto request);

  ResponseData<Object> deleteBook(long id);
}
