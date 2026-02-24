package com.example.Library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import com.example.Library.dto.RentalListDTO;
import com.example.Library.entity.Book;
import com.example.Library.repository.BooksRepository;
import com.example.Library.repository.RentalsRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final BooksRepository booksRepository;
    private final RentalsRepository rentalsRepository;

    @Transactional
    public void rentBook(Integer userId, Long bookId) {

        // 1. 대여 가능한 재고 확인
        Book book = booksRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("해당 도서를 찾을 수 없습니다."));

        if ((Integer) book.getAvailableCount() <= 0) {
            throw new RuntimeException("해당 도서의 대출 가능한 재고가 없습니다.");
        }

        // 2. 도서 재고 1 감소 (UPDATE 쿼리)
        booksRepository.decreaseCount(bookId);

        // 3. 대여 기록 저장 (INSERT 쿼리)
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        Timestamp dueDate = Timestamp.valueOf(LocalDateTime.now().plusDays(7)); // 7일 뒤 반납

        rentalsRepository.insertRental(
                userId,
                bookId.intValue(), // Long을 Integer로 변환 (Repository 파라미터 타입에 맞춤)
                now,
                dueDate,
                "RENTED",
                now,
                String.valueOf(userId) // 등록자 (일단 userId로 넣음)
        );

        System.out.println("도서 대여가 완료되었습니다. User ID: " + userId + ", Book ID: " + bookId);
    }

    public List<RentalListDTO> getRentalList() {
        var temp = rentalsRepository.findRentalListWithDetails();
        return temp;

    }
}