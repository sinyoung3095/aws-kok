package com.example.kok.repository;

import com.example.kok.dto.ConsolePaymentDTO;
import com.example.kok.mapper.ConsolePaymentMapper;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ConsolePaymentDAO {
    private final ConsolePaymentMapper consolePaymentMapper;

//    결제 등록
    public void insertPayment(ConsolePaymentDTO payment) {
        consolePaymentMapper.insertPayment(payment);
    }

//    결제 삭제
    public void deleteByAdvertisementId(Long advertisementId) {
        // payment_user 삭제
        consolePaymentMapper.deletePaymentUserByAdvertisementId(advertisementId);

        // payment 삭제
        consolePaymentMapper.deleteByAdvertisementId(advertisementId);
    }

//    결제 목록(전체)
    public List<ConsolePaymentDTO> findAllByCompany(Long companyId, Criteria criteria) {
        return consolePaymentMapper.selectPaymentByCompany(companyId, criteria);
    }

//    결제 개수
    public int findCountByCompany(Long companyId) {
        return consolePaymentMapper.selectCountByCompany(companyId);
    }

}
