package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.service.DummyDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class InitController {

    private final DummyDataService dummyDataService;

    // 생성자 주입을 이용하여 DummyDataService를 주입받습니다.
    public InitController(DummyDataService dummyDataService) {
        this.dummyDataService = dummyDataService;
    }

    @GetMapping("/init")
    public String initDatabase() {
        dummyDataService.insertDummyData();
        return "삽입 성공";
    }
}
