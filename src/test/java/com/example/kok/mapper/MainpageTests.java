package com.example.kok.mapper;

import com.example.kok.dto.UserDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.repository.MemberAlarmSettingDAO;
import com.example.kok.repository.MemberDAO;
import com.example.kok.repository.RequestExperienceDAO;
import com.example.kok.repository.UserDAO;
import com.example.kok.service.MemberService;
import com.example.kok.util.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MainpageTests {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private RequestExperienceDAO requestExperienceDAO;
    @Autowired
    private MemberAlarmSettingDAO  memberAlarmSettingDAO;
    @Autowired
    private UserDAO userDAO;

    @Test
    public void mainpageTest(){
        long id = 2;
    }
    @Test
    public void updateTest(){
        String keyword = "member_post_like_alarm";
        Long id = 2L;
        memberAlarmSettingDAO.updateByKeywordToActive(id, keyword);
    }
    @Test
    public void updateTest2(){
        String keyword = "member_post_like_alarm";
        Long id = 2L;
        memberAlarmSettingDAO.updateByKeywordToInactive(id, keyword);
    }
    @Test
    public void getUserDTO(){
        String userDTO=userDAO.findByEmail("company@gmail.com").toString();
        log.info("userDTO={}",userDTO);
    }

}
