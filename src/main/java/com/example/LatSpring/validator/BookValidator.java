package com.example.LatSpring.validator;

import com.example.LatSpring.exception.custom.CustomBadRequestException;
import com.example.LatSpring.exception.custom.CustomNotFoundException;
import com.example.LatSpring.model.dto.request.PeminjamanBukuDto;
import com.example.LatSpring.model.entity.Book;

public class BookValidator {
    public void validateBookNotFoundOrIsDeleted(PeminjamanBukuDto string,Book book) throws Exception {
        if (string.getBook().isEmpty()|| book.getIsDeleted().equals(true)) {
            throw new CustomNotFoundException("Buku Tidak ada atau sudah di hapus");
        }
    }

    public void validateBookIsDipinjam(Book book) throws Exception {
        if (book.getIsDipinjam().equals(true)) {
            throw new CustomBadRequestException("Buku sudah di pinjam sama orang");
        }
    }

    public void validateReturnBookNotFoundOrIsDeleted(PeminjamanBukuDto request,Book book) throws Exception {
        if (request.getBook().isEmpty()|| book.getIsDipinjam().equals(false)) {
            throw new CustomNotFoundException("Buku Tidak dipinjam atau sudah di hapus/tidak ada");
        }
    }
    
}
