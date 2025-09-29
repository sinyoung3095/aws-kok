package com.example.kok.controller;

import com.example.kok.auth.JwtAuthenticationFilter;
import com.example.kok.auth.JwtTokenProvider;
import com.example.kok.dto.UserDTO;
import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.UserRole;
import com.example.kok.repository.UserDAO;
import com.example.kok.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDAO userDAO;
    private JwtTokenProvider  jwtTokenProvider;

    @GetMapping("join-member")
    public String goToJoinPage() {
        return "member/join-member";
    }

    @PostMapping("join-member")
    public RedirectView joinMember(UserDTO userDTO) {
        int count;
        count = userDAO.findUserByEmail(userDTO.getUserEmail());
        if(count==0) {
            userService.joinUser(userDTO);
            return new RedirectView("/member/login");
        }else{
            return new RedirectView("/member/join-member?error");
        }
    }

    @GetMapping("join-social")
    public String goToJoinSocialPage(@CookieValue(value = "memberEmail", required = false) String userEmail,
                                     @CookieValue(value = "memberName", required = false) String userName,
                                     UserDTO userDTO, Model model) {

        userDTO.setUserEmail(userEmail);
        userDTO.setUserName(userName);
        model.addAttribute("userDTO", userDTO);
        log.info("Get userDTO:"+userDTO);
        return "member/join-social";
    }
    @PostMapping("join-social")
    public RedirectView joinSocial(@CookieValue(value = "role",required = false) String role,
                             String provider, UserDTO userDTO) {
        log.info("userDTO:"+userDTO);
        log.info("role:"+role);
        log.info("provider:"+provider);
        userDTO.setUserRole(role.equals("ROLE_MEMBER") ? UserRole.MEMBER : UserRole.ADMIN);
        userDTO.setMemberProvider(Provider.valueOf(provider));
        log.info("userDTO:"+userDTO);
//        memberService.joinSns(userDTO);
//
//        jwtTokenProvider.createAccessToken(userDTO.getSnsEmail(), provider);
//        jwtTokenProvider.createRefreshToken(userDTO.getSnsEmail(), provider);

        return new RedirectView("/post/list/1");
    }


    @GetMapping("join-company")
    public String goToJoinCompanyPage() {
        return "member/join-company";
    }

    @GetMapping("login")
    public String goToLoginPage(UserDTO userDTO, Model model) {
        model.addAttribute("userDTO", userDTO);
        return "member/login";
    }

    @GetMapping("find-password")
    public String goToFindPasswordPage() {
        return "member/find-password";
    }

    @GetMapping("find-password-new")
    public String goToFindPasswordNewPage() {
        return "member/find-password-new";
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
