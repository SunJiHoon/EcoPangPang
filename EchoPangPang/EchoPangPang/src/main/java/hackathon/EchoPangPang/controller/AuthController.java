package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    /*
    로그인 페이지: 기존 사용자 자격 증명을 입력받기 위한 폼만 제공하며, 별도의 모델 객체가 필요하지 않습니다.
    * */
    @GetMapping(value = "/")
    String getIndexPage(Model model) {
        return "redirect:/LoginPage";
    }

    @GetMapping(value = "/LoginPage")
    String getLoginPage(Model model) {
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/LoginPage";
    }

}
