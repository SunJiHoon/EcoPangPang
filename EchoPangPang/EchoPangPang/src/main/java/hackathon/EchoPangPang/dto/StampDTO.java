package hackathon.EchoPangPang.dto;

import hackathon.EchoPangPang.entity.Stamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StampDTO {
    private int date;

    public static StampDTO of(Stamp stamp) {
        return new StampDTO(stamp.getCreatedDate().getDayOfMonth());
    }
}
