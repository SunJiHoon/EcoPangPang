package hackathon.EchoPangPang.service;

import hackathon.EchoPangPang.dto.RankingDTO;
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

    public List<RankingDTO> sortMemberByPoint() { //entity 추가하면 수정하기
        List<Member> members = memberRepository.findAll();
        members.sort((m1, m2) -> Integer.compare(m2.getPoint(), m1.getPoint()));

        List<RankingDTO> result = new ArrayList<>();
        for (Member member : members) {
            result.add(RankingDTO.of(member));
        }

        return result;
    }

    /*
    값이 없는 경우 [] 반환
     */
    public List<RankingDTO> search(String toFind) {
        List<Optional<Member>> findMember = new ArrayList<>();
        List<RankingDTO> result = new ArrayList<>();
        if (toFind.contains("@")) {
            log.info("email 검색");
            try {
//                log.info("여기");
                findMember.add(memberRepository.findByEmail(toFind));
                log.info(String.valueOf(findMember));
                if (findMember.get(0).equals(Optional.empty())) {
                    findMember = new ArrayList<>();
//                    log.info("이거 함?");
                } else {
                    for (Optional<Member> member : findMember) {
                        if (member.isPresent()) {
                            result.add(RankingDTO.of(member.get()));
                        }
//                        member.ifPresent(value -> result.add(RankingDTO.of(value)));
                    }
//                    log.info("이거 왜 안됨?");
                }
//                log.info("여기2");
                log.info(String.valueOf(findMember));
            } catch (NullPointerException e) {

            }
        }
        else {
            log.info("name 검색");
            findMember = memberRepository.findByName(toFind);
            for (Optional<Member> member : findMember) {
                result.add(RankingDTO.of(member.get()));
            }
        }
        return result;
    }
}
