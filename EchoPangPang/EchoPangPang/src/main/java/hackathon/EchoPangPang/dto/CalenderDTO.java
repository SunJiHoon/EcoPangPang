package hackathon.EchoPangPang.dto;

import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.entity.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalenderDTO {
    private MissionStatus missionStatus;
    private String content;
    private int point;

    public static CalenderDTO of(MissionMap missionMap) {
        return new CalenderDTO(missionMap.getStatus(), missionMap.getMission().getContent(), missionMap.getMission().getPoint());
    }
}
