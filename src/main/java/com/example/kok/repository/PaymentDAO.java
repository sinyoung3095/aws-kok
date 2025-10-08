package com.example.kok.repository;

import com.example.kok.dto.PaymentDTO;
import com.example.kok.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {
    private final PaymentMapper paymentMapper;

    public void insertPayment(PaymentDTO payment) {
        paymentMapper.insertPayment(payment);
    }
}
