package com.example.kok.repository;

import com.example.kok.dto.UserDTO;
import com.example.kok.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;

//    이메일로 조회
    public Optional<UserDTO> findByEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }
}
