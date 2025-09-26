package com.example.kok.repository;

<<<<<<< HEAD
=======
import com.example.kok.domain.UserVO;
>>>>>>> sub
import com.example.kok.dto.UserDTO;
import com.example.kok.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> sub
@Repository
@RequiredArgsConstructor
public class UserDAO {
    private final UserMapper userMapper;
<<<<<<< HEAD

//    이메일로 조회
    public Optional<UserDTO> findByEmail(String userEmail) {
        return userMapper.selectByEmail(userEmail);
    }
=======
//    일반 회원 가입
    public void saveUser(UserDTO userDTO) {
        userMapper.insertMember(userDTO);
    }
    public int findUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

>>>>>>> sub
}
