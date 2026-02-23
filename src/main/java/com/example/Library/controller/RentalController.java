package com.example.Library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @GetMapping("/rent")
    public String rentBook(
            @RequestParam("userId") Long userId,
            @RequestParam("bookId") Long bookId) {

        try {
            rentalService.rentBook(userId.intValue(), bookId); // Repository 파라미터 타입에 맞게 Long을 Integer로 변환
            return "🎉 성공! " + userId + "번 회원이 " + bookId + "번 책을 무사히 대출했습니다. DB를 확인해보세요!";

        } catch (Exception e) {
            return "❌ 실패: " + e.getMessage();
        }
    }
}