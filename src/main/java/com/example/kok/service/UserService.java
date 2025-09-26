package com.example.kok.service;

import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;

public interface UserService {
    public void joinUser(UserDTO userDTO);
    public int searchUserByEmail(String email);

    default UserVO toVO(UserDTO userDTO) {
       return UserVO.builder()
               .userEmail(userDTO.getUserEmail())
               .userName(userDTO.getUserName())
               .userPassword(userDTO.getUserPassword())
               .userPhone(userDTO.getUserPhone())
               .build();

    }
}
