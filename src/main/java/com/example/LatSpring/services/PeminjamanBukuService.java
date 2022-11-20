package com.example.LatSpring.services;

import com.example.LatSpring.model.dto.BookDto;
import com.example.LatSpring.model.dto.PeminjamanBukuDto;
import com.example.LatSpring.model.dto.ResponseData;

public interface PeminjamanBukuService {
    ResponseData<Object> meminjamBuku (PeminjamanBukuDto requestBuku , PeminjamanBukuDto requestUser);

    ResponseData<Object> mengembalikanBuku (PeminjamanBukuDto requestBuku , PeminjamanBukuDto requestUser);

    ResponseData<Object> getHistory ();

}
