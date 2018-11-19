package eu.telecomnancy.championnat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByIdEquipeAAndIdEquipeB(Long idEquipeA, Long idEquipeB);
}
