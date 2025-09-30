package com.example.kok.service;

import com.example.kok.domain.MemberVO;
import com.example.kok.domain.UserVO;
import com.example.kok.dto.UserDTO;
import com.example.kok.repository.MemberAlarmSettingDAO;
import com.example.kok.repository.MemberDAO;
import com.example.kok.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final MemberDAO memberDAO;
    private final UserDAO  userDAO;
    private final PasswordEncoder passwordEncoder;
    private final MemberAlarmSettingDAO memberAlarmSettingDAO;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinUser(UserDTO userDTO) {

            userDTO.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));
            userDAO.saveUser(userDTO);
            memberDAO.saveMember(MemberVO.builder().userId(userDTO.getId()).memberProvider(userDTO.getMemberProvider()).build());
            memberAlarmSettingDAO.save(userDTO.getId());
    }

    @Override
    public int searchUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void joinSnsUser(UserDTO userDTO) {
        userDAO.saveSnsUser(userDTO);
        memberDAO.saveMember(MemberVO.builder().userId(userDTO.getId()).memberProvider(userDTO.getMemberProvider()).build());
        memberAlarmSettingDAO.save(userDTO.getId());
    }
}
