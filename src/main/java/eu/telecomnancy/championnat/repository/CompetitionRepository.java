package eu.telecomnancy.championnat.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import eu.telecomnancy.championnat.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{
	
	List<Competition> findByNomCompetition(String nomCompetition);

}