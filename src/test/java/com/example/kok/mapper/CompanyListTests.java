package com.example.kok.mapper;

import com.example.kok.repository.CompanyDAO;
import com.example.kok.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CompanyListTests {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDAO companyDAO;
}
