package com.example.kok.mapper;

import com.example.kok.repository.RequestExperienceDAO;
import com.example.kok.service.RequestExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RequestExperienceMapperTests {

    @Autowired
    private RequestExperienceMapper requestExperienceMapper;
    @Autowired
    private RequestExperienceDAO requestExperienceDAO;

    @Test
    public void requestExperienceMapperTest(){
        requestExperienceMapper.selectRequestById(1L);
        log.info("requestExperienceMapperTest: {}", requestExperienceMapper.selectRequestById(1L));

    }

    @Test
    public void requestExperienceDAOTest(){
        requestExperienceDAO.selectAllRequestById(1L);
        log.info("requestExperienceMapperTest: {}", requestExperienceDAO.selectAllRequestById(1L));

    }
}
