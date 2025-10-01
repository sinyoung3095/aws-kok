package com.example.kok.service;

import com.example.kok.domain.AdminNoticeVO;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminExperienceDAO;
import com.example.kok.repository.AdminNoticeDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Override
    public AdminExperienceCriteriaDTO getExperienceNotice(int page, Search search) {
        AdminExperienceCriteriaDTO adminExperienceCriteriaDTO = new AdminExperienceCriteriaDTO();
        Criteria criteria = new Criteria(page, adminExperienceDAO.adminExperienceCountAll());
        List<AdminExperienceDTO> experiences = adminExperienceDAO.adminExperienceAll(criteria, search);

        criteria.setHasMore(experiences.size() > criteria.getRowCount());

//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            experiences.remove(experiences.size()-1);
        }

        adminExperienceCriteriaDTO.setExperienceList(experiences);
        return adminExperienceCriteriaDTO;
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
