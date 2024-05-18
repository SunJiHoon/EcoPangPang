package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.dto.CalenderDTO;
import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final MissionMapRepository missionMapRepository;

    public List<CalenderDTO> missionsOfDay(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");

        LocalDate localDate = LocalDate.parse(date, formatter);
        List<MissionMap> findMissions = missionMapRepository.findByCreatedDate(localDate);
        List<CalenderDTO> result = new ArrayList<>();

        for (MissionMap findMission : findMissions) {
            result.add(CalenderDTO.of(findMission));
        }

        return result;
    }

    public List<CalenderDTO> missionsOfToday() {
        LocalDate today = LocalDate.now();

        List<MissionMap> findMissions = missionMapRepository.findByCreatedDate(today);
        List<CalenderDTO> result = new ArrayList<>();

        for (MissionMap findMission : findMissions) {
            result.add(CalenderDTO.of(findMission));
        }

        return result;
    }
}