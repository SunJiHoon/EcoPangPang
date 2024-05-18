package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankingApiController {

    @Autowired
    RankingService rankingService;

//    @GetMapping("")
//    @ResponseBody
//    public List<>
}
