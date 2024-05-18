package hackathon.EchoPangPang.entity;

import hackathon.EchoPangPang.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/**
 * MissionMap 엔티티 클래스.
 * 이 클래스는 회원이 수행 중인 미션 정보를 저장
 * 다대다 관계를 매핑
 * 객체 참조 사용:
 * 장점: 더 직관적이고 객체 지향적인 설계, 연관 엔티티에 쉽게 접근 가능.
 * 단점: 복잡성이 증가, 성능 최적화가 필요할 수 있음.
 * 식별자 사용: (private Long memberId)
 * 장점: 단순하고 효율적, 성능 최적화가 더 용이.
 * 단점: 엔티티 간의 관계를 명시적으로 표현하지 못함, 추가 쿼리가 필요할 수 있음.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MissionMap extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member", nullable = false) // member 객체
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission", nullable = false) //mission 객체
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MissionStatus status; // enum, mission의 상태
}


