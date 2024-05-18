package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.Mission;
import hackathon.EchoPangPang.entity.Puang;
import hackathon.EchoPangPang.repository.MemberRepository;
import hackathon.EchoPangPang.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DummyDataService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;


    public void insertDummyData() {
        // 더미 회원 데이터 생성
        Member member1 = Member.builder()
                .name("John Doe")
                .email("john@example.com")
                .password("password1")
                .point(50)
                .puang(new Puang("푸앙1", Puang.Grade.EGG))
                .build();

        Member member2 = Member.builder()
                .name("Jane Doe")
                .email("jane@example.com")
                .password("password2")
                .point(100)
                .puang(new Puang("푸앙2", Puang.Grade.ADULT))
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        // 20개의 미션 생성하여 저장
        for (int i = 1; i <= 20; i++) {
            Mission mission = Mission.builder()
                    .content("Mission" + i)
                    .point(i) // 각 미션은 100의 배수로 포인트 설정
                    .build();

            missionRepository.save(mission);
        }
    }
}
