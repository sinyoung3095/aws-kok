package com.example.kok.service;

import com.example.kok.dto.ConsolePaymentCriteriaDTO;
import com.example.kok.dto.ConsolePaymentDTO;
import com.example.kok.dto.ConsoleExperienceNoticeRequestDTO;
import com.example.kok.enumeration.Status;
import com.example.kok.repository.ConsoleExperienceListDAO;
import com.example.kok.repository.ConsolePaymentDAO;
import com.example.kok.util.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsolePaymentServiceImpl implements ConsolePaymentService {
    private final ConsolePaymentDAO consolePaymentDAO;

    @Override
    public ConsolePaymentCriteriaDTO getList(Long companyId, int page) {
        ConsolePaymentCriteriaDTO consolePaymentCriteriaDTO = new ConsolePaymentCriteriaDTO();

        int totalCount = consolePaymentDAO.findCountByCompany(companyId);
        Criteria criteria = new Criteria(page, totalCount);

        List<ConsolePaymentDTO> payments = consolePaymentDAO.findAllByCompany(companyId, criteria);

        criteria.setHasMore(payments.size() > criteria.getRowCount());
        if(criteria.isHasMore()){
            payments.remove(payments.size() - 1);
        }

        consolePaymentCriteriaDTO.setTotalCount(totalCount);
        consolePaymentCriteriaDTO.setCriteria(criteria);
        consolePaymentCriteriaDTO.setPayments(payments);

        return consolePaymentCriteriaDTO;
    }
}
