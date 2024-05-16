package hackathon.EchoPangPang.Service;

import hackathon.EchoPangPang.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RankingService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> sortMemberByPoint() { //entity 추가하면 수정하기
        List<Member> members = memberRepository.findAll();
        Collections.sort(members, new Comparator<Member>() {
            public int compare(Member m1, Member m2) {
                return Integer.compare(m2.getPoint(), m1.getPoint());
            }
        });

        return members;
    }

    public Member searchByName(String name) {
        return memberRepository.findByName(name);
    }
}
