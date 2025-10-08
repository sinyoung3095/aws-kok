package com.example.kok.repository;

import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.dto.ConsoleInternNoticeRequestDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.mapper.ConsoleAdNoticeMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsoleAdNoticeDAO {
    private final ConsoleAdNoticeMapper consoleAdMapper;

//    목록(전체)
    public List<ConsoleAdNoticeDTO> findAllByCompany(Long companyId, Criteria criteria, String keyword) {
        return consoleAdMapper.selectAdByCompany(companyId, criteria, keyword);
    }

//    개수
    public int findCountByCompany(Long companyId, RequestStatus status, String keyword) {
        return consoleAdMapper.selectCountByCompany(companyId, status, keyword);
    }

//    활성화 상태 광고
    public int findActiveCountByCompany(Long companyId, RequestStatus status, String keyword) {
        return consoleAdMapper.selectCountByCompany(companyId, status, keyword);
    }

//    총 금액
    public Long findActiveTotalPriceByCompany(Long companyId) {
        return consoleAdMapper.selectActiveTotalPriceByCompany(companyId);
    }

//    광고 등록
    public void createAdvertisement(ConsoleAdNoticeDTO adNoticeDTO) {
        consoleAdMapper.insertAdvertisement(adNoticeDTO);
    }

//    공고 상세
    public ConsoleAdNoticeDTO findDetailById(Long id) {
        return consoleAdMapper.selectAdDetailById(id);
    }

//    공고 수정 등록
    public void editNotice(ConsoleAdNoticeDTO adNoticeDTO) {
        consoleAdMapper.updateNotice(adNoticeDTO);
    }

//    공고 수정 상세
    public ConsoleAdNoticeDTO findById(Long id) {
        return consoleAdMapper.selectById(id);
    }

}
