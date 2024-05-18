package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.Member;
import hackathon.EchoPangPang.entity.MissionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MissionMapRepository extends JpaRepository<MissionMap, Long> {

    List<MissionMap> findByMemberAndUpdatedDate(Member member, LocalDate today);

    List<MissionMap> findByCreatedDate(LocalDate date);

}