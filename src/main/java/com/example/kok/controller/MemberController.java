package com.example.kok.controller;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {
    private final UserService userService;

    @GetMapping("join-member")
    public String goToJoinPage() {
        return "member/join-member";
    }

    @PostMapping("join-member")
    public RedirectView joinMember(UserDTO userDTO) {
        userService.joinUser(userDTO);
        return new RedirectView("/member/join-member");
    }

    @GetMapping("join-social")
    public String goToJoinSocialPage() {
        return "member/join-social";
    }

    @GetMapping("join-company")
    public String goToJoinCompanyPage() {
        return "member/join-company";
    }

    @GetMapping("login")
    public String goToLoginPage() {
        return "member/login";
    }

    @GetMapping("find-password")
    public String goToFindPasswordPage() {
        return "member/find-password";
    }

    @GetMapping("find-password-new")
    public String goToFindPasswordNewPage() {
        return "find-password-new";
    }

    @GetMapping("find-password-ok")
    public String goToFindPasswordOkPage() {
        return "member/find-password-ok";
    }

    @GetMapping("find-email")
    public String goToFindEmailPage() {
        return "member/find-email";
    }

    @GetMapping("find-email-ok")
    public String goToFindEmailOkPage() {
        return "member/find-email-ok";
    }
}
