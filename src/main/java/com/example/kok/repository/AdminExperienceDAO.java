package com.example.kok.repository;

import com.example.kok.dto.AdminExperienceDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.UserEvaluationDTO;
import com.example.kok.dto.UserRequestExperienceDTO;
import com.example.kok.mapper.AdminExperienceMapper;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

//    체험공고 - 상세
    public Optional<AdminExperienceDTO> selectAdminExperience(Long id) {
        return adminExperienceMapper.selectAdminExperienceById(id);
    }

//    체험공고 - 신청자 내역
    public List<UserRequestExperienceDTO> requestUser(AdminExperienceCriteria criteria, Long id) {
        return adminExperienceMapper.selectRequestUser(criteria, id);
    }
//    체험공고 - 신청자 내역 개수
    public int countRequestUser(Long id) {
        return adminExperienceMapper.countRequestUser(id);
    }

//    체험공고 - 체험 회원 평가
    public List<UserEvaluationDTO> userEvaluation(AdminExperienceCriteria criteria, Long id) {
        return adminExperienceMapper.selectUserEvaluation(criteria, id);
    }
//    체험공고 - 체험 회원 평가 개수
    public int countUserEvaluation(Long id) {
        return adminExperienceMapper.countUserEvaluation(id);
    }
}
