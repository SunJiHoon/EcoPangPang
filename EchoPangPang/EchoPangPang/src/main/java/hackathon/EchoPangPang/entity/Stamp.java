package hackathon.EchoPangPang.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Stamp 엔티티 클래스.
 * 캘린더에 찍힐 도장 정보를 저장.
 */
@Entity
@Data
@NoArgsConstructor
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stamp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "created_date", nullable = false)
    private LocalDate created_date;
}
