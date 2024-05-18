package hackathon.EchoPangPang.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageDTO {

    private List<ToDoItem> todayToDoList;
    private int point;
    private int percentage;
    private String myPuangName;
    private String myPuangLevel;
    private String puangPicture;
    private String todayDate;
}
