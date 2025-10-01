package com.example.kok.mapper;

import com.example.kok.dto.AdminExperienceDTO;
import com.example.kok.dto.UserEvaluationDTO;
import com.example.kok.dto.UserRequestExperienceDTO;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminExperienceMapper {
//    전체(목록)
    public List<AdminExperienceDTO> selectAdminExperienceAll(@Param("criteria") Criteria criteria, @Param("search") Search search);

//    검색 개수
    public int selectAdminExperienceSearchCountAll(@Param("search") Search search);

//    체험공고 - 상세
    public Optional<AdminExperienceDTO> selectAdminExperienceById(Long id);

//    체험공고 - 신청자 내역
    public List<UserRequestExperienceDTO> selectRequestUser(@Param("criteria") AdminExperienceCriteria criteria, @Param("id") Long id);
//    체험공고 - 신청자 내역 개수
    public int countRequestUser(Long id);

//    체험공고 - 체험 회원 평가
    public List<UserEvaluationDTO> selectUserEvaluation(@Param("criteria") AdminExperienceCriteria criteria, @Param("id") Long id);
//    체험공고 - 체험 회원 평가 개수
    public int countUserEvaluation(Long id);
}
