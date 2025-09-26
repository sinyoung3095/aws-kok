package com.example.kok.mapper;

<<<<<<< HEAD
=======
import com.example.kok.domain.UserVO;
>>>>>>> sub
import com.example.kok.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
<<<<<<< HEAD
//    이메일로 조회
    public Optional<UserDTO> selectByEmail(String userEmail);
=======
//    일반 회원 가입
    public void insertMember(UserDTO userDTO);
//    이메일로 회원 조회
    public int selectByEmail(String userEmail);
>>>>>>> sub
}
