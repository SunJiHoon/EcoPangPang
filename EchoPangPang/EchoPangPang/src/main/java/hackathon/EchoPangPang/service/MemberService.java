package hackathon.EchoPangPang.service;


import hackathon.EchoPangPang.dto.MainPageDTO;
import hackathon.EchoPangPang.dto.ToDoItem;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.entity.Puang;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MissionService missionService;


    public MainPageDTO getMainPageInfo(Member member) {

        //missionMapList, 오늘 날짜를 반영해서 List<Mission>을 받아와야함.
        List<MissionMap> missionMapList = missionService.getTodayToDoList(member, LocalDate.now());

        //missionMapList에서 미션 명, 미션 상태 추출해서 toDoItemList 만들기
        List<ToDoItem> toDoItemList = new ArrayList<>();
        for (MissionMap missionMap : missionMapList) {
            log.info(String.valueOf(missionMap.getStatus()));
            toDoItemList.add(new ToDoItem(missionMap.getMission().getId(),
                    missionMap.getMission().getContent(), missionMap.getStatus()));
        }

        //멤버 포인트
        //멤버 포인트를 체크하고, 그에 따라 Puang 상태를 반영해야한다
        int point = member.getPoint();

        // percentage -> point 값에 따라, 다음 진화까지 진행도를 나타낸다.
        int maxPointsForNextLevel = getMaxPointsForNextLevel(member.getPuang().getGrade());

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


        // 현재 날짜와 시간을 한국 표준시(KST)로 가져오기
        ZonedDateTime nowInKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        // 한국 시간에서의 현재 날짜 가져오기
        LocalDate todayInKST = nowInKST.toLocalDate();

        // 원하는 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = todayInKST.format(formatter);


//        //오늘 날짜 가져오기
//        LocalDate today = LocalDate.now();
//        //원하는 형식으로 포맷팅
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return MainPageDTO.builder()
                .todayToDoList(toDoItemList)
                .point(point)
                .myPuangName(myPuangName)
                .myPuangLevel(myPuangLevel)
                .puangPicture(puangPicture)
                .percentage(percentage)
                .todayDate(formattedDate)
                .build();

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
