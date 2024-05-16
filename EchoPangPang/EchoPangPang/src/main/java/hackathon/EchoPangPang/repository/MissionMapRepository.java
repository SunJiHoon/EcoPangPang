package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.MissionMap;
import hackathon.EchoPangPang.entity.MissionMapPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionMapRepository extends JpaRepository<MissionMap, MissionMapPK> {
}