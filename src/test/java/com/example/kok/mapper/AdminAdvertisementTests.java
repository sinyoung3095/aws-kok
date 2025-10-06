package com.example.kok.mapper;

import com.example.kok.dto.AdminAdvertisementDTO;
import com.example.kok.repository.AdminAdvertisementDAO;
import com.example.kok.service.AdminAdvertisementService;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AdminAdvertisementTests {
    @Autowired
    private AdminAdvertisementMapper adminAdvertisementMapper;
    @Autowired
    private AdminAdvertisementDAO adminAdvertisementDAO;
    @Autowired
    private AdminAdvertisementService adminAdvertisementService;

//    Mapper
    @Test
    public void testSelectAllAdvertisementList(){
        Search search = new Search();
        search.setKeyword("");
        search.setCategory("");
        Criteria criteria = new Criteria(1, adminAdvertisementMapper.countAllAdvertisement(search));
        log.info("##################");
        adminAdvertisementMapper.selectAllAdvertisementList(criteria, search).stream().map(AdminAdvertisementDTO::toString).forEach(log::info);
        log.info("{}", criteria);
        log.info("{}", search);
    }

    @Test
    public void testCountAllAdvertisement(){
        Search search = new Search();
        search.setKeyword("");
        search.setCategory("");
        log.info("전체 개수: {}", adminAdvertisementMapper.countAllAdvertisement(search));
    }

    @Test
    public void testSelectAdvertisementById(){
        Long id = 5L;
        log.info("5번 게시글: {}", adminAdvertisementMapper.selectAdvertisementById(id));
    }

    @Test
    public void testAcceptAdvertisementById(){
        Long id = 41L;
        log.info("41번 승인 전: {}", adminAdvertisementMapper.selectAdvertisementById(id).get().getAdvertisementRequestStatus());
        adminAdvertisementMapper.acceptAdvertisementById(id);
        log.info("41번 승인 후: {}", adminAdvertisementMapper.selectAdvertisementById(id).get().getAdvertisementRequestStatus());
    }

    @Test
    public void testRejectAdvertisementById(){
        Long id = 41L;
        log.info("41번 거절 전: {}", adminAdvertisementMapper.selectAdvertisementById(id).get().getAdvertisementRequestStatus());
        adminAdvertisementMapper.rejectAdvertisementById(id);
        log.info("41번 거절 후: {}", adminAdvertisementMapper.selectAdvertisementById(id).get().getAdvertisementRequestStatus());
    }

//    DAO
}
