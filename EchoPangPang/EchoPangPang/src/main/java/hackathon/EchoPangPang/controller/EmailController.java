package hackathon.EchoPangPang.controller;


import hackathon.EchoPangPang.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;


    /**
     * 이메일 전송 요청을 처리하는 엔드포인트.
     **/
    @PostMapping("/api/sendEmail")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody Map<String, String> requestMap) {
        String to = requestMap.get("to");
        Map<String, String> response = new HashMap<>();
        try {
            emailService.sendEmail(to);
            response.put("message", "Email sent successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MessagingException e) {
            response.put("message", "Error sending email: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
