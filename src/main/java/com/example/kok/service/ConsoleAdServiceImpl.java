package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleAdNoticeDAO;
import com.example.kok.repository.PaymentDAO;
import com.example.kok.repository.PaymentUserDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsoleAdServiceImpl implements ConsoleAdService {
    private final ConsoleAdNoticeDAO consoleAdDAO;
    private final PaymentDAO paymentDAO;
    private final PaymentUserDAO paymentUserDAO;
//    private final ConsoleAdFileDAO postFileDAO;
    private final S3Service s3Service;
    private final PostFileDTO postFileDTO;

    // 목록
    @Override
    public ConsoleAdNoticeCriteriaDTO getList(Long companyId, int page, String keyword) {
        ConsoleAdNoticeCriteriaDTO consoleAdNoticeCriteriaDTO = new ConsoleAdNoticeCriteriaDTO();

        int totalCount = consoleAdDAO.findCountByCompany(companyId, null, keyword);
        int activeCount = consoleAdDAO.findActiveCountByCompany(companyId, RequestStatus.ACCEPT, keyword);
        Long activeTotalPrice = consoleAdDAO.findActiveTotalPriceByCompany(companyId);
        Criteria criteria = new Criteria(page, totalCount);

        List<ConsoleAdNoticeDTO> notices = consoleAdDAO.findAllByCompany(companyId, criteria, keyword);

        criteria.setHasMore(notices.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            notices.remove(notices.size() - 1);
        }

        consoleAdNoticeCriteriaDTO.setAdLists(notices);
        consoleAdNoticeCriteriaDTO.setCriteria(criteria);
        consoleAdNoticeCriteriaDTO.setTotalCount(totalCount);
        consoleAdNoticeCriteriaDTO.setActiveTotalCount(activeCount);
        consoleAdNoticeCriteriaDTO.setActiveTotalPrice(activeTotalPrice);

        return consoleAdNoticeCriteriaDTO;
    }

    @Override
    public ConsoleAdNoticeDTO getDetail(Long id) {
        return consoleAdDAO.findDetailById(id);
    }

    @Override
    @Transactional
    public void registerAdvertisement(ConsoleAdNoticeDTO adNoticeDTO) {
//        광고 등록
        consoleAdDAO.createAdvertisement(adNoticeDTO);

        Long advertisementId = adNoticeDTO.getId();

//        결제 등록
        PaymentDTO payment = new PaymentDTO();
        payment.setAdvertisementId(advertisementId);
        payment.setUserId(adNoticeDTO.getCompanyId());
        payment.setPaymentPrice(adNoticeDTO.getPaymentPrice());
        payment.setPaymentStatus(RequestStatus.AWAIT);
        paymentDAO.insertPayment(payment);

//        결제사용자 등록
        PaymentUserDTO paymentUser = new PaymentUserDTO();
        paymentUser.setPaymentId(payment.getId()); // useGeneratedKeys로 생성된 PK
        paymentUser.setUserId(adNoticeDTO.getCompanyId());
        paymentUser.setAdvertisementId(advertisementId);
        paymentUserDAO.insertPaymentUser(paymentUser);
    }

//    광고 수정
    @Override
    public void modifyNotice(ConsoleAdNoticeDTO adNoticeDTO) {
        consoleAdDAO.editNotice(adNoticeDTO);
    }

//    광고 수정 상세
    @Override
    public ConsoleAdNoticeDTO getNotice(Long id) {
        return consoleAdDAO.findById(id);
    }


//    @Override
//    public ConsoleAdNoticeDTO getNotice(Long id) {
//        return null;
//    }

//    @Override
//    public void setPreSignedUrl(PostDTO postDTO) {
//        List<PostFileDTO> postFiles = postFileDAO.findAllByPostId(postDTO.getId());
//        postFiles.forEach((postFile) -> {
//            postFile.setPostFilePath(s3Service.getPreSignedUrl(postFile.getPostFilePath(), Duration.ofMinutes(5)));
//        });
//
//        postDTO.setPostFiles(postFiles);
//    }
}
