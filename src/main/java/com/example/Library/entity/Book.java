package com.example.Library.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "bookname")
    private String bookname;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "price")
    private int price;

    @Column(name = "total_count")
    private int total_count;

    @Column(name = "available_count")
    private int available_count;

    @Column(name = "createdate")
    private Timestamp createdate;

    @Column(name = "createuser")
    private String createuser;

    @Column(name = "modifydate")
    private Timestamp modifydate;

    @Column(name = "modifyuser")
    private String modifyuser;

    @PrePersist
    public void prePersist() {
        if (this.createdate == null) {
            this.createdate = Timestamp.valueOf(LocalDateTime.now());
        }
        if (this.modifydate == null) {
            this.modifydate = Timestamp.valueOf(LocalDateTime.now());
        }
    }

    public Object getAvailableCount() {
        return this.available_count;
    }
}
