package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.dto.LoginDTO;
import hackathon.EchoPangPang.dto.RegisterDTO;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.Puang;
import hackathon.EchoPangPang.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입 시 사용
    public Member register(RegisterDTO registerDTO) {

        if (memberRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword()); // 비밀번호 암호화

        Member member = Member.builder()
                .name(registerDTO.getName())
                .email(registerDTO.getEmail())
                .password(encodedPassword) // 비밀번호 암호화 후 저장
                .puang(new Puang("푸앙이", Puang.Grade.EGG))
                .build();
        memberRepository.save(member);

        return member;
    }

    // 로그인 시 사용
    public Member login(LoginDTO loginDTO) {
        Member member = memberRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!passwordEncoder.matches(loginDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        return member;
    }

}

