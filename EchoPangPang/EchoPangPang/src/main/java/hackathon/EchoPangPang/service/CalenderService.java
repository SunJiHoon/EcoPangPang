package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.repository.MissionMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private final MissionMapRepository missionMapRepository;

//    public List<MissionMap> missionsOfDay(String date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
//
//        LocalDate localDate = LocalDate.parse(date, formatter);
////        return missionMapRepository.findByCreatedAt(localDate);
//    }
//
//    public List<MissionMap> missionsOfToday() {
//        LocalDate today = LocalDate.now();
//
////        return missionMapRepository.findByCreatedAt(today);
//    }
}