package hackathon.EchoPangPang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Code {
    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code", nullable = false)
    private String codeNumber;
}
