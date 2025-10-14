package com.example.kok.service;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.dto.ConsoleExperienceListRequestDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.mapper.MemberAlarmSettingMapper;
import com.example.kok.repository.ConsoleExperienceListDAO;
import com.example.kok.util.Criteria;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsoleExperienceListServiceImpl implements ConsoleExperienceListService {
    private final ConsoleExperienceListDAO consoleExperienceDAO;
    private final MemberAlarmSettingMapper memberAlarmSettingMapper;
    private final MailService mailService;

    @Override
    public ConsoleExperienceListCriteriaDTO getList(Long companyId, int page, Status status, String keyword) {
        ConsoleExperienceListCriteriaDTO consoleExperienceNoticeCriteriaDTO = new ConsoleExperienceListCriteriaDTO();

        int totalCount = consoleExperienceDAO.findCountByCompany(companyId, status, keyword);
        int activeCount = consoleExperienceDAO.findActiveCountByCompany(companyId, Status.ACTIVE, keyword);
        int requestCount = consoleExperienceDAO.findRequestCountByCompany(companyId);
        int activeRequestCount = consoleExperienceDAO.findRequestActiveCountByCompany(companyId);

        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleExperienceListDTO> notices = consoleExperienceDAO.findAllByCompany(companyId, criteria, status, keyword);



        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleExperienceNoticeCriteriaDTO.setExperienceLists(notices);
        consoleExperienceNoticeCriteriaDTO.setCriteria(criteria);
        consoleExperienceNoticeCriteriaDTO.setTotalCount(totalCount);
        consoleExperienceNoticeCriteriaDTO.setActiveTotalCount(activeCount);
        consoleExperienceNoticeCriteriaDTO.setTotalRequestCount(requestCount);
        consoleExperienceNoticeCriteriaDTO.setActiveRequestCount(activeRequestCount);

        return consoleExperienceNoticeCriteriaDTO;
    }

    @Override
    public void updateListStatus(Long noticeId, Status status) {
        consoleExperienceDAO.updateNoticeStatus(noticeId, status);
    }

//    공고 상세
    @Override
    public ConsoleExperienceListRequestDTO getExperienceDetail(Long id) {
        return consoleExperienceDAO.findDetailById(id);
    }

    @Override
    @Transactional
    public void registerNotice(ConsoleExperienceListRequestDTO noticeRequestDTO) {
//        공고 등록
        consoleExperienceDAO.createNotice(noticeRequestDTO);

//        직군 등록
        consoleExperienceDAO.createNoticeJobCategory(noticeRequestDTO);
//        ExperienceNoticeDTO experienceNoticeDTO = memberAlarmSettingMapper.selectByNoticeId(noticeRequestDTO.getId());
//        experienceNoticeDTO.getUsers().forEach((userDTO) -> {
//
//            if(userDTO.getUserEmail()!=null){
//                try {
//                    mailService.sendMailByNotice(userDTO.getUserEmail(),userDTO.getUserName(),experienceNoticeDTO.getExperienceNoticeTitle(),experienceNoticeDTO.getCompanyName());
//                } catch (MessagingException e) {
//                    throw new RuntimeException(e);
//                }
//            }else{
//                try {
//                    mailService.sendMailByNotice(userDTO.getSnsEmail(),userDTO.getUserName(),experienceNoticeDTO.getExperienceNoticeTitle(),experienceNoticeDTO.getCompanyName());
//                } catch (MessagingException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
    }

    @Override
    @Transactional
    public void modifyNotice(ConsoleExperienceListRequestDTO noticeRequestDTO) {
//        공고 수정
        consoleExperienceDAO.editNotice(noticeRequestDTO);

//        직군 수정
        consoleExperienceDAO.editNoticeJobCategory(noticeRequestDTO);
    }

//    공고 수정 상세
    @Override
    public ConsoleExperienceListRequestDTO getNotice(Long id) {
        return consoleExperienceDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteExperience(Long id) {
        consoleExperienceDAO.deleteRequestExperienceByNoticeId(id);
        consoleExperienceDAO.deleteSaveExperienceByNoticeId(id);
        consoleExperienceDAO.deleteJobCategoryByNoticeId(id);
        consoleExperienceDAO.deleteExperienceNoticeById(id);
    }

}
