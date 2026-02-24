package com.example.Library.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Library.dto.RentalListDTO;
import com.example.Library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("/rent")
    public String rentBook(
            @RequestParam("userId") Long userId,
            @RequestParam("bookId") Long bookId) {

        try {
            rentalService.rentBook(userId.intValue(), bookId);

            return "redirect:/rental/list";

        } catch (Exception e) {
            System.out.println("대출 실패: " + e.getMessage());
            return "redirect:/rental/list";
        }
    }

    @GetMapping("/rental/list")
    public String rentalList(Model model) {

        List<RentalListDTO> list = rentalService.getRentalList();

        try {
            if (list != null) {
                System.out.println("DB에서 가져온 대출 건수: " + list.size() + "건");

                model.addAttribute("Rentals", list);
            } else {
                System.out.println("list 가 null입니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "rent-list";
    }
}