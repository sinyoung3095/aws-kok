package com.example.kok.service;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminExperienceDAO;
import com.example.kok.repository.AdminNoticeDAO;
import com.example.kok.repository.UserDAO;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import com.example.kok.util.Search;
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

//    체험 목록
//    @Override
//    public AdminExperienceDetailDTO getExperienceList(int page, Search search) {
//        AdminExperienceDetailDTO adminExperienceDetailDTO = new AdminExperienceDetailDTO();
//        Criteria criteria = new Criteria(page, adminExperienceDAO.adminExperienceSearchCountAll(search));
//        List<AdminExperienceDTO> experiences = adminExperienceDAO.adminExperienceAll(criteria, search);
//
//        criteria.setHasMore(experiences.size() > criteria.getRowCount());
//
////        11개 가져왔으면, 마지막 1개 삭제
//        if(criteria.isHasMore()){
//            experiences.remove(experiences.size()-1);
//        }
//
//        adminExperienceDetailDTO.setExperienceList(experiences);
//        return adminExperienceDetailDTO;
//    }

//    체험 상세
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminExperienceDetailDTO getExperience(int page, Search search, Long id) {
        AdminExperienceDetailDTO adminExperienceDetailDTO = new AdminExperienceDetailDTO();
        Criteria listCriteria = new Criteria(page, adminExperienceDAO.adminExperienceSearchCountAll(search));
        List<AdminExperienceDTO> experiences = adminExperienceDAO.adminExperienceAll(listCriteria, search);

        listCriteria.setHasMore(experiences.size() > listCriteria.getRowCount());

//        11개 가져왔으면, 마지막 1개 삭제
        if(listCriteria.isHasMore()){
            experiences.remove(experiences.size()-1);
        }

        Optional<AdminExperienceDTO> adminExperienceDTO = adminExperienceDAO.selectAdminExperience(id);
        AdminExperienceCriteria requestCriteria = new AdminExperienceCriteria(page, adminExperienceDAO.countRequestUser(id));
        List<UserRequestExperienceDTO> requestExperienceList = adminExperienceDAO.requestUser(requestCriteria, id);
        AdminExperienceCriteria criteria = new AdminExperienceCriteria(page, adminExperienceDAO.countUserEvaluation(id));
        List<UserEvaluationDTO> userEvaluationList = adminExperienceDAO.userEvaluation(criteria, id);

        adminExperienceDetailDTO.setExperienceList(experiences);
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
        foundNotice.ifPresent(notice -> {
            notice.setCreatedDateTime(DateUtils.getCreatedDate(notice.getCreatedDateTime()));
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
