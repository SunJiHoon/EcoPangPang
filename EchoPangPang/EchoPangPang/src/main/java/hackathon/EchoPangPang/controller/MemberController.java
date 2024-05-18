package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.dto.MainPageDTO;
import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /***
     * 로그인 후, session에서 사용자 정보 얻어서
     * todayToDoList와 푸앙이 관련 정보 모델에 담아 MainPage에 넘겨준다.
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/MainPage")
    public String MainPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        MainPageDTO mainPageDTO = memberService.getMainPageInfo(member);

        model.addAttribute("mainPageDTO", mainPageDTO);

        return "MainPage";
    }

}
