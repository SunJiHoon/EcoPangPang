package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Member 엔티티를 위한 레포지토리 인터페이스.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}

