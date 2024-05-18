package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.Mission;
import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.repository.MemberRepository;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import hackathon.EchoPangPang.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MissionMapRepository missionMapRepository;

    @Autowired
    public MissionService(MemberRepository memberRepository,
                          MissionRepository missionRepository, MissionMapRepository missionMapRepository) {
        this.memberRepository = memberRepository;
        this.missionRepository = missionRepository;
        this.missionMapRepository = missionMapRepository;
    }

    /***
     * 매일 자정에 실행된다.
     * 모든 멤버들은, 랜덤으로 3개의 미션이 매일 자정에 할당된다.
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void updateDailyMissions() {
        // Logic to update missions for each member
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            updateMissionForMember(member);
        }
        // List<Member> members = memberRepository.findAll();
        // for (Member member : members) {
        //     updateMissionsForMember(member);
        // }
    }

    /***
     * MissionRepository에서 모든 미션들을 불러와, 3개를 랜덤으로 선택한다.
     * MissionMap에 넣어준다.
     * @param member
     */
    private void updateMissionForMember(Member member) {
        // Logic to update missions for a specific member

        List<Mission> allMission = missionRepository.findAll();
        List<Mission> dailyMission = new ArrayList<>();
        dailyMission = selectRandomMissions(allMission, 3);

        // List<Mission> newMissions = generateNewMissions();
        // List<MissionMap> currentMissions = missionMapRepository.findByMemberId(member.getId());
        // missionMapRepository.deleteAll(currentMissions); // Remove existing missions
        // saveNewMissionsForMember(member, newMissions); // Assign new missions
    }

    private List<Mission> selectRandomMissions(List<Mission> allMission, int count) {
        Collections.shuffle(allMission);
        return allMission.subList(0, count);
    }

    /***
     * member entity 객체 받아서, 오늘의 할 일, 미션 리스트 불러와서 넘겨주기
     * @param member
     * @return
     */
    public List<MissionMap> getTodayToDoList(Member member) {
        Long id = member.getId();
        //member id 불러와서 MissionMap List 불러오기

        
        return null;
    }
}
