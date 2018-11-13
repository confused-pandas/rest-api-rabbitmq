package eu.telecomnancy.championnat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByIdEquipeAAndIdEquipeB(Long idEquipeA, Long idEquipeB);
}
