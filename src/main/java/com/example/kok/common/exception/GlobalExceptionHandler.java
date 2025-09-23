package com.example.kok.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

public class GlobalExceptionHandler {
    @ExceptionHandler(MemberLoginFailException.class)
    public RedirectView handleMemberLoginFailException(MemberLoginFailException e) {
        return new RedirectView("");
    }
}
