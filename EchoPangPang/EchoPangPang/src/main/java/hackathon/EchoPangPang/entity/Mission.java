package hackathon.EchoPangPang.entity;

import hackathon.EchoPangPang.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Mission 엔티티 클래스.
 * 미션 자체의 정보를 저장
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id; // 기본키

    @Column(name = "point", nullable = false)
    private int point = 0; // 미션마다 다른데 통일하면 여기서 default 설정해버리면 됨

    @Column(name = "content", nullable = false)
    private String content; // 미션 내용

}
