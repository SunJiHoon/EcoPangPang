package hackathon.EchoPangPang.service;


import hackathon.EchoPangPang.dto.MainPageDTO;
import hackathon.EchoPangPang.dto.ToDoItem;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.entity.Puang;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MissionService missionService;


    public MainPageDTO getMainPageInfo(Member member) {

        //missionMapList, 오늘 날짜를 반영해서 List<Mission>을 받아와야함.
        List<MissionMap> missionMapList = missionService.getTodayToDoList(member, LocalDate.now());

        //missionMapList에서 미션 명, 미션 상태 추출해서 toDoItemList 만들기
        List<ToDoItem> toDoItemList = new ArrayList<>();
        for (MissionMap missionMap : missionMapList) {
            toDoItemList.add(new ToDoItem(missionMap.getMission().getId(),
                    missionMap.getMission().getContent(), missionMap.getStatus()));
        }

        //멤버 포인트
        //멤버의 전체 포인트
        int point = member.getPoint();

        //멤버 포인트를 체크하고, 그에 따라 Puang 상태를 반영해야한다
        setPuangGrade(member, point);


        //현재 멤버의 푸앙이 레벨에 따라, 다음 레벨까지의 maxPoint를 얻는다.
        int maxPointsForNextLevel = getMaxPointsForNextLevel(member.getPuang().getGrade());

        //멤버 포인트를 체크하고, 그에 따라 Puang 상태를 반영해야한다
        //현재 다음 level까지의 진화까지, 현재 포인트 상황
//        int currentPointForNextLevel = 0;

        // percentage 계산
        // percentage -> point 값에 따라, 다음 진화까지 진행도를 나타낸다.
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

        //오늘 날짜 가져오기
        LocalDate today = LocalDate.now();
        //원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return MainPageDTO.builder()
                .todayToDoList(toDoItemList)
                .point(point)
                .myPuangName(myPuangName)
                .myPuangLevel(myPuangLevel)
                .puangPicture(puangPicture)
                .percentage(percentage)
                .todayDate(today.format(formatter))
                .build();

    }

    private void setPuangGrade(Member member, int point) {
        if (point >= 0 && point <= 5) {
            member.getPuang().setGrade(Puang.Grade.EGG);
        } else if (point >= 6 && point <= 10) {
            member.getPuang().setGrade(Puang.Grade.BABY);
        } else if (point >= 11 && point <= 15) {
            member.getPuang().setGrade(Puang.Grade.CHILD);
        } else if (point >= 16 && point <= 25) {
            member.getPuang().setGrade(Puang.Grade.TEENAGER);
        } else if (point >= 26 && point <= 35) {
            member.getPuang().setGrade(Puang.Grade.ADULT);
        } else {
            member.getPuang().setGrade(Puang.Grade.GUARDIAN);
        }
    }

    // 다음 레벨에 필요한 점수를 얻는 메서드
    private int getMaxPointsForNextLevel(Puang.Grade puangGrade) {
        switch (puangGrade) {
            case EGG:
                return 5;
            case BABY:
                return 10;
            case CHILD:
                return 15;
            case TEENAGER:
                return 25;
            case ADULT:
                return 35;
            case GUARDIAN:
                return 1000;
            default:
                throw new IllegalStateException("Unexpected value: " + puangGrade);
        }
    }
}
