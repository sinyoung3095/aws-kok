package com.example.kok.service;

import com.example.kok.dto.*;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.repository.ConsoleAdNoticeDAO;
import com.example.kok.repository.ConsoleAdNoticeFileDAO;
import com.example.kok.repository.PaymentDAO;
import com.example.kok.repository.PaymentUserDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
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
    public void setPreSignedUrl(ConsoleAdNoticeDTO consoleAdDTO) {
        List<FileDTO> files = consoleAdNoticeFileDAO.findAllByAdvertisementId(consoleAdDTO.getId());

        files.forEach(file -> {
            file.setFilePath(
                    s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5))
            );
        });

        consoleAdDTO.setUploadedFiles(files);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerAdvertisement(ConsoleAdNoticeDTO consoleAdDTO, List<MultipartFile> multipartFiles) {
//        광고 등록
        consoleAdDAO.createAdvertisement(consoleAdDTO);

        // 파일 업로드
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

                ConsoleAdNoticeFileDTO consoleFileDTO = new ConsoleAdNoticeFileDTO();
                consoleFileDTO.setFileId(fileDTO.getId());
                consoleFileDTO.setAdvertisementId(consoleAdDTO.getId());

                System.out.println("🧩 연결하려는 광고 ID: " + consoleAdDTO.getId());
                System.out.println("🧩 연결하려는 파일 ID: " + fileDTO.getId());

                // 광고-파일 연결
                consoleAdNoticeFileDAO.linkFileToAdvertisement(consoleFileDTO);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Long advertisementId = consoleAdDTO.getId();

//        결제 등록
        PaymentDTO payment = new PaymentDTO();
        payment.setAdvertisementId(advertisementId);
        payment.setUserId(consoleAdDTO.getCompanyId());
        payment.setPaymentPrice(consoleAdDTO.getPaymentPrice());
        payment.setPaymentStatus(RequestStatus.AWAIT);
        paymentDAO.insertPayment(payment);

//        결제사용자 등록
        PaymentUserDTO paymentUser = new PaymentUserDTO();
        paymentUser.setPaymentId(payment.getId());
        paymentUser.setUserId(consoleAdDTO.getCompanyId());
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
    @CacheEvict(value = "posts", key = "'post_' + #consoleAdDTO.id")
    public void modifyNotice(ConsoleAdNoticeDTO consoleAdDTO, List<MultipartFile> multipartFiles) {
        consoleAdDAO.editNotice(toConsoleAdVO(consoleAdDTO));

        if (multipartFiles != null && !multipartFiles.isEmpty()) {
            multipartFiles.forEach((multipartFile) -> {
                if (multipartFile.isEmpty()) return;

                try {
                    String s3Key = s3Service.uploadFile(multipartFile, getPath());
                    FileDTO fileDTO = new FileDTO();
                    fileDTO.setFileOriginName(multipartFile.getOriginalFilename());
                    fileDTO.setFileName(s3Key.substring(s3Key.lastIndexOf("/") + 1));
                    fileDTO.setFileSize(String.valueOf(multipartFile.getSize()));
                    fileDTO.setFilePath(s3Key);
                    fileDTO.setFileContentType(multipartFile.getContentType());

                    consoleAdNoticeFileDAO.saveFile(fileDTO);

                    ConsoleAdNoticeFileDTO consoleAdNoticeFileDTO = new ConsoleAdNoticeFileDTO();
                    consoleAdNoticeFileDTO.setFileId(fileDTO.getId());
                    consoleAdNoticeFileDTO.setAdvertisementId(consoleAdDTO.getId());

                    consoleAdNoticeFileDAO.linkFileToAdvertisement(consoleAdNoticeFileDTO);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

//    광고 수정 상세
    @Override
    public ConsoleAdNoticeDTO getNotice(Long id) {
        return consoleAdDAO.findById(id);
    }

}
