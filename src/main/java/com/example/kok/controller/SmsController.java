//package com.example.kok.controller;
//
//
//import com.example.kok.dto.UserDTO;
//import com.example.kok.repository.UserDAO;
//import com.example.kok.service.SmsService;
//import com.example.kok.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/sms/**")
//@Slf4j
//public class SmsController {
//    private final SmsService smsService;
//    private final UserDAO  userDAO;
//    private final UserService userService;
//
//    @PostMapping("/send")
//    public ResponseEntity<?> sendSms(@RequestBody Map<String, String> request) {
//        String phone = request.get("userPhone");
//        Map<String, Object> body = new HashMap<>();
//
//        if (phone == null || phone.isEmpty()) {
//            body.put("success", false);
//            body.put("message", "전화번호가 없습니다.");
//            return ResponseEntity.ok(body);
//        }
//
//        UserDTO user = userDAO.findEmailByPhone(phone);
//        if (user == null) {
//            body.put("success", false);
//            body.put("message", "가입된 계정을 찾을 수 없습니다.");
//            return ResponseEntity.ok(body);
//        }
//
//        body.put("success", true);
//        body.put("code", smsService.send(phone));
//        return ResponseEntity.ok(body);
//    }
//
//    @PostMapping("/find-email")
//    public ResponseEntity<?> findEmail(@RequestBody Map<String, String> request) {
//        String phone = request.get("userPhone");
//        Map<String, Object> body = new HashMap<>();
//
//        if (phone == null || phone.isEmpty()) {
//            body.put("success", false);
//            body.put("message", "전화번호가 없습니다.");
//            return ResponseEntity.ok(body);
//        }
//
//        UserDTO user = userDAO.findEmailByPhone(phone);
//
//        if (user != null) {
//            body.put("success", true);
//            body.put("email", user.getUserEmail());
//        } else {
//            body.put("success", false);
//            body.put("message", "가입된 계정을 찾을 수 없습니다.");
//        }
//
//        return ResponseEntity.ok(body);
//    }
//
//}
