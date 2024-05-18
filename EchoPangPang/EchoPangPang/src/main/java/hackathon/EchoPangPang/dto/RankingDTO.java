package hackathon.EchoPangPang.dto;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.Puang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {
    private String name;
    private int point;
    private String puangName;
    private Puang.Grade puangGrade;

    public static RankingDTO of(Member member) {
        return new RankingDTO(member.getName(), member.getPoint(), member.getPuang().getName(), member.getPuang().getGrade());
    }
}
