package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class RankingApiController {

    @Autowired
    RankingService rankingService;

    @GetMapping("/Ranking/Sort")
    @ResponseBody
    public List<Member> sortApiController() {
        return rankingService.sortMemberByPoint();
    }

    @GetMapping("/Ranking/Search/{toFind}")
    @ResponseBody
    public List<Optional<Member>> searchApiController(@PathVariable(name = "toFind") String toFind) {
        return rankingService.search(toFind);
    }


}
