package hackathon.EchoPangPang.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String to;
    private String subject;
    private String body;
}
