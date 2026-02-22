package com.example.Library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.entity.User;
import com.example.Library.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // 이름 맞춤

    // 회원가입
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // 아이디로 찾기 (PK)
    public Object getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 로그인용 아이디로 찾기 (String ID)
    public Object getUserByUserId(String userid) {
        return userRepository.findByUserid(userid);
    }

    // 전체 회원 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}