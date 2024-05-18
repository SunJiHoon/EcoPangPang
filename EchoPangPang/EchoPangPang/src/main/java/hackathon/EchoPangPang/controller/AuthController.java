package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.dto.ToDoItem;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.MissionStatus;
import hackathon.EchoPangPang.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    /*
    로그인 페이지: 기존 사용자 자격 증명을 입력받기 위한 폼만 제공하며, 별도의 모델 객체가 필요하지 않습니다.
    * */
    @GetMapping(value = "/")
    String getIndexPage(Model model){
        return "redirect:/LoginPage";
    }

    @GetMapping(value = "/LoginPage")
    String getLoginPage(Model model){
        return "LoginPage";
    }

    @PostMapping("/LoginPage")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        try {
            Member member = authService.login(email, password);
            session.setAttribute("member", member);
            return "redirect:/MainPage";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "LoginPage";
        }
    }

    /*
    * 회원가입 페이지: 새로운 사용자 데이터를 입력받기 위해 빈 Member 객체를 모델에 추가하여 폼 데이터 바인딩을 지원합니다.
    * */
    @GetMapping("/SignUp")
    public String register(Model model) {
        model.addAttribute("member", new Member()); // 새로운 멤버 객체를 모델에 추가
        return "SignUp";
    }

    @PostMapping("/SignUp")
    public String register(Member member, Model model, HttpSession session) {
        try {
            authService.register(member);
            session.setAttribute("member", member);
            model.addAttribute("member", new Member()); // 새로운 멤버 객체를 모델에 추가

            return "redirect:/MainPage";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "SignUp";
        }
    }
    @GetMapping("/MainPage")
    public String MainPage(Model model) {
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


    @GetMapping("/Setting")
    public String getSettingPage() {
        return "Setting";
    }
    @GetMapping("/Award")
    public String getAwardPage(){
        return "Award";
    }
    @GetMapping("/Calender")
    public String getCalenderPage() {
        return "Calender";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/LoginPage";
    }

}
