package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;
import com.example.kok.repository.MemberDAO;
import com.example.kok.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final MemberDAO memberDAO;
    private final UserDAO  userDAO;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void joinUser(UserDTO userDTO) {

            userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
            userDAO.saveUser(userDTO);
            memberDAO.saveMember(MemberVO.builder().userId(userDTO.getId()).build());
    }

    @Override
    public int searchUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public void joinSnsUser(UserDTO userDTO) {
        userDAO.saveSnsUser(userDTO);
        memberDAO.saveMember(MemberVO.builder().userId(userDTO.getId()).build());
    }
}
