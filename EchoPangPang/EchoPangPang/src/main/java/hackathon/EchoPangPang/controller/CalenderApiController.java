package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.dto.CalenderDTO;
import hackathon.EchoPangPang.dto.StampDTO;
import hackathon.EchoPangPang.service.CalenderService;
import hackathon.EchoPangPang.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CalenderApiController {

    @Autowired
    CalenderService calenderService;

    @Autowired
    StampService stampService;

    @GetMapping("/Calender/day/{date}")
    @ResponseBody
    public List<CalenderDTO> missionOfDayController(@PathVariable(name = "date") String date) {
        return calenderService.missionsOfDay(date);
    }

    @GetMapping("/Calender/today")
    @ResponseBody
    public List<CalenderDTO> missionOfTodayController() {
        return calenderService.missionsOfToday();
    }

    @GetMapping("/Calender/month/{yearMonth}")
    @ResponseBody
    public List<StampDTO> stampOfMonth(@PathVariable(name = "yearMonth") String yearMonth) {
        return stampService.stampsOfMonth(yearMonth);
    }

}
