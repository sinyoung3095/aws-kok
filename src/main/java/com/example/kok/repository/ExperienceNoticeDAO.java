package com.example.kok.repository;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.mapper.ExperienceNoticeMapper;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExperienceNoticeDAO {
    private final ExperienceNoticeMapper experienceNoticeMapper;

//    목록 조회
    public List<ExperienceNoticeDTO> findAll(Criteria criteria, Search search) {
        System.out.println("다오에 들어왔어용");
        return experienceNoticeMapper.selectAllExperienceNotice(criteria, search);
    }

//    개수 조회
    public int findCountAll(){
        return experienceNoticeMapper.selectCountAll();
    }
}
