package com.example.LatSpring.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "peminjaman")
@Data
@NoArgsConstructor
public class PeminjamanBuku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(iso = ISO.DATE, fallbackPatterns = { "M/d/yy", "dd.MM.yyyy" })
    private String tanggal;

    @ManyToOne
    @JoinColumn(name = "buku")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    private Boolean meminjam = false;

    private Boolean mengembalikan = false;
}
