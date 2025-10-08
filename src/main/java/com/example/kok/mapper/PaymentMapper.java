package com.example.kok.mapper;

import com.example.kok.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
//    결제
    void insertPayment(PaymentDTO payment);
}
