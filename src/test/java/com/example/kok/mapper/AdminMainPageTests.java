package com.example.kok.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminMainPageTests {
    @Autowired
    private AdminMainPageMapper adminMainPageMapper;

//    Mapper
    @Test
    public void testMemberExperienceRequestAverage(){
        log.info("회원 신청 평균: {}", adminMainPageMapper.memberExperienceRequestAverage());
    }
}
