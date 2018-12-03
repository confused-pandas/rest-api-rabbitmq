package eu.telecomnancy.championnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.telecomnancy.championnat.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByIdEquipeAAndIdEquipeB(Long idEquipeA, Long idEquipeB);
}
