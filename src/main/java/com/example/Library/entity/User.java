package com.example.Library.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid", unique = true, nullable = false)
    private String userid;

    @Column(name = "userpw", nullable = false)
    private String userpw;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "useyn")
    private String useyn;

    @Column(name = "role")
    private String role;

    @Column(name = "penalty_end_date")
    private Timestamp penaltyEndDate;

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
}