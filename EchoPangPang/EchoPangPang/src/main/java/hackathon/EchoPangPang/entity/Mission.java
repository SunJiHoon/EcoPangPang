package hackathon.EchoPangPang.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Mission 엔티티 클래스.
 * 미션 자체의 정보를 저장
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본키

    @Column(nullable = false)
    private int point = 0; // 미션마다 다른데 통일하면 여기서 설정해버리면 됨

    @Column(nullable = false)
    private String content;
}
