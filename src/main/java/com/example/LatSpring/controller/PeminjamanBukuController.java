package com.example.LatSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LatSpring.exception.custom.CustomBadRequestException;
import com.example.LatSpring.exception.custom.CustomNotFoundException;
import com.example.LatSpring.model.dto.request.PeminjamanBukuDto;
import com.example.LatSpring.model.dto.response.ResponseData;
import com.example.LatSpring.services.CategoryService;
import com.example.LatSpring.services.PeminjamanBukuService;

@RestController
@RequestMapping("/peminjaman")
public class PeminjamanBukuController {
    @Autowired
    private PeminjamanBukuService peminjamanBukuService;

    private ResponseData<Object> responseData;

    @PostMapping("/borrow")
    public ResponseEntity<Object> meminjamBuku(@RequestBody PeminjamanBukuDto request) throws Exception {
        responseData = peminjamanBukuService.meminjamBuku(request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> mengembalikanBuku(@RequestBody PeminjamanBukuDto request) throws Exception {
        responseData = peminjamanBukuService.mengembalikanBuku(request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
