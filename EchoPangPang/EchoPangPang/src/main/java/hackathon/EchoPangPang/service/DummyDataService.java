package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.*;
import hackathon.EchoPangPang.repository.MemberRepository;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import hackathon.EchoPangPang.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.GenericDeclaration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DummyDataService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MissionMapRepository missionMapRepository;

    @Value("${dummyPassword}")
    private String dummyPassword;

    private ArrayList<Member> members = new ArrayList<>();

    public List<Mission> getRandomMissions(int count) {
        return missionRepository.findRandomMissions(PageRequest.of(0, count));
    }

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

    public void insertDummyData() {
        // 이름 배열
        String[] names = {
                "김민수", "이영희", "박지훈", "최수지", "홍길동", "강민준", "이지은", "신동엽", "윤아름", "안지훈",
                "김서현", "정예원", "문지훈", "이하나", "정지훈", "김수현", "박민정", "최영수", "김미나", "박서준",
                "한예슬", "백지훈", "이정은", "송민호", "서지수", "김보라", "정성훈", "이현우", "장미란", "박소영",
                "강서연", "이지수", "김다은", "박상훈", "최유진", "윤지훈", "신지우", "한지민", "김다인", "박준영",
                "김지원", "최현우", "이지현", "박혜진", "송지호", "유민준", "정하늘", "김준수", "박서연", "한유라"
        };



        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(dummyPassword);

        for (int i = 0; i < names.length; i++) {
            Member member = Member.builder()
                    .name(names[i])
                    .email("user" + i + "@example.com")
                    .password(encodedPassword)
                    .point(0)
                    .puang(new Puang("푸앙" + names[i], Puang.Grade.EGG))
                    .build();
            memberRepository.save(member);
            members.add(member);
        }

        // 20개의 미션 생성하여 저장
        for (int i = 1; i <= 20; i++) {
            Mission mission = Mission.builder()
                    .content("Mission" + i)
                    .point(i) // 각 미션은 100의 배수로 포인트 설정
                    .build();

            missionRepository.save(mission);
        }

        List<Mission> missions = getRandomMissions(3);

        for (Member member : members) {
            linkMissions(member, missions);
        }
    }
}
