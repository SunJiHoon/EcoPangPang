package hackathon.EchoPangPang.repository;

import hackathon.EchoPangPang.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Stamp 엔티티를 위한 레포지토리 인터페이스.
 */
@Repository
public interface StampRepository extends JpaRepository<Stamp, Long> {
    Optional<Stamp> findByCreatedDate(LocalDate date);

    @Query("SELECT e FROM Stamp e WHERE YEAR(e.createdDate) = :year AND MONTH(e.createdDate) = :month")
    List<Stamp> findAllByYearAndMonth(@Param("year") int year, @Param("month") int month);
}