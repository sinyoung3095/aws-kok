package com.example.kok.repository;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.mapper.ExperienceNoticeMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceNoticeDAO {
    private final ExperienceNoticeMapper experienceNoticeMapper;

    public List<ExperienceNoticeDTO> findAll(Criteria criteria) {
        return experienceNoticeMapper.selectAllExperienceNotice(criteria);
    }

//    개수 조회
    public int findCountAll(){
        return experienceNoticeMapper.selectCountAll();
    }
}
