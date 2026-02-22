package com.example.Library.repository;

import com.example.Library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Object findByUserid(String userid);
    // 요게 있어야 스프링이 창고 관리인으로 인정해 줍니다!
}