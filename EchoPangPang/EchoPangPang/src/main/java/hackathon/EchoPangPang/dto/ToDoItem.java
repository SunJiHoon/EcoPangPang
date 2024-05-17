package hackathon.EchoPangPang.dto;

import hackathon.EchoPangPang.entity.MissionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDoItem {
    private String description;
    private MissionStatus status;

    public ToDoItem(String description, MissionStatus status) {
        this.description = description;
        this.status = status;
    }
}
