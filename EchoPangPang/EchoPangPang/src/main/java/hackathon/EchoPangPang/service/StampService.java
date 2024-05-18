package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.dto.StampDTO;
import hackathon.EchoPangPang.entity.Stamp;
import hackathon.EchoPangPang.repository.StampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StampService {

    private final StampRepository stampRepository;

    public List<StampDTO> stampsOfMonth(String yearMonth) {
        String[] date = yearMonth.split("-");
        int year = Integer.parseInt("20" + date[0]);
        int month = Integer.parseInt(date[1]);
        List<Stamp> stamps = stampRepository.findAllByYearAndMonth(year, month);

        List<StampDTO> result = new ArrayList<>();
        for (Stamp stamp : stamps) {
            result.add(StampDTO.of(stamp));
        }

        return result;
    }

}
