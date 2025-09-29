package com.example.kok.service;

import com.example.kok.auth.CustomUserDetails;
import com.example.kok.dto.UserDTO;
import com.example.kok.repository.UserDAO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDAO userDAO;
    private final HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userEmail = null;
        UserDTO userDTO = null;


        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if("userEmail".equals(cookie.getName())){
                    userEmail = cookie.getValue();
                }
            }
        }

        if(userEmail == null){
//            if(기업?){
//
//            }else{
//
//            }
//        이메일로 전체 정보 조회
            userDTO = userDAO.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("소유자를 찾을 수 없습니다."));
            log.info("userDTO{}",userDTO);
        }else {

        }

        return new CustomUserDetails(userDTO);
    }
}
