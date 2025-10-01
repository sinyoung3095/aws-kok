package com.example.kok.controller;

import com.example.kok.auth.JwtAuthenticationFilter;
import com.example.kok.auth.JwtTokenProvider;
import com.example.kok.dto.UserDTO;
import com.example.kok.enumeration.Provider;
import com.example.kok.enumeration.UserRole;
import com.example.kok.mybatis.handler.ProviderHandler;
import com.example.kok.repository.UserDAO;
import com.example.kok.service.S3Service;
import com.example.kok.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDAO userDAO;
    private final JwtTokenProvider  jwtTokenProvider;

    @GetMapping("join-member")
    public String goToJoinPage() {
        return "member/join-member";
    }

    @PostMapping("join-member")
    public RedirectView joinMember(UserDTO userDTO) {
        int count;
        count = userDAO.findUserByEmail(userDTO.getUserEmail());
        if(count==0) {
            userDTO.setMemberProvider(Provider.KOK);
            userService.joinUser(userDTO);
            return new RedirectView("/member/login");
        }else{
            return new RedirectView("/member/join-member?error");
        }
    }

    @GetMapping("join-social")
    public String goToJoinSocialPage(@CookieValue(value = "userEmail", required = false) String userEmail,
                                     @CookieValue(value = "memberName", required = false) String userName,
                                     UserDTO userDTO, Model model,String provider) {

        userDTO.setSnsEmail(userEmail);
        userDTO.setUserName(userName);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("provider", provider);
        log.info("Get userDTO:"+userDTO);
        return "member/join-social";
    }
    @PostMapping("join-social")
    public RedirectView joinSocial(@CookieValue(value = "role",required = false) String role, UserDTO userDTO) {
        String kind = userDTO.getMemberProvider().getValue();
        userDTO.setUserRole(role.equals("ROLE_MEMBER") ? UserRole.MEMBER : UserRole.ADMIN);

        userService.joinSnsUser(userDTO);
        log.info("userDTO:"+userDTO);
        log.info("kind:"+kind);

        jwtTokenProvider.createAccessToken(userDTO.getSnsEmail(), kind);
        jwtTokenProvider.createRefreshToken(userDTO.getSnsEmail(), kind);

        return new RedirectView("/community/page");
    }


    @GetMapping("join-company")
    public String goToJoinCompanyPage() {
        return "member/join-company";
    }

    @PostMapping("join-company")
    public RedirectView joinCompany(UserDTO userDTO, @RequestParam("file") MultipartFile multipartFiles ) {
        log.info("post들어옴");
        int count;
        count = userDAO.findUserByEmail(userDTO.getUserEmail());
        if(count==0) {
            userDTO.setMemberProvider(Provider.KOK);
            try {
                userService.joinCompany(userDTO,multipartFiles);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return new RedirectView("/member/login");
        }else{
            return new RedirectView("/member/join-company?error");
        }
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

    @GetMapping("find-password-ok")
    public RedirectView goToFindPasswordOkPage(@CookieValue(name="code", required = false) String cookieCode,
                                         String code,
                                         HttpServletResponse response){
        if(cookieCode == null || cookieCode.isEmpty()){
            return new RedirectView("/member/find-password");
        }

        if(cookieCode.equals(code)){
            Cookie cookie = new Cookie("code", null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            return new RedirectView("/member/find-password-new");
        }

        return new RedirectView("/member/find-password-ok");
    }

    @GetMapping("find-password-new")
    public String goToFindPasswordNewPage(@CookieValue(name="email", required = false) String email,Model model) {
        model.addAttribute("userEmail", email);
        return "member/find-password-new";
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
