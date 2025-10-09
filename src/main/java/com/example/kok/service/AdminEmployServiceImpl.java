package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.*;
import com.example.kok.repository.AdminEmployDAO;
import com.example.kok.util.AdminExperienceCriteria;
import com.example.kok.util.AdminExperienceListCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminEmployServiceImpl implements AdminEmployService {
    private final AdminEmployDAO adminEmployDAO;

    @Override
    public AdminInternNoticeListCriteriaDTO getList(int page, String keyword) {
        AdminInternNoticeListCriteriaDTO adminInternNoticeList = new AdminInternNoticeListCriteriaDTO();
        AdminExperienceListCriteria listCriteria = new AdminExperienceListCriteria(page, adminEmployDAO.countAllEmploy(keyword));
        List<AdminInternNoticeListDTO> internNotices = adminEmployDAO.getEmployList(listCriteria, keyword);

        listCriteria.setHasMore(internNotices.size() > listCriteria.getRowCount());
        listCriteria.setHasPreviousPage(page > 1);
        listCriteria.setHasNextPage(page < listCriteria.getRealEnd());

        log.info("이전 페이지 버튼: {}", listCriteria.isHasPreviousPage());
        log.info("다음 페이지 버튼: {}", listCriteria.isHasNextPage());
//        11개 가져왔으면, 마지막 1개 삭제
        if(listCriteria.isHasMore()){
            internNotices.remove(internNotices.size()-1);
        }

        adminInternNoticeList.setInternNoticeList(internNotices);
        adminInternNoticeList.setInternListCriteria(listCriteria);
        return adminInternNoticeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AdminInternNoticeDetailCriteriaDTO getDetail(int page, Long id) {
        AdminInternNoticeDetailCriteriaDTO adminInternNoticeDetail = new AdminInternNoticeDetailCriteriaDTO();

//        인턴공고 - 상세정보
        AdminInternNoticeDetailDTO adminInternNoticeDetailDTO = adminEmployDAO.selectEmploy(id).orElseThrow(PostNotFoundException::new);

//        인턴공고 - 신청자 정보
        AdminExperienceCriteria requestCriteria = new AdminExperienceCriteria(page, adminEmployDAO.countRequest(id));
        List<AdminInternNoticeDetailRequestDTO> internRequests = adminEmployDAO.selectRequest(requestCriteria, id);

        requestCriteria.setHasMore(internRequests.size() > requestCriteria.getRowCount());
        requestCriteria.setHasPreviousPage(page > 1);
        requestCriteria.setHasNextPage(5 < internRequests.size());

        log.info("이전 페이지 버튼: {}", requestCriteria.isHasPreviousPage());
        log.info("다음 페이지 버튼: {}", requestCriteria.isHasNextPage());
        log.info("신청자 내역 총 개수: {}", adminEmployDAO.countRequest(id));
//        6개 가져왔으면, 마지막 1개 삭제
        if(requestCriteria.isHasMore()){
            internRequests.remove(internRequests.size()-1);
        }

        adminInternNoticeDetail.setInternNoticeDetail(adminInternNoticeDetailDTO);
        adminInternNoticeDetail.setInternNoticeDetailRequests(internRequests);
        adminInternNoticeDetail.setInternDetailCriteria(requestCriteria);
        return adminInternNoticeDetail;
    }
}














