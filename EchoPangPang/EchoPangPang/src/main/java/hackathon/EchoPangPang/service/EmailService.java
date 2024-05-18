package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Code;
import hackathon.EchoPangPang.repository.CodeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final CodeRepository codeRepository;

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    /**
     * 주어진 수신자와 인증 코드로 이메일을 전송한다.
     *
     * @param to 수신자 이메일 주소를 담은 String 변수
     *
     * @throws MessagingException 이메일 전송 중 오류가 발생하면 예외를 던진다.
     */

    public void sendEmail(String to) throws MessagingException {

        // DB에 코드 값 저장
        String code = generateRandomCode();

        // 이메일 주소로 코드 조회
        Optional<Code> existingCode = codeRepository.findCodeByEmail(to);
        Code codeObject;
        if (existingCode.isPresent()) {
            // 이미 존재하는 코드가 있다면 업데이트
            codeObject = existingCode.get();
            codeObject.setCodeNumber(code);
        } else {
            // 새로운 코드 생성
            codeObject = Code.builder()
                    .email(to)
                    .codeNumber(code)
                    .build();
        }
        codeRepository.save(codeObject);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String emailTo = to;
        String emailSubjet = "[ECOPUANG] 이메일 인증";
        String body = getEmailBody(code);

        helper.setTo(emailTo);
        helper.setSubject(emailSubjet);
        helper.setText(body, true);

        // 메일 전송
        mailSender.send(message);
    }

    /**
     * 주어진 인증 코드를 사용하여 Thymeleaf 템플릿을 통해 이메일 본문을 생성한다.
     *
     * @param code 이메일 본문에 포함할 인증 코드.
     * @return 생성된 이메일 본문.
     */
    private String getEmailBody(String code) {
        Context context = new Context();
        context.setVariable("code", code);

        return templateEngine.process("EmailTemplate", context);
    }

    /**
     * 6자리의 랜덤 숫자 코드를 생성합니다.
     *
     * @return 6자리의 랜덤 숫자 코드.
     */
    public String generateRandomCode() {
        Random random = new Random();
        String randomCode = new String();
        // 랜덤 코드 생성
        for (int i = 0; i < 6; i++) {
            randomCode = randomCode.concat(String.valueOf(random.nextInt(10)));
        }

        return randomCode;
    }

    public boolean checkCode(String code, String email) throws MessagingException {
        Optional<Code> existingCode = codeRepository.findCodeByEmail(email);

        return existingCode.isPresent() && existingCode.get().getCodeNumber().equals(code);
    }
}
