package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.Mission;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mission 엔티티를 위한 레포지토리 인터페이스.
 */
@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query(value = "SELECT m FROM Mission m ORDER BY function('RAND')")
    List<Mission> findRandomMissions(Pageable Pageable);

}