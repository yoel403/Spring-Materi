package com.example.LatSpring.services;

import com.example.LatSpring.model.dto.request.BookDto;
import com.example.LatSpring.model.dto.response.ResponseData;

public interface BookServices {
  // Proses CRUD
  ResponseData<Object> createBook(BookDto requesDto);

  ResponseData<Object> getAll();

  ResponseData<Object> getById(long id);

  ResponseData<Object> updateBook(long id, BookDto request);

  ResponseData<Object> deleteBook(long id);
}
