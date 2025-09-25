package com.example.kok.mapper;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.util.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ExperienceTests {
    @Autowired
    private ExperienceNoticeMapper experienceNoticeMapper;

    @Test
    public void testSelectAllExperienceNotice(){
        Criteria criteria = new Criteria(1, 1);
        System.out.println("######################################");
        System.out.println(criteria);
        System.out.println(experienceNoticeMapper.selectAllExperienceNotice(criteria));
    }
}
