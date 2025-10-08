package com.example.kok.mapper;

import com.example.kok.dto.PaymentDTO;
import com.example.kok.dto.PaymentUserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentUserMapper {
//    결제
    void insertPaymentUser(PaymentUserDTO paymentUser);
}
