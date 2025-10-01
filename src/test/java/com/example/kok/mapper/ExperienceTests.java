package com.example.kok.mapper;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.repository.CompanyDAO;
import com.example.kok.repository.ExperienceNoticeDAO;
import com.example.kok.service.CompanyService;
import com.example.kok.service.ExperienceNoticeService;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ExperienceTests {
    @Autowired
    private ConsoleExperienceListMapper consoleExperienceListMapper;

    @Test
    void testSelectById() {
        ConsoleExperienceNoticeRequestDTO dto = consoleExperienceListMapper.selectById(1L);

        System.out.println("id: " + dto.getId());
        System.out.println("제목: " + dto.getExperienceNoticeTitle());
        System.out.println("직군 ID: " + dto.getJobCategoryId());
        System.out.println("직군 이름: " + dto.getJobCategoryName());
    }

}
