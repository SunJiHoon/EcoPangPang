package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.*;
import hackathon.EchoPangPang.repository.MemberRepository;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import hackathon.EchoPangPang.repository.MissionRepository;
import hackathon.EchoPangPang.repository.StampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MissionMapRepository missionMapRepository;
    private final StampRepository stampRepository;

    @Autowired
    public MissionService(MemberRepository memberRepository,
                          MissionRepository missionRepository, MissionMapRepository missionMapRepository, StampRepository stampRepository) {
        this.memberRepository = memberRepository;
        this.missionRepository = missionRepository;
        this.missionMapRepository = missionMapRepository;
        this.stampRepository = stampRepository;
    }

    /***
     * 매일 자정에 실행된다.
     * 모든 멤버들은, 랜덤으로 3개의 미션이 매일 자정에 할당된다.
     */
//    @Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 0 * * *")
    public void updateDailyMissions() {
        // Logic to update missions for each member
        System.out.println("스케줄러 등록");
        List<Member> members = memberRepository.findAll();
        List<Mission> allMission = missionRepository.findAll();
        for (Member member : members) {
            updateMissionForMember(member, allMission);
        }
    }

    @Transactional
    public void checkMissionStatus(Long id, Member member) {
        if (id == null) {
            throw new IllegalArgumentException("Mission ID must not be null");
        }

        LocalDate today = LocalDate.now();
        Optional<Mission> missionOptional = missionRepository.findById(id);
        if (missionOptional.isEmpty()) {
            throw new IllegalArgumentException("Mission not found with ID: " + id);
        }

        Mission mission = missionOptional.get();
        MissionMap findMission = missionMapRepository.findByMissionAndCreatedDate(mission, today);
        if (findMission != null) {
            findMission.setStatus(MissionStatus.COMPLETED);

            if (stampRepository.findByCreatedDate(today).isEmpty()) {
                Stamp newStamp = Stamp.builder()
                        .member(member)
                        .build();
                stampRepository.save(newStamp);
            }
        }
    }


    /***
     * mission id로 미션 조회
     * 미션에 배정된 포인트만큼 사용자 포인트 증가시키기
     * @param id
     * @param member
     * @throws Exception
     */
    public void checkMissionPoint(Long id, Member member) throws Exception {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isEmpty()) {
            throw new Exception("Mission not found");
        }
        member.increasePoint(mission.get().getPoint());
    }

    /***
     * mission id로 미션 조회
     * 미션에 배정된 포인트만큼 사용자 포인트 감소시키키
     * @param id
     * @param member
     * @throws Exception
     */
    public void uncheckMissionPoint(Long id, Member member) throws Exception {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isEmpty()) {
            throw new Exception("Mission not found");
        }
        member.decreasePoint(mission.get().getPoint());
    }

    @Transactional
    public void uncheckMissionStatus(Long id, Member member) {
        LocalDate today = LocalDate.now();
        MissionMap findMission = missionMapRepository.findByMissionAndCreatedDate(missionRepository.findById(id).get(), today);
        findMission.setStatus(MissionStatus.NOT_STARTED);

        boolean check = false; //하나라도 COMPLETED인 미션이 있으면 true
        List<MissionMap> todayMission = missionMapRepository.findByCreatedDate(today);
        for (MissionMap missionMap : todayMission) {
            if (missionMap.getStatus() == MissionStatus.COMPLETED) {
                check = true;
                break;
            }
        }

        if (!check) {
            stampRepository.delete(stampRepository.findByCreatedDate(today).get());
        }
    }

    /***
     * MissionRepository에서 모든 미션들을 불러와, 3개를 랜덤으로 선택한다.
     * MissionMap에 넣어준다.
     * @param member
     */

    @Transactional
    protected void updateMissionForMember(Member member, List<Mission> allMission) {
        // Logic to update missions for a specific member

//        List<Mission> allMission = missionRepository.findAll();
        List<Mission> dailyMissions = selectRandomMissions(allMission, 3);

        // 새로운 미션을 할당한다.
        for (Mission mission : dailyMissions) {
            MissionMap missionMap = MissionMap.builder()
                    .member(member)
                    .mission(mission)
                    .status(MissionStatus.NOT_STARTED)
                    .build();
            missionMapRepository.save(missionMap);
        }
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
    public List<MissionMap> getTodayToDoList(Member member, LocalDate today) {

        return missionMapRepository.findByMemberAndUpdatedDate(member, today);
    }

}
