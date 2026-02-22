package com.example.Library.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void rentBook(Long userId, Long bookId) {
        // 대여 처리 로직 (예: 대여 기록 저장, 책 재고 감소 등)
        String sql = "Select available_count from books where id = ?";

        Integer availableCount = jdbcTemplate.queryForObject(sql, Integer.class, bookId);
        if (availableCount == null || availableCount == 0) {
            throw new RuntimeException("해당 도서의 대출 가능한 재고가 없습니다.");
        }

        // 도서 재고 1 감소
        String updatesql = "UPDATE books SET available_count = available_count - 1 WHERE id = ?";
        jdbcTemplate.update(updatesql, bookId);

        // 대여 기록 저장 (예: rental_records 테이블에 사용자 ID, 책 ID, 대여 날짜 등 저장)
        String insertSql = "Insert into rentals (user_id, book_id, rent_date, due_date, status) " +
                "VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 'RENTED')";

        jdbcTemplate.update(insertSql, userId, bookId);

        System.out.println("도서 대여가 완료되었습니다. User ID: " + userId + ", Book ID: " + bookId);

    }
}
