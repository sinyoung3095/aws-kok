package com.example.kok.mapper;

import com.example.kok.dto.AdminMainPageDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMainPageMapper {
//    회원 - 평균 체험 지원 수
    public AdminMainPageDTO memberExperienceRequestAverage();
}
