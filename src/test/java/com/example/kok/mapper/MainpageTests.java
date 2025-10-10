package com.example.kok.mapper;

import com.example.kok.dto.UserMemberDTO;
import com.example.kok.repository.MemberDAO;
import com.example.kok.repository.RequestExperienceDAO;
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

    @Test
    public void mainpageTest(){
        long id = 2;
        log.info(requestExperienceDAO.selectAllRequestById(id).toString());
    }

}
