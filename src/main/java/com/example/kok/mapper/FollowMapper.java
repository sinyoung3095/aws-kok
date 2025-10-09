package com.example.kok.mapper;

import com.example.kok.dto.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper{
    public List<CompanyDTO> selectPopularCompany();
}
