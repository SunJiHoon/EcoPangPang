package hackathon.EchoPangPang.Controller;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    /*
    로그인 페이지: 기존 사용자 자격 증명을 입력받기 위한 폼만 제공하며, 별도의 모델 객체가 필요하지 않습니다.
    * */
    @GetMapping("/login")
    public String login() {
        return "LoginPageTest";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        try {
            Member member = authService.login(email, password);
            session.setAttribute("member", member);
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "LoginPageTest";
        }
    }

    /*
    * 회원가입 페이지: 새로운 사용자 데이터를 입력받기 위해 빈 Member 객체를 모델에 추가하여 폼 데이터 바인딩을 지원합니다.
    * */
    @GetMapping("/register")
    public String register(Model model) {
        Member member = new Member();
        model.addAttribute("member", new Member()); // 새로운 멤버 객체를 모델에 추가
        return "RegisterPage";
    }

    @PostMapping("/register")
    public String register(Member member, Model model) {
        try {
            authService.register(member);
            return "redirect:/home";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "RegisterPage";
        }
    }

    @GetMapping("/home")
    public String renderMainPage() {
        return "MainPageTest";
    }

}
