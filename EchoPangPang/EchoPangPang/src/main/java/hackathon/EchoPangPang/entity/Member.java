package hackathon.EchoPangPang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Member 엔티티 클래스
 * 이 클래스는 회원 정보를 저장하는 엔티티로 사용.
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
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
    private int point = 0; // 가지고 있는 포인트, 기본값 0

    @Embedded
    private Puang puang;

    @Column(name = "created_time",nullable = false)
    private LocalDateTime created_time = LocalDateTime.now(); // 생성시각인데 필요 없으면 삭제
}
