package com.example.kok.repository;

import com.example.kok.dto.AdminExperienceDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.mapper.AdminExperienceMapper;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminExperienceDAO {
    private final AdminExperienceMapper adminExperienceMapper;

//    전체(목록)
    public List<AdminExperienceDTO> adminExperienceAll(Criteria criteria, Search search) {
        return adminExperienceMapper.selectAdminExperienceAll(criteria, search);
    }

//    검색 개수
    public int adminExperienceSearchCountAll(Search search) {
        return adminExperienceMapper.selectAdminExperienceSearchCountAll(search);
    }

//    전체 개수
    public int adminExperienceCountAll() {
        return adminExperienceMapper.selectAdminExperienceCountAll();
    }
    
//    상세
    public ExperienceNoticeDTO selectAdminExperience(Long id) {
        return adminExperienceMapper.selectAdminExperienceById(id);
    }
}
