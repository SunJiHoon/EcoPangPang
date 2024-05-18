package hackathon.EchoPangPang.entity;

import hackathon.EchoPangPang.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Code extends BaseEntity {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code_umber", nullable = false)
    private String codeNumber;
}
