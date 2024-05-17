package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {


    private final MemberRepository memberRepository;

    public List<Member> sortMemberByPoint() { //entity 추가하면 수정하기
        List<Member> members = memberRepository.findAll();
        members.sort((m1, m2) -> Integer.compare(m2.getPoint(), m1.getPoint()));

        return members;
    }

    public Member searchByName(String name) {
        return memberRepository.findByName(name);
    }
}