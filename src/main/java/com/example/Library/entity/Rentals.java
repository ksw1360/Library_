package com.example.Library.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
@Table(name = "rentals")
public class Rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 원시타입 long보다 래퍼클래스 Long을 쓰는 것이 JPA 표준에 더 적합합니다.

    @Column(name = "user_id", nullable = false)
    private Integer userId; // 카멜케이스 적용, unique 제거

    @Column(name = "book_id", nullable = false)
    private Integer bookId; // 카멜케이스 적용, unique 제거

    @Column(name = "rent_date")
    private Timestamp rentDate; // birth_date 오타 수정

    @Column(name = "due_date", nullable = false)
    private Timestamp dueDate; // unique 제거

    @Column(name = "return_date")
    private Timestamp returnDate;

    @Column(name = "status")
    private String status;

    @Column(name = "overdue_days")
    private Integer overdueDays;

    @Column(name = "overdue_fee")
    private Integer overdueFee;

    @Column(name = "create_date", updatable = false) // 생성일은 업데이트 시 변경되지 않도록 막음
    private Timestamp createDate;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "modify_date")
    private Timestamp modifyDate;

    @Column(name = "modify_user")
    private String modifyUser;

    @PrePersist
    public void prePersist() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        if (this.createDate == null) {
            this.createDate = now;
        }
        if (this.modifyDate == null) {
            this.modifyDate = now;
        }
        if (this.status == null) {
            this.status = "RENTED"; // 대출 시 기본 상태값 세팅 (예시)
        }
    }

    // 데이터가 수정(Update)될 때마다 modifyDate를 자동으로 갱신해 주는 유용한 어노테이션입니다.
    @PreUpdate
    public void preUpdate() {
        this.modifyDate = Timestamp.valueOf(LocalDateTime.now());
    }
}