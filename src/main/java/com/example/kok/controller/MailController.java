package com.example.kok.controller;

import com.example.kok.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mail/**")
@RequiredArgsConstructor
@Slf4j
public class MailController {
    private final MailService mailService;

    @PostMapping("send")
    public RedirectView send(String email,String name, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        mailService.sendMail(email,name, request, response);
        return new RedirectView("/member/find-password-ok");
    }
}













