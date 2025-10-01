package com.example.kok.service;

import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    public void joinUser(UserDTO userDTO);
    public void joinCompany(UserDTO userDTO, MultipartFile multipartFiles) throws IOException;
    public int searchUserByEmail(String email);
    public void joinSnsUser(UserDTO userDTO);


    default UserVO toVO(UserDTO userDTO) {
       return UserVO.builder()
               .userEmail(userDTO.getUserEmail())
               .userName(userDTO.getUserName())
               .userPassword(userDTO.getUserPassword())
               .userPhone(userDTO.getUserPhone())
               .build();

    }
}
