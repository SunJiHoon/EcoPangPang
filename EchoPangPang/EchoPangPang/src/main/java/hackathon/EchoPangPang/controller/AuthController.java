package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.dto.LoginDTO;
import hackathon.EchoPangPang.dto.RegisterDTO;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    /*
    로그인 페이지
    * */
    @GetMapping("/")
    String getIndexPage(Model model) {
        return "redirect:/LoginPage";
    }

    @GetMapping("/LoginPage")
    String getLoginPage(Model model) {
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

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/LoginPage";
    }


}