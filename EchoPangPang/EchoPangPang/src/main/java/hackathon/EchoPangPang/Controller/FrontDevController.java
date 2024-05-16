package hackathon.EchoPangPang.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/front/dev")
@Slf4j
public class FrontDevController {
    @GetMapping(value = "/LoginPage")
    String getMagicUniv_enroll_01(Model model){
//        model.addAttribute("CurrPath", pathControllerApi.getCurrPath());
        return "LoginPage";
    }

}
