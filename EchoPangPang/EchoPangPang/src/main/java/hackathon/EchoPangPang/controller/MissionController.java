package hackathon.EchoPangPang.controller;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.service.MissionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/mission")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PatchMapping("/check")
    public ResponseEntity<?> checkBox(@RequestBody Map<String, Long> requestBody, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        //체크박스 누르면, 미션 상태 바꾸기
        missionService.checkMissionStatus(requestBody.get("id"), member);

        //체크박스 누르면, 사용자 포인트 증가
        missionService.checkMissionPoint(requestBody.get("id"), member);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/uncheck")
    public ResponseEntity<?> uncheckBox(@RequestBody Map<String, Long> requestBody, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        missionService.uncheckMissionStatus(requestBody.get("id"), member);
        missionService.uncheckMissionPoint(requestBody.get("id"), member);
        return ResponseEntity.ok().build();
    }

}
