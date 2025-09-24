package com.example.kok.service;

import com.example.kok.repository.AdminNoticeDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class AdminNoticeServiceImpl implements AdminNoticeService {
    private final AdminNoticeDAO adminNoticeDAO;
}
