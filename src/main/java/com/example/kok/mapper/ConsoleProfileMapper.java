package com.example.kok.mapper;

import com.example.kok.dto.ConsoleCompanyProfileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConsoleProfileMapper {
    // 기업 프로필
    ConsoleCompanyProfileDTO selectCompanyProfileByUserId(@Param("userId") Long userId);

    // 기업 프로필 수정
    void updateCompanyProfile(ConsoleCompanyProfileDTO companyProfileDTO);

    // 대표자명 수정
    void updateCeoName(ConsoleCompanyProfileDTO companyProfileDTO);

    // 산업 분야 수정
    void updateCompanySector(ConsoleCompanyProfileDTO companyProfileDTO);

    // 기업 규모 수정
    void updateCompanyScale(ConsoleCompanyProfileDTO companyProfileDTO);
}
