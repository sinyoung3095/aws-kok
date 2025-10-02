package com.example.kok.controller;

import com.example.kok.dto.ConsolePaymentCriteriaDTO;
import com.example.kok.service.ConsolePaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/payment")
public class ConsolePaymentApiController {
    private final ConsolePaymentService paymentService;

//    공고 목록
//    @GetMapping("/list/{companyId}/{page}")
//    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId, @PathVariable("page") int page) {
//
//        ConsolePaymentCriteriaDTO paymentCriteriaDTO = paymentService.getList(companyId, page);
//        if(paymentCriteriaDTO == null || paymentCriteriaDTO.getPayments().size() == 0){
//            return ResponseEntity.ok(paymentCriteriaDTO);
//        }
//
//        return ResponseEntity.ok(paymentCriteriaDTO);
//    }

}
