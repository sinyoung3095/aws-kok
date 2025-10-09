package com.example.kok.repository;

import com.example.kok.dto.CompanyDTO;
import com.example.kok.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowDAO {
    private final FollowMapper followMapper;

    public List<CompanyDTO> selectPopularCompany(){
        return followMapper.selectPopularCompany();
    }
}
