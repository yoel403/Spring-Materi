package com.example.LatSpring.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LatSpring.model.dto.PeminjamanBukuDto;
import com.example.LatSpring.model.dto.ResponseData;
import com.example.LatSpring.model.entity.Book;
import com.example.LatSpring.model.entity.Category;
import com.example.LatSpring.model.entity.PeminjamanBuku;
import com.example.LatSpring.model.entity.User;
import com.example.LatSpring.repository.BookRepository;
import com.example.LatSpring.repository.PeminjamanBukuRepository;
import com.example.LatSpring.repository.UserRepository;

@Service
@Transactional
public class PeminjamanBukuServiceImpl implements PeminjamanBukuService{
    
    @Autowired
    private PeminjamanBukuRepository peminjamanBukuRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Book book;
    private PeminjamanBuku peminjamanBuku;
    private ResponseData<Object> responseData;
    private List<PeminjamanBuku> peminjamanBukus;
    // private Category category;
    
    @Override
    public ResponseData<Object> meminjamBuku(PeminjamanBukuDto requestBuku ,PeminjamanBukuDto requestUser ) {
        // TODO Auto-generated method stub
        peminjamanBuku = new PeminjamanBuku();
        peminjamanBuku.setMeminjam(true);
        peminjamanBuku.setMengembalikan(false);

        //Time
        LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = now.format(format);  
        peminjamanBuku.setTanggal(formatDateTime);

        //Book
        book = bookRepository.findByTitle(requestBuku.getBook());
        if (requestBuku.getBook().isEmpty()) {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "Buku yang di pinjam tidak ada", null);
        }else{
            book.setDipinjam(true);
            bookRepository.save(book);
        }

        //user
        user = userRepository.findBorrowByEmail(requestUser.getUser());
        
        //Save
        peminjamanBukuRepository.save(peminjamanBuku);

        responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Meminjam success", peminjamanBuku);
        return responseData;
    }

    @Override
    public ResponseData<Object> mengembalikanBuku(PeminjamanBukuDto requestBuku , PeminjamanBukuDto requestUser) {
        // TODO Auto-generated method stub
        peminjamanBuku = new PeminjamanBuku();
        peminjamanBuku.setMeminjam(false);
        peminjamanBuku.setMengembalikan(true);

        //Time
        LocalDateTime now = LocalDateTime.now();  
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = now.format(format);  
        peminjamanBuku.setTanggal(formatDateTime);

        //book
        book = bookRepository.findByTitle(requestBuku.getBook());
        if (requestBuku.getBook().isEmpty()) {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "Buku yang di kembalikan tidak ada", null);
        }else{
            book.setDipinjam(false);
            bookRepository.save(book);
        }

        //User
        user = userRepository.findBorrowByEmail(requestUser.getUser());
        peminjamanBukuRepository.save(peminjamanBuku);
        responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Mengembalikan success", peminjamanBuku);
        return responseData;
    }

    @Override
    public ResponseData<Object> getHistory() {
        // TODO Auto-generated method stub
        peminjamanBukus = peminjamanBukuRepository.findAll();
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", peminjamanBukus);
        return responseData;
    }
    
}
