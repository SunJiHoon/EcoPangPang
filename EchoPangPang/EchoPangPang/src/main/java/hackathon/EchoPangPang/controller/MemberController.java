package hackathon.EchoPangPang.controller;


import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.MissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MissionService missionService;

    @Autowired
    public MemberController(MissionService missionService) {
        this.missionService = missionService;
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

        missionService.getTodayToDoList(member);

        return "MainPage";

        //DTO생성하고, model에 담아 넘겨주기.


//        model.addAttribute("todayDate", "5월 17일 금요일");
//
//        List<ToDoItem> todayToDoList = Arrays.asList(
//                new ToDoItem("대중교통 이용하기", MissionStatus.COMPLETED),
//                new ToDoItem("메일함 비우기", MissionStatus.NOT_STARTED),
//                new ToDoItem("분리수거 하기", MissionStatus.FAILED)
//        );
//
//
//        model.addAttribute("todayToDoList", todayToDoList);
//
//
//        model.addAttribute("puangLevel", "Lv.4 청소년 푸앙");
//        model.addAttribute("myPuangName", "푸앙");
//        model.addAttribute("perCentage", "95");
////puangPicture
////        model.addAttribute("puangPicture", "1");
//        model.addAttribute("puangPicture", "/assets/images/" + "puang4" + ".png");
//        return "MainPage";
    }

}
