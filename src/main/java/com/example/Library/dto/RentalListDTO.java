package com.example.Library.dto;

import java.time.LocalDateTime;

public class RentalListDTO {
    private Integer id;
    private String username;
    private String bookname;
    private LocalDateTime rentDate;
    private LocalDateTime dueDate;
    private String status;

    public RentalListDTO(Integer id, String username, String bookname, LocalDateTime rentDate, LocalDateTime dueDate,
            String status) {
        this.id = id;
        this.username = username;
        this.bookname = bookname;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public LocalDateTime getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDateTime rentDate) {
        this.rentDate = rentDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
