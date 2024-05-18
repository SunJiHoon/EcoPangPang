package hackathon.EchoPangPang.entity;

import hackathon.EchoPangPang.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Code extends BaseEntity {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code", nullable = false)
    private String codeNumber;
}
