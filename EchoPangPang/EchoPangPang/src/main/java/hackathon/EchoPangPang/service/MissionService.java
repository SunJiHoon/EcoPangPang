package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.dto.MemberMissionListDTO;
import hackathon.EchoPangPang.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MissionService {

    /***
     * member entity 객체 받아서, 오늘의 할 일, 미션 리스트 불러와서 넘겨주기
     * @param member
     * @return
     */
    public MemberMissionListDTO getTodayToDoList(Member member) {
        return null;
    }
}
