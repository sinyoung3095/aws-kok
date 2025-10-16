package com.example.kok.service;

import com.example.kok.dto.ConsoleInternListCriteriaDTO;
import com.example.kok.dto.ConsoleInternListDTO;
import com.example.kok.dto.ConsoleInternListRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.repository.ConsoleInternNoticeDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleInternListServiceImpl implements ConsoleInternListService {
    private final ConsoleInternNoticeDAO consoleInternDAO;

    @Override
    public ConsoleInternListCriteriaDTO getList(Long companyId, int page, Status status, String keyword) {
        ConsoleInternListCriteriaDTO consoleInternListCriteriaDTO = new ConsoleInternListCriteriaDTO();

        int totalCount = consoleInternDAO.findCountByCompany(companyId, status, keyword);
        int activeCount = consoleInternDAO.findActiveCountByCompany(companyId, Status.ACTIVE, keyword);
        int requestCount = consoleInternDAO.findRequestCountByCompany(companyId);
        int activeRequestCount = consoleInternDAO.findRequestActiveCountByCompany(companyId);

        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleInternListDTO> notices = consoleInternDAO.findAllByCompany(companyId, criteria, status, keyword);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleInternListCriteriaDTO.setInternLists(notices);
        consoleInternListCriteriaDTO.setCriteria(criteria);
        consoleInternListCriteriaDTO.setTotalCount(totalCount);
        consoleInternListCriteriaDTO.setActiveTotalCount(activeCount);
        consoleInternListCriteriaDTO.setTotalRequestCount(requestCount);
        consoleInternListCriteriaDTO.setActiveRequestCount(activeRequestCount);

        return consoleInternListCriteriaDTO;
    }

    @Override
    public void updateListStatus(Long noticeId, Status status) {
        consoleInternDAO.updateNoticeStatus(noticeId, status);
    }

//    공고 상세
    @Override
    public ConsoleInternListRequestDTO getDetail(Long id) {
        return consoleInternDAO.findDetailById(id);
    }

    @Override
    @Transactional
    public void registerNotice(ConsoleInternListRequestDTO noticeRequestDTO) {
//        공고 등록
        consoleInternDAO.createNotice(noticeRequestDTO);

//        직군 등록
        consoleInternDAO.createNoticeJobCategory(noticeRequestDTO);
    }

    @Override
    @Transactional
    public void modifyNotice(ConsoleInternListRequestDTO noticeRequestDTO) {
//        공고 수정
        consoleInternDAO.editNotice(noticeRequestDTO);

//        직군 수정
        consoleInternDAO.editNoticeJobCategory(noticeRequestDTO);
    }

//    공고 수정 상세
    @Override
    public ConsoleInternListRequestDTO getNotice(Long id) {
        return consoleInternDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteIntern(Long id) {
        consoleInternDAO.deleteRequestInternByNoticeId(id);
        consoleInternDAO.deleteSaveInternByNoticeId(id);
        consoleInternDAO.deleteJobCategoryByNoticeId(id);
        consoleInternDAO.deleteInternNoticeById(id);
    }

//    체험 공고 마감 처리
    public void closeNotice(){
        consoleInternDAO.setNoticeStatusToInactive();
    }

}
