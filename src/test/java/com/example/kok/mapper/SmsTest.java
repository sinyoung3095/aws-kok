package com.example.kok.mapper;

import com.example.kok.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SmsTest {
    @Autowired
    private SmsService smsService;

    @Test
    public void sendSms(){
        String phone ="01051133095";
        smsService.send(phone);
    }
}
