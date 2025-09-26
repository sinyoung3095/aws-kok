package com.example.kok.mapper;

import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
//    이메일로 조회
    public Optional<UserDTO> selectByEmail(String userEmail);
//    일반 회원 가입
    public void insertMember(UserDTO userDTO);
//    이메일로 회원 수 조회
    public int selectCountByEmail(String userEmail);
}
