package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.MissionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionMapRepository extends JpaRepository<MissionMap, Long> {
    List<MissionMap> findByMemberAndCreatedAtBetween(Member member, LocalDateTime startDate, LocalDateTime endDate);
}