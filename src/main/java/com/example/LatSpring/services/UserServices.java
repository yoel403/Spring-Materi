package com.example.LatSpring.services;

import com.example.LatSpring.model.dto.ResponseData;
import com.example.LatSpring.model.dto.UserDto;

public interface UserServices {
    ResponseData<Object> register(UserDto request);
  
    ResponseData<Object> login(UserDto request);

    ResponseData<Object> updateUser( UserDto request);

    // ResponseData<Object> getAll();
  }