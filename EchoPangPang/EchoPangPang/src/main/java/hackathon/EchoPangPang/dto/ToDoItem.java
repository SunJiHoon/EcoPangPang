package hackathon.EchoPangPang.dto;

import hackathon.EchoPangPang.entity.MissionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ToDoItem {
    private Long id;
    private String description;
    private MissionStatus status;

    public ToDoItem(Long id, String description, MissionStatus status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }
}
