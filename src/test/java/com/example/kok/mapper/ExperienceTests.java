package com.example.kok.mapper;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.SaveExperienceNoticeDTO;
import com.example.kok.repository.CompanyDAO;
import com.example.kok.repository.ExperienceNoticeDAO;
import com.example.kok.repository.SaveExperienceNoticeDAO;
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
<<<<<<< HEAD
    private ExperienceNoticeMapper experienceNoticeMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private ExperienceNoticeService experienceNoticeService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ExperienceNoticeDAO experienceNoticeDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private SaveExperienceNoticeDAO saveExperienceNoticeDAO;
=======
    private ConsoleExperienceListMapper consoleExperienceListMapper;
>>>>>>> ex/apply

    @Test
    void testSelectById() {
        ConsoleExperienceNoticeRequestDTO dto = consoleExperienceListMapper.selectById(1L);

        System.out.println("id: " + dto.getId());
        System.out.println("제목: " + dto.getExperienceNoticeTitle());
        System.out.println("직군 ID: " + dto.getJobCategoryId());
        System.out.println("직군 이름: " + dto.getJobCategoryName());
    }

<<<<<<< HEAD
    @Test
    public void testSelectCompany(){
        System.out.println(companyMapper.selectCompanyById(8L));
        System.out.println(experienceNoticeMapper.selectById(21L));
    }

    @Test
    public void testSelectDAOService(){
        System.out.println("다오 회사: "+companyDAO.findCompanyById(8L));
        System.out.println("다오 공고: "+experienceNoticeDAO.findById(21L));
        CompanyDTO companys=companyService.findCompanyById(8L);
        System.out.println(companys);
        System.out.println("서비스 공고: "+experienceNoticeService.findNoticeById(21L));
    }

    @Test
    public void testSaveExperienceNotice(){
        SaveExperienceNoticeDTO saveExp=new SaveExperienceNoticeDTO();
        saveExp.setExperienceNoticeId(2L);
        saveExp.setMemberId(6L);
        experienceNoticeService.saveExp(saveExp);

        System.out.println(saveExp);

//        SaveExperienceNoticeDTO deleteExp=new SaveExperienceNoticeDTO();
//        deleteExp.setExperienceNoticeId(2L);
//        deleteExp.setMemberId(6L);
//        experienceNoticeService.deleteExp(deleteExp);
//
//        System.out.println(deleteExp);
    }
=======
>>>>>>> ex/apply
}
