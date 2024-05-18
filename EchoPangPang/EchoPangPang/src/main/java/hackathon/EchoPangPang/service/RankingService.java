package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankingService {


    private final MemberRepository memberRepository;

    public List<Member> sortMemberByPoint() { //entity 추가하면 수정하기
        List<Member> members = memberRepository.findAll();
        members.sort((m1, m2) -> Integer.compare(m2.getPoint(), m1.getPoint()));

        return members;
    }

    /*
    값이 없는 경우
    email: [Optional.empty] 반환
    name: [] 반환
     */
    public List<Optional<Member>> search(String toFind) {
        List<Optional<Member>> findMember = new ArrayList<>();
        if (toFind.contains("@")) {
            log.info("email 검색");
            try {
                log.info("여기");
                findMember.add(memberRepository.findByEmail(toFind));
                log.info("여기2");
            } catch (NullPointerException e) {
                log.info("들어감?");
            }
        }
        else {
            log.info("name 검색");
            findMember = memberRepository.findByName(toFind);
        }
        return findMember;
    }
}
