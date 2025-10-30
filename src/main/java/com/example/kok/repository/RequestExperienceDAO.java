package com.example.kok.repository;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.mapper.RequestExperienceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestExperienceDAO {
    private final RequestExperienceMapper requestExperienceMapper;

//    지원서 추가
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO){
        requestExperienceMapper.insertRequest(requestExperienceDTO);
    }

//    회원별 지원서 조회
    public List<RequestExperienceDTO> selectAllRequestById(Long id){
        return requestExperienceMapper.selectRequestById(id);
    }
    //    지원 내역 목록 조회
    public List<RequestExperienceDTO> selectAllRequestByUserId(Long id,Long experienceId){
        log.info("selectAllRequestByUserId id={}",requestExperienceMapper.selectRequestByUserId(id,experienceId).toString());
        return requestExperienceMapper.selectRequestByUserId(id,experienceId);
    }
//    지원 여부 판별
    public boolean isRequested(RequestExperienceDTO requestExperienceDTO){
        Long experienceNoticeId=requestExperienceDTO.getExperienceNoticeId();
        Long memberId = requestExperienceDTO.getMemberId();
        System.out.println("다오 experienceNoticeId:"+experienceNoticeId+" memberId:"+memberId);
        if(requestExperienceMapper.countRequest(experienceNoticeId, memberId)>0){
            return true;
        } else{
            return false;
        }
    }
}
