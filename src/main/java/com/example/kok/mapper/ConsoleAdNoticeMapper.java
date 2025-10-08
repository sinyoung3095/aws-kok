package com.example.kok.mapper;

import com.example.kok.dto.ConsoleAdNoticeDTO;
import com.example.kok.enumeration.RequestStatus;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsoleAdNoticeMapper {

//    목록
    public List<ConsoleAdNoticeDTO> selectAdByCompany(@Param("companyId") Long companyId,
                                                          @Param("criteria") Criteria criteria,
                                                          @Param("keyword") String keyword);

//    개수
    public int selectCountByCompany(@Param("companyId") Long companyId,
                                    @Param("status") RequestStatus status,
                                    @Param("keyword") String keyword);

//    총 금액
    Long selectActiveTotalPriceByCompany(@Param("companyId") Long companyId);

//    등록
    public void insertAdvertisement(ConsoleAdNoticeDTO adNoticeDTO);

//    공고 상세
    public ConsoleAdNoticeDTO selectAdDetailById(@Param("id") Long id);

//    공고 수정 등록
    public void updateNotice(ConsoleAdNoticeDTO adNoticeDTO);

//    공고 수정 상세
    ConsoleAdNoticeDTO selectById(@Param("id") Long id);

}
