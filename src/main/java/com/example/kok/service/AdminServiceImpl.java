package com.example.kok.service;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminExperienceDAO;
import com.example.kok.repository.AdminNoticeDAO;
import com.example.kok.repository.UserDAO;
import com.example.kok.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminServiceImpl implements AdminService {
    private final AdminNoticeDAO adminNoticeDAO;
    private final AdminExperienceDAO adminExperienceDAO;

//    지원 센터 - 목록
    public AdminNoticeCriteriaDTO supportList(int page, String keyword){
        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = new AdminNoticeCriteriaDTO();
        Criteria criteria = new Criteria(page, adminNoticeDAO.supportNoticeCount(keyword));
        List<AdminNoticeDTO> noticeList = adminNoticeDAO.supportNoticeList(criteria, keyword);

        criteria.setHasMore(noticeList.size() > criteria.getRowCount());
//        criteria.setHasPreviousPage(page > 1);
//        criteria.setHasNextPage(page < criteria.getRealEnd());

        //  11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            noticeList.remove(noticeList.size() - 1);
        }

        adminNoticeCriteriaDTO.setNoticeList(noticeList);
        adminNoticeCriteriaDTO.setNoticeCriteria(criteria);

        return adminNoticeCriteriaDTO;
    }

//    체험
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminExperienceListDTO getExperience(int page, String keyword) {
        AdminExperienceListDTO adminExperienceListDTO = new AdminExperienceListDTO();
        AdminExperienceListCriteria listCriteria = new AdminExperienceListCriteria(page, adminExperienceDAO.adminExperienceSearchCountAll(keyword));
        List<AdminExperienceDTO> experiences = adminExperienceDAO.adminExperienceAll(listCriteria, keyword);

        listCriteria.setHasMore(experiences.size() > listCriteria.getRowCount());
        listCriteria.setHasPreviousPage(page > 1);
        listCriteria.setHasNextPage(page < listCriteria.getRealEnd());

//        11개 가져왔으면, 마지막 1개 삭제
        if(listCriteria.isHasMore()){
            experiences.remove(experiences.size()-1);
        }

        adminExperienceListDTO.setListCriteria(listCriteria);
        adminExperienceListDTO.setExperienceList(experiences);

        return adminExperienceListDTO;
    }

//    체험 상세
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminExperienceDetailDTO getExperienceDetail(int page, Long id) {
        AdminExperienceDetailDTO adminExperienceDetailDTO = new AdminExperienceDetailDTO();

//        체험공고 상세정보
        AdminExperienceDTO adminExperienceDTO = adminExperienceDAO.selectAdminExperience(id);

//        신청자 내역
        AdminExperienceRequestCriteria requestCriteria = new AdminExperienceRequestCriteria(page, adminExperienceDAO.countRequestUser(id));
        List<UserRequestExperienceDTO> requestExperienceList = adminExperienceDAO.requestUser(requestCriteria, id);

        requestCriteria.setHasMore(requestExperienceList.size() > requestCriteria.getRowCount());
        requestCriteria.setHasPreviousPage(page > 1);
        requestCriteria.setHasNextPage(5 < requestExperienceList.size());

//        6개 가져왔으면, 마지막 1개 삭제
        if(requestCriteria.isHasMore()){
            requestExperienceList.remove(requestExperienceList.size()-1);
        }
        adminExperienceDetailDTO.setAdminExperienceRequestCriteria(requestCriteria);

//        체험 회원 평가
        AdminExperienceCriteria experienceCriteria = new AdminExperienceCriteria(page, adminExperienceDAO.countUserEvaluation(id));
        List<UserEvaluationDTO> userEvaluationList = adminExperienceDAO.userEvaluation(experienceCriteria, id);

        experienceCriteria.setHasMore(userEvaluationList.size() > experienceCriteria.getRowCount());
        experienceCriteria.setHasPreviousPage(page > 1);
        experienceCriteria.setHasNextPage(5 < userEvaluationList.size());

//        6개 가져왔으면, 마지막 1개 삭제
        if(experienceCriteria.isHasMore()){
            userEvaluationList.remove(userEvaluationList.size()-1);
        }
        adminExperienceDetailDTO.setAdminExperienceCriteria(experienceCriteria);

        adminExperienceDetailDTO.setExperience(adminExperienceDTO);
        adminExperienceDetailDTO.setUserRequestExperience(requestExperienceList);
        adminExperienceDetailDTO.setUserEvaluation(userEvaluationList);
        return adminExperienceDetailDTO;
    }

//    공지 등록
    @Override
    public void write(AdminNoticeDTO adminNoticeDTO) {
        AdminNoticeVO adminNoticeVO = toVO(adminNoticeDTO);
        adminNoticeDAO.insert(adminNoticeVO);
        adminNoticeDTO.setId(adminNoticeVO.getId());
    }

//    공지 상세
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<AdminNoticeDTO> getNotice (Long id) {
        Optional<AdminNoticeDTO> foundNotice = adminNoticeDAO.selectNotice(id);
        foundNotice.ifPresent((notice) -> {
            notice.setCreatedDateTime(DateUtils.getCreatedDate(notice.getCreatedDateTime()));
            notice.setUpdatedDateTime(DateUtils.getCreatedDate(notice.getUpdatedDateTime()));
        });
        return foundNotice;
    }

//    공지 목록
    @Override
    public AdminNoticeCriteriaDTO getList(int page) {
        AdminNoticeCriteriaDTO adminNoticeCriteriaDTO = new AdminNoticeCriteriaDTO();
        Criteria criteria = new Criteria(page, adminNoticeDAO.countAll());
        List<AdminNoticeDTO> noticeList = adminNoticeDAO.selectAll(criteria);
        noticeList.forEach((notice) -> {
            String relativeDate = DateUtils.getCreatedDate(notice.getCreatedDateTime());
            String[] dateTime = relativeDate.split(" ");
            notice.setRelativeDate(dateTime[0]);
        });

        criteria.setHasMore(noticeList.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        //  11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            noticeList.remove(noticeList.size() - 1);
        }

        adminNoticeCriteriaDTO.setNoticeList(noticeList);
        adminNoticeCriteriaDTO.setNoticeCriteria(criteria);

        return adminNoticeCriteriaDTO;
    }

//    공지 수정
    @Override
    public void update(AdminNoticeDTO adminNoticeDTO) {
        AdminNoticeVO adminNoticeVO = toVO(adminNoticeDTO);
        adminNoticeDAO.updateNotice(adminNoticeVO);
        adminNoticeDTO.setId(adminNoticeVO.getId());
    }

//    공지 삭제
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        adminNoticeDAO.deleteNotice(id);
    }

}
