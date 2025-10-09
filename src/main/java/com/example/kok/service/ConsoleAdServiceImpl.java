package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleAdNoticeDAO;
import com.example.kok.repository.ConsoleAdNoticeFileDAO;
import com.example.kok.repository.PaymentDAO;
import com.example.kok.repository.PaymentUserDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsoleAdServiceImpl implements ConsoleAdService {
    private final ConsoleAdNoticeDAO consoleAdDAO;
    private final PaymentDAO paymentDAO;
    private final PaymentUserDAO paymentUserDAO;
    private final S3Service s3Service;
    private final ConsoleAdNoticeDTO consoleAdDTO;
    private final ConsoleAdNoticeFileDAO consoleAdNoticeFileDAO;

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
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "posts", key="'post_' + #id")
    public ConsoleAdNoticeDTO getDetail(Long id) {
        return consoleAdDAO.findDetailById(id);
    }

    @Override
    public void setPreSignedUrl(ConsoleAdNoticeDTO consoleAdNoticeDTO) {
        List<FileDTO> files = consoleAdNoticeFileDAO.findAllByAdvertisementId(consoleAdNoticeDTO.getId());

        files.forEach(file -> {
            file.setFilePath(
                    s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5))
            );
        });

        consoleAdNoticeDTO.setFiles(files);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerAdvertisement(ConsoleAdNoticeDTO adNoticeDTO, List<MultipartFile> multipartFiles) {
//        광고 등록
        consoleAdDAO.createAdvertisement(adNoticeDTO);

        // 파일 업로드
        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            multipartFiles.forEach(multipartFile -> {
                if (multipartFile.isEmpty()) return;

                try {
                    // S3 업로드
                    String s3Key = s3Service.uploadFile(multipartFile, getPath());

                    // 파일 DTO 구성
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                    fileDTO.setFileName(UUID.randomUUID().toString());
                    fileDTO.setFilePath(s3Key);
                    fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                    fileDTO.setFileContentType(multipartFile.getContentType());

                    // tbl_file 저장
                    consoleAdNoticeFileDAO.saveFile(fileDTO);

                    // 광고-파일 연결
                    consoleAdNoticeFileDAO.linkFileToAdvertisement(fileDTO.getId(), adNoticeDTO.getId());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

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
        paymentUser.setPaymentId(payment.getId());
        paymentUser.setUserId(adNoticeDTO.getCompanyId());
        paymentUser.setAdvertisementId(advertisementId);
        paymentUserDAO.insertPaymentUser(paymentUser);
    }

    public String getPath() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return today.format(formatter);
    }

//    광고 수정
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyNotice(ConsoleAdNoticeDTO adNoticeDTO, List<MultipartFile> multipartFiles) {
        consoleAdDAO.editNotice(adNoticeDTO);

        if (multipartFiles != null && !multipartFiles.isEmpty()) {

            for (MultipartFile multipartFile : multipartFiles) {
                if (multipartFile.isEmpty()) continue;

                try {
                    String s3Key = s3Service.uploadFile(multipartFile, getPath());

                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                    fileDTO.setFileName(UUID.randomUUID().toString());
                    fileDTO.setFilePath(s3Key);
                    fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                    fileDTO.setFileContentType(multipartFile.getContentType());

                    consoleAdNoticeFileDAO.saveFile(fileDTO);

                    consoleAdNoticeFileDAO.linkFileToAdvertisement(fileDTO.getId(), adNoticeDTO.getId());

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

//    광고 수정 상세
    @Override
    public ConsoleAdNoticeDTO getNotice(Long id) {
        return consoleAdDAO.findById(id);
    }

}
