package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입 시 사용
    public void register(Member member) {
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword())); // 비밀번호 암호화 후 저장
        memberRepository.save(member);
    }

    // 로그인 시 사용
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일이나 비밀번호가 틀렸습니다."));
        // 비밀번호 검증
        if (!passwordEncoder.matches(password, member.getPassword())) {
            new IllegalArgumentException("이메일이나 비밀번호가 틀렸습니다.");
        }
        return member;
    }
}
