package hackathon.EchoPangPang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * MissionMap 엔티티의 복합 키 클래스.
 * 이 클래스는 회원과 미션의 복합 키를 정의한다.
 * Serializable => 복합키를 사용할 때 반드시 필요 (JPA 명세에 따름)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionMapPK implements Serializable {
    private Long memberId; // Member 엔티티 id 필드
    private Long missionId; // Mission 엔티티 id 필드
}
