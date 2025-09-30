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
//    이메일로 조회
    public int findUserByEmail(String email) {
        return userMapper.selectCountByEmail(email);
    }
//    sns 이메일로 조회
    public Optional<UserDTO> findUserBySnsEmail(String snsEmail) {
        return userMapper.selectBySnsEmail(snsEmail);
    }
//    sns 이메일로 회원가입
    public void saveSnsUser(UserDTO userDTO) {
        userMapper.insertSnsMember(userDTO);
    }
}
