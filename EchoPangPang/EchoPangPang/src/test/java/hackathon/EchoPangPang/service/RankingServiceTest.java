package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RankingServiceTest {

    @Autowired RankingService rankingService;

    @Test
    void sortMemberByPoint() {
        List<Member> members = rankingService.sortMemberByPoint();
        for (Member member : members) {
            System.out.println("member = " + member.getPoint());
        }
    }

    @Test
    void search() {
        System.out.println("rankingService = " + rankingService.search("can2can"));
    }
}