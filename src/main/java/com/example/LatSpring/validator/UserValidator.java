package com.example.LatSpring.validator;

import com.example.LatSpring.exception.custom.CustomNotFoundException;
import com.example.LatSpring.model.dto.request.PeminjamanBukuDto;

public class UserValidator {
    public void validateUserNotFound(PeminjamanBukuDto request)throws Exception {
        if (request.getUser().isEmpty()) {
            throw new CustomNotFoundException("User tidak ditemukan");
        }
    }
}
