package com.example.LatSpring.services;

import com.example.LatSpring.model.dto.request.UserDto;
import com.example.LatSpring.model.dto.response.ResponseData;

public interface UserServices {
    ResponseData<Object> register(UserDto request);
  
    ResponseData<Object> login(UserDto request);

    ResponseData<Object> updateUser( UserDto request);

    // ResponseData<Object> getAll();
  }