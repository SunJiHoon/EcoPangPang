package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Mission;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final MissionMapRepository missionMapRepository;

    public List<Mission> missionsOfDay(String date) {
        String[] dates = date.split("-");

    }

    public List<Mission> missionsOfToday() {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

        String formattedDate = today.format(formatter);
    }
}
