package com.example.LatSpring.services;

import com.example.LatSpring.exception.custom.CustomBadRequestException;
import com.example.LatSpring.exception.custom.CustomNotFoundException;
import com.example.LatSpring.model.dto.request.BookDto;
import com.example.LatSpring.model.dto.request.PeminjamanBukuDto;
import com.example.LatSpring.model.dto.response.ResponseData;

public interface PeminjamanBukuService {
    ResponseData<Object> meminjamBuku (PeminjamanBukuDto request) throws CustomNotFoundException, CustomBadRequestException, Exception;

    ResponseData<Object> mengembalikanBuku (PeminjamanBukuDto request) throws CustomNotFoundException, Exception;

    ResponseData<Object> getHistory ();

}
