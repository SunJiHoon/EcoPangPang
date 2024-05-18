package hackathon.EchoPangPang.entity;

import hackathon.EchoPangPang.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Member 엔티티 클래스
 * 이 클래스는 회원 정보를 저장하는 엔티티로 사용.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // 기본키

    @Column(name = "name", nullable = false)
    private String name; // 이름

    @Column(name = "email", nullable = false)
    private String email; // 이메일

    @Setter
    @Column(name = "password", nullable = false)
    private String password; // 비밀번호

    @Column(name = "point", nullable = false)
    private int point; // 가지고 있는 포인트, 기본값 0

    @Embedded
    private Puang puang;

    public void increasePoint(int point) {
        this.point += point;
    }

    public void decreasePoint(int point) {
        this.point -= point;
    }
}
