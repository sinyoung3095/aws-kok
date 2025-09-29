package com.example.kok.service;

import com.example.kok.dto.AdminNoticeCriteriaDTO;
import com.example.kok.dto.AdminNoticeDTO;
import com.example.kok.repository.AdminNoticeDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
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
public class AdminNoticeServiceImpl implements AdminNoticeService {
    private final AdminNoticeDAO adminNoticeDAO;

//    등록
    @Override
    public void write(AdminNoticeDTO adminNoticeDTO) {
        adminNoticeDAO.insert(toVO(adminNoticeDTO));
    }

//    상세
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<AdminNoticeDTO> getNotice (Long id) {
        Optional<AdminNoticeDTO> foundNotice = adminNoticeDAO.selectNotice(id);
        foundNotice.ifPresent(notice -> {
            notice.setCreatedDateTime(DateUtils.getCreatedDate(notice.getCreatedDateTime()));
        });
        return foundNotice;
    }

//    목록
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

        //  11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            noticeList.remove(noticeList.size() - 1);
        }

        adminNoticeCriteriaDTO.setNoticeList(noticeList);
        adminNoticeCriteriaDTO.setCriteria(criteria);

        return adminNoticeCriteriaDTO;
    }

//    수정
    @Override
    public void update(AdminNoticeDTO adminNoticeDTO) {
        adminNoticeDAO.updateNotice(toVO(adminNoticeDTO));
    }

//    삭제
    @Override
    public void delete(Long id) {
        adminNoticeDAO.deleteNotice(id);
    }

}
