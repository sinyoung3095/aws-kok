package com.example.kok.mapper;

import com.example.kok.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
//    이메일로 조회
    public Optional<UserDTO> selectByEmail(String userEmail);
}
