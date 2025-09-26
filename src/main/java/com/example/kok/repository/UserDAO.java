package com.example.kok.repository;

import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;
import com.example.kok.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;
    public Optional<UserDTO> findByEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }
//    일반 회원 가입
    public void saveUser(UserDTO userDTO) {
        userMapper.insertMember(userDTO);
    }
    public int findUserByEmail(String email) {
        return userMapper.selectCountByEmail(email);
    }
}
