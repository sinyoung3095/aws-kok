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
        Criteria criteria = new Criteria(1, 100);
        log.info(criteria.toString());
        experienceNoticeMapper.selectAllExperienceNotice(criteria).stream().map(ExperienceNoticeDTO::toString).forEach(log::info);
    }
}
