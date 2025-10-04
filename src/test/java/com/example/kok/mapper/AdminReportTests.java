package com.example.kok.mapper;

import com.example.kok.dto.AdminReportDTO;
import com.example.kok.util.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminReportTests {
    @Autowired
    private AdminReportMapper adminReportMapper;

//    Mapper
    @Test
    public void testSelectReportAll() {
        Criteria criteria = new Criteria(4, adminReportMapper.selectReportCount());
        adminReportMapper.selectReportAll(criteria).stream().map(AdminReportDTO::toString).forEach(log::info);
        log.info("{}", criteria);
    }

    @Test
    public void testSelectReportCount() {
        log.info("전체 개수: {}", adminReportMapper.selectReportCount());
    }

    @Test
    public void testSelectAdminExperienceById() {
        Long id = 31L;
        log.info("31번 게시글: {}", adminReportMapper.selectReportDetail(id));
    }

    @Test
    public void testDeleteReportPost(){
        Long id = 36L;
        log.info("삭제 전 전체 개수: {}", adminReportMapper.selectReportCount());
        adminReportMapper.deleteReportPost(id);
        log.info("삭제 후 전체 개수: {}", adminReportMapper.selectReportCount());
    }


//    DAO




//    Service

}
