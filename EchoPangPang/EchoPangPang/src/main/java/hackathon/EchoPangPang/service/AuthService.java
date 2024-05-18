package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.dto.LoginDTO;
import hackathon.EchoPangPang.dto.RegisterDTO;
import hackathon.EchoPangPang.entity.*;
import hackathon.EchoPangPang.repository.MemberRepository;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import hackathon.EchoPangPang.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    private final MissionMapRepository missionMapRepository;

    private final MissionRepository missionRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final int DEFAULT_MISSION_COUNT = 3;

    // 회원가입 시 사용
    @Transactional
    public Member register(RegisterDTO registerDTO) {

        if (memberRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }
        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword()); // 비밀번호 암호화
        Member member = Member.builder()
                .name(registerDTO.getName())
                .email(registerDTO.getEmail())
                .point(0)
                .puang(new Puang("푸앙이", Puang.Grade.EGG))
                .password(encodedPassword) // 비밀번호 암호화 후 저장
                .build();
        memberRepository.save(member);

        // 미션 할당
        List<Mission> dailyMissions = getRandomMissions(DEFAULT_MISSION_COUNT);
        linkMissions(member, dailyMissions);

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

    public List<Mission> getRandomMissions(int count) {
        return missionRepository.findRandomMissions(PageRequest.of(0, count));
    }

    @Transactional
    public void linkMissions(Member member, List<Mission> dailyMissions) {
        for (Mission mission : dailyMissions) {
            MissionMap missionMap = MissionMap.builder()
                    .member(member)
                    .mission(mission)
                    .status(MissionStatus.NOT_STARTED)
                    .build();
            missionMapRepository.save(missionMap);
        }
    }
}

