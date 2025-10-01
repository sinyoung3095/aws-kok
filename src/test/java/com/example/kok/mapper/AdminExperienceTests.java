package com.example.kok.mapper;

import com.example.kok.dto.AdminExperienceDTO;
import com.example.kok.dto.UserEvaluationDTO;
import com.example.kok.dto.UserRequestExperienceDTO;
import com.example.kok.repository.AdminExperienceDAO;
import com.example.kok.service.AdminService;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class AdminExperienceTests {
    @Autowired
    private AdminExperienceMapper adminExperienceMapper;
    @Autowired
    private AdminExperienceDAO adminExperienceDAO;
    @Autowired
    private AdminService adminService;


//    Mapper
    @Test
    public void testAdminExperienceAll() {
        Search search = new Search();
        search.setKeyword(null);
        Criteria criteria = new Criteria(3, adminExperienceMapper.selectAdminExperienceSearchCountAll(search));
        adminExperienceMapper.selectAdminExperienceAll(criteria, search).stream().map(AdminExperienceDTO::toString).forEach(log::info);
        log.info("{}", criteria);
    }

    @Test
    public void testSelectAdminExperienceCountAll() {
        Search search = new Search();
        search.setKeyword(null);
        log.info("전체 개수: {}", adminExperienceMapper.selectAdminExperienceSearchCountAll(search));
    }

    @Test
    public void testSelectAdminExperienceById() {
        Long id = 55L;
        log.info("55번 게시글: {}", adminExperienceMapper.selectAdminExperienceById(id));
    }

    @Test
    public void testSelectRequestUser() {
        Long id = 54L;
        AdminExperienceCriteria adminExperienceCriteria = new AdminExperienceCriteria(1, adminExperienceMapper.countRequestUser(id));
        adminExperienceMapper.selectRequestUser(adminExperienceCriteria, id).stream().map(UserRequestExperienceDTO::toString).forEach(log::info);
        log.info("adminExperienceCriteria: {}", adminExperienceCriteria);
        log.info("신청자 수: {}", adminExperienceMapper.countRequestUser(id));
    }

    @Test
    public void testCountRequestUser(){
        Long id = 54L;
        log.info("신청자 수: {}", adminExperienceMapper.countRequestUser(id));
    }

    @Test
    public void testSelectUserEvaluation() {
        Long id = 56L;
        AdminExperienceCriteria adminExperienceCriteria = new AdminExperienceCriteria(2, adminExperienceMapper.countUserEvaluation(id));
        adminExperienceMapper.selectUserEvaluation(adminExperienceCriteria, id).stream().map(UserEvaluationDTO::toString).forEach(log::info);
        log.info("adminExperienceCriteria: {}", adminExperienceCriteria);
        log.info("평가자 수: {}", adminExperienceMapper.countUserEvaluation(id));
    }

    @Test
    public void testCountUserEvaluation() {
        Long id = 56L;
        log.info("평가자 수: {}", adminExperienceMapper.countUserEvaluation(id));
    }

//    DAO
    @Test
    public void testAdminExperienceAllDAO() {
        Search search = new Search();
        search.setKeyword(null);
        Criteria criteria = new Criteria(3, adminExperienceDAO.adminExperienceSearchCountAll(search));
        adminExperienceDAO.adminExperienceAll(criteria, search).stream().map(AdminExperienceDTO::toString).forEach(log::info);
        log.info("{}", criteria);
    }

    @Test
    public void testAdminExperienceSearchCountAll() {
        Search search = new Search();
        search.setKeyword(null);
        log.info("전체 개수{}", adminExperienceDAO.adminExperienceSearchCountAll(search));
    }

    @Test
    public void testSelectAdminExperience() {
        Long id = 55L;
        log.info("55번 게시글: {}", adminExperienceDAO.selectAdminExperience(id));
    }


//    Service
    @Test
    public void testGetExperienceNotice() {
        Search search = new Search();
        search.setKeyword(null);
        log.info("페이지 1: {}", adminService.getExperienceList(1, search));
    }

//    @Test
//    public void testGetExperience() {
//        Long id = 55L;
//        log.info("55번 게시글: {}", adminService.getExperience(id));
//    }
}
