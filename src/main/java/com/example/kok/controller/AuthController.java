package com.example.kok.controller;


import com.example.kok.auth.CustomUserDetails;
import com.example.kok.auth.JwtTokenProvider;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserDTO;
import com.example.kok.service.CustomUserDetailsService;
import com.example.kok.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth/**")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpServletResponse response;
    private final MemberService memberService;

//    로그인
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
            log.info(userDTO.toString());
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserEmail(), userDTO.getUserPassword()));
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            if(!user.getUserRole().getValue().equals(userDTO.getUserRole().getValue())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "로그인 실패"));
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtTokenProvider.createAccessToken(((CustomUserDetails) authentication.getPrincipal()).getUserEmail());
            String refreshToken = jwtTokenProvider.createRefreshToken(((CustomUserDetails) authentication.getPrincipal()).getUserEmail());

            log.info(refreshToken);
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);


            return ResponseEntity.ok(tokens);

        } catch(AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "로그인 실패: " + e.getMessage()));
        }
    }

//    로그아웃
    @PostMapping("logout")
    public void logout(@CookieValue(value = "accessToken", required = false) String token) {

        log.info("Logout User: {}", token);
        String username = jwtTokenProvider.getUserName(token);
        String provider = (String) jwtTokenProvider.getClaims(token).get("provider");
        log.info(provider);

        if(provider == null){
            jwtTokenProvider.deleteRefreshToken(username);
        }else{
            jwtTokenProvider.deleteRefreshToken(username,provider);
        }

        jwtTokenProvider.addToBlacklist(token);

        Cookie deleteAccessCookie = new Cookie("accessToken", null);
        deleteAccessCookie.setHttpOnly(true);
        deleteAccessCookie.setSecure(true);
        deleteAccessCookie.setPath("/");
        deleteAccessCookie.setMaxAge(0);

        response.addCookie(deleteAccessCookie);

        Cookie deleteRefreshCookie = new Cookie("refreshToken", null);
        deleteRefreshCookie.setHttpOnly(true);
        deleteRefreshCookie.setSecure(true);
        deleteRefreshCookie.setPath("/");
        deleteRefreshCookie.setMaxAge(0);

        response.addCookie(deleteRefreshCookie);

        Cookie memberNameCookie = new Cookie("memberName", null);
        memberNameCookie.setHttpOnly(true);
        memberNameCookie.setSecure(true);
        memberNameCookie.setPath("/");
        memberNameCookie.setMaxAge(0);

        response.addCookie(memberNameCookie);


        Cookie userEmailCookie = new Cookie("userEmail", null);
        userEmailCookie.setHttpOnly(true);
        userEmailCookie.setSecure(true);
        userEmailCookie.setPath("/");
        userEmailCookie.setMaxAge(0);

        response.addCookie(userEmailCookie);

        Cookie providerCookie = new Cookie("provider", null);
        providerCookie.setHttpOnly(true);
        providerCookie.setSecure(true);
        providerCookie.setPath("/");
        providerCookie.setMaxAge(0);

        response.addCookie(providerCookie);

        Cookie profileCookie = new Cookie("profile", null);
        profileCookie.setHttpOnly(true);
        profileCookie.setSecure(true);
        profileCookie.setPath("/");
        profileCookie.setMaxAge(0);

        response.addCookie(profileCookie);
    }

//    리프레시 토큰으로 엑세스 토큰 발급
    @GetMapping("refresh")
    public Map<String, String> refresh(@CookieValue(value = "refreshToken", required = false) String token){
        String username = jwtTokenProvider.getUserName(token);
        String refreshToken = jwtTokenProvider.getRefreshToken(username);
        if(refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)){
            throw new RuntimeException("리프레시 토큰이 유효하지 않습니다.");
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) jwtTokenProvider.getAuthentication(refreshToken).getPrincipal();
        String accessToken = jwtTokenProvider.createAccessToken(customUserDetails.getUsername());

        jwtTokenProvider.deleteRefreshToken(username);
        jwtTokenProvider.createRefreshToken(customUserDetails.getUsername());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken);

        return tokenMap;
    }

}












