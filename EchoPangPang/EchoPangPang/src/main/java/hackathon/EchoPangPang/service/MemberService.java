package hackathon.EchoPangPang.service;


import hackathon.EchoPangPang.dto.MainPageDTO;
import hackathon.EchoPangPang.dto.ToDoItem;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.MissionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MissionService missionService;

    @Autowired
    public MemberService(MissionService missionService) {
        this.missionService = missionService;
    }


    public MainPageDTO getMainPageInfo(Member member) {

        //missionMapList
        List<MissionMap> missionMapList = missionService.getTodayToDoList(member);

        //missionMapList에서 미션 명, 미션 상태 추출해서 toDoItemList 만들기
        List<ToDoItem> toDoItemList = new ArrayList<>();
        for (MissionMap missionMap : missionMapList) {
            toDoItemList.add(new ToDoItem(missionMap.getMission().getContent(), missionMap.getStatus()));
        }

        //멤버 포인트
        int point = member.getPoint();

        // percentage -> point 값에 따라, 다음 진화까지 진행도를 나타낸다.
        int maxPointsForNextLevel;
        switch (member.getPuang().getGrade()) {
            case EGG -> maxPointsForNextLevel = 5; // EGG -> BABY로 진화하기 위한 최대 포인트 예시 값
            case BABY -> maxPointsForNextLevel = 10; // BABY -> CHILD
            case CHILD -> maxPointsForNextLevel = 15; // CHILD -> TEENAGER
            case TEENAGER -> maxPointsForNextLevel = 25; // TEENAGER -> ADULT
            case ADULT -> maxPointsForNextLevel = 35; // ADULT -> GUARDIAN
            case GUARDIAN -> maxPointsForNextLevel = 1000;
            default -> throw new IllegalStateException("Unexpected value: " + member.getPuang().getGrade());
        }

        // percentage 계산
        int percentage = (point * 100) / maxPointsForNextLevel;

        //푸앙이 정보 (이름, 레벨 명, 푸앙이 사진)
        String myPuangName = member.getPuang().getName();
        String puangPicture = "";
        String myPuangLevel = "";
        switch (member.getPuang().getGrade()) {
            case EGG:
                myPuangLevel = "Lv.1 egg 푸앙";
                puangPicture = "/assets/images/" + "puang1" + ".png";
                break;
            case BABY:
                myPuangLevel = "Lv.2 baby 푸앙";
                puangPicture = "/assets/images/" + "puang2" + ".png";
                break;
            case CHILD:
                myPuangLevel = "Lv.3 child 푸앙";
                puangPicture = "/assets/images/" + "puang3" + ".png";
                break;
            case TEENAGER:
                myPuangLevel = "Lv.4 teenager 푸앙";
                puangPicture = "/assets/images/" + "puang4" + ".png";
                break;
            case ADULT:
                myPuangLevel = "Lv.5 adult 푸앙";
                puangPicture = "/assets/images/" + "puang5" + ".png";
                break;
            case GUARDIAN:
                myPuangLevel = "Lv.6 guardian 푸앙";
                puangPicture = "/assets/images/" + "puang6" + ".png";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + member.getPuang().getGrade());
        }


        return MainPageDTO.builder()
                .todayToDoList(toDoItemList)
                .point(point)
                .myPuangName(myPuangName)
                .myPuangLevel(myPuangLevel)
                .puangPicture(puangPicture)
                .percentage(percentage)
                .build();

    }
}
