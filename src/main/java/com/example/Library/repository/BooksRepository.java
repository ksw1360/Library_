package com.example.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Library.entity.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Long> { // 📌 Rentals -> Book 으로 수정!

    // 서적 대출
    @Modifying
    @Query(value = "UPDATE books SET available_count = available_count - 1 WHERE id = :bookId", nativeQuery = true)
    void decreaseCount(@Param("bookId") Long bookId);

    // 서적 반납
    @Modifying
    @Query(value = "UPDATE books SET available_count = available_count + 1 WHERE id = :bookId", nativeQuery = true)
    void increaseCount(@Param("bookId") Long bookId);
}