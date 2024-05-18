package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Code 엔티티를 위한 레포지토리 인터페이스.
 */
@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    // 이메일로 코드 찾아줌
    Optional<Code> findCodeByEmail(String email);
}