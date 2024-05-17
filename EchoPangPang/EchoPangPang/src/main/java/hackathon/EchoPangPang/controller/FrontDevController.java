package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.entity.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/front/dev")
@Slf4j
public class FrontDevController {
    @GetMapping(value = "/LoginPage")
    String getLoginPage(Model model){
//        model.addAttribute("CurrPath", pathControllerApi.getCurrPath());
        return "LoginPage";
    }

    @GetMapping(value = "/MainPage")
    String getMainPage(Model model){
        model.addAttribute("todayDate", "5월 17일 금요일");

        List<ToDoItem> todayToDoList = Arrays.asList(
                new ToDoItem("대중교통 이용하기", MissionStatus.COMPLETED),
                new ToDoItem("메일함 비우기", MissionStatus.NOT_STARTED),
                new ToDoItem("분리수거 하기", MissionStatus.FAILED)
        );


        model.addAttribute("todayToDoList", todayToDoList);


        model.addAttribute("puangLevel", "Lv.4 청소년 푸앙");
        model.addAttribute("myPuangName", "푸앙");
        model.addAttribute("perCentage", "95");
//puangPicture
//        model.addAttribute("puangPicture", "1");
        model.addAttribute("puangPicture", "/assets/images/" + "puang4"+ ".png");



        return "MainPage";
    }

    @GetMapping(value = "/SignUp")
    String getSignUpPage(Model model){
//        model.addAttribute("CurrPath", pathControllerApi.getCurrPath());
        return "SignUp";
    }

    @GetMapping(value = "/Calender")
    String getCalenderPage(Model model){
//        model.addAttribute("CurrPath", pathControllerApi.getCurrPath());
        return "Calender";
    }



}

class ToDoItem {
    private String description;
    private MissionStatus status;

    public ToDoItem(String description, MissionStatus status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus check) {
        this.status = check;
    }
}
