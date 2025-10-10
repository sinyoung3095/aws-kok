package com.example.kok.mapper;

import com.example.kok.repository.ExperienceNoticeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ExperienceNoticeMapperTests {
    @Autowired
    ExperienceNoticeMapper experienceNoticeMapper;
    @Autowired
    ExperienceNoticeDAO experienceNoticeDAO;

    @Test
    public void testFindCompanyExperienceNotice() {
        log.info("testFindCompanyExperienceNotice: {}", experienceNoticeMapper.selectListById(6L));
    }

    @Test
    public void daoTestFindCompanyExperienceNotice() {
        log.info("result: {}", experienceNoticeDAO.selectListById(6L));
    }
}
