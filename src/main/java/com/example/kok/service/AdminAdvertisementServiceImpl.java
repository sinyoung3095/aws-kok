package com.example.kok.service;

import com.example.kok.common.exception.PostNotFoundException;
import com.example.kok.dto.AdminAdvertisementCriteriaDTO;
import com.example.kok.dto.AdminAdvertisementDTO;
import com.example.kok.dto.AdvertisementBackgroundFileDTO;
import com.example.kok.dto.PostFileDTO;
import com.example.kok.repository.AdminAdvertisementDAO;
import com.example.kok.repository.AdvertisementBackgroundFileDAO;
import com.example.kok.repository.CommunityPostFileDAO;
import com.example.kok.util.AdminAdvertisementCriteria;
import com.example.kok.util.Criteria;
import com.example.kok.util.DateUtils;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Primary
public class AdminAdvertisementServiceImpl implements AdminAdvertisementService {
    private final AdminAdvertisementDAO adminAdvertisementDAO;
    private final AdvertisementBackgroundFileDAO advertisementBackgroundFileDAO;
    private final S3Service s3Service;

//    광고 목록
    @Override
    public AdminAdvertisementCriteriaDTO advertisementList(int page, Search search) {
        AdminAdvertisementCriteriaDTO advertisementCriteriaDTO = new AdminAdvertisementCriteriaDTO();
        AdminAdvertisementCriteria criteria = new AdminAdvertisementCriteria(page, adminAdvertisementDAO.countAll(search));
        List<AdminAdvertisementDTO> advertisements = adminAdvertisementDAO.getAdvertisementList(criteria, search);

        criteria.setHasMore(advertisements.size() > criteria.getRowCount());
        criteria.setHasPreviousPage(page > 1);
        criteria.setHasNextPage(page < criteria.getRealEnd());

        log.info("이전 페이지 버튼: {}", criteria.isHasPreviousPage());
        log.info("다음 페이지 버튼: {}", criteria.isHasNextPage());
//        11개 가져왔으면, 마지막 1개 삭제
        if(criteria.isHasMore()){
            advertisements.remove(advertisements.size()-1);
        }

        advertisementCriteriaDTO.setAdvertisements(advertisements);
        advertisementCriteriaDTO.setCriteria(criteria);
        return advertisementCriteriaDTO;
    }

//    광고 상세
    @Override
    public AdminAdvertisementDTO advertisementDetail(Long id) {
        return adminAdvertisementDAO.selectAdvertisement(id).orElseThrow(PostNotFoundException::new);
    }

    @Override
    public void setPreSignedUrl(AdminAdvertisementDTO advertisementDTO){
        List<AdvertisementBackgroundFileDTO> files = advertisementBackgroundFileDAO.advertisementBackgroundFile(advertisementDTO.getId());
        files.forEach(file -> {
            file.setFilePath(s3Service.getPreSignedUrl(file.getFilePath(), Duration.ofMinutes(5)));
        });

        advertisementDTO.setAdvertisementBackgroundFiles(files);
    }

//    광고 승인
    public void accept(Long id){
        adminAdvertisementDAO.acceptAdvertisement(id);
    }
//    광고 거절
    public void reject(Long id){
        adminAdvertisementDAO.rejectAdvertisement(id);
    }
}
