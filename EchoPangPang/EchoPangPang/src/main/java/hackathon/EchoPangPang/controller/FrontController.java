package hackathon.EchoPangPang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    @GetMapping("/Setting")
    public String getSettingPage() {
        return "Setting";
    }

    @GetMapping("/Ranking")
    public String getAwardPage() {
        return "Ranking";
    }

    @GetMapping("/Calender")
    public String getCalenderPage() {
        return "Calender";
    }

}
