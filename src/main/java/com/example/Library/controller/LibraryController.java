package com.example.Library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;

import com.example.Library.entity.Book;
import com.example.Library.repository.BooksRepository;
import java.util.List;

@Controller // 📌 RestController가 아닙니다! HTML 화면을 렌더링하기 위한 Controller!
@RequiredArgsConstructor
public class LibraryController {

    private final BooksRepository booksRepository;

    @GetMapping("/books")
    public String bookList(Model model) {
        // 1. DB에서 모든 도서 목록을 꺼내옵니다.
        List<Book> bookList = booksRepository.findAll();

        // 2. 화면(HTML)으로 넘겨주기 위해 Model에 "books"라는 이름으로 담습니다.
        model.addAttribute("books", bookList);

        // 3. "book-list.html" 파일을 찾아서 브라우저에 띄워라!
        return "book-list";
    }

    // 주소 강제 Direct 변경
    @GetMapping("/")
    public String home() {
        // "/books" 주소로 곧바로 이동(리다이렉트) 시켜버립니다.
        return "redirect:/books";
    }
}