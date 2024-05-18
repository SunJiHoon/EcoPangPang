package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.dto.LoginDTO;
import hackathon.EchoPangPang.dto.RegisterDTO;
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

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    /*
    로그인 페이지
    * */
    @GetMapping("/")
    String getIndexPage(Model model){
        return "redirect:/LoginPage";
    }

    @GetMapping("/LoginPage")
    String getLoginPage(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "LoginPage";
    }

    @PostMapping("/LoginPage")
    public String login(
            LoginDTO loginDTO,
            HttpSession session,
            Model model
    ) {
        try {
            Member member = authService.login(loginDTO);
            session.setAttribute("member", member);
            return "redirect:/MainPage";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "LoginPage";
        }
    }

    /*
    * 회원가입 페이지
    * */
    @GetMapping("/SignUp")
    public String getSignUpForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "SignUp";
    }

    @PostMapping("/SignUp")
    public String register(
            RegisterDTO registerDTO,
            Model model,
            HttpSession session
    ) {
        try {
            Member member = authService.register(registerDTO);
            session.setAttribute("member", member);
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
        model.addAttribute("myPoint", "10");

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


//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/LoginPage";
//    }



}
