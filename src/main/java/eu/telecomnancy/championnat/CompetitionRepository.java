package eu.telecomnancy.championnat;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long>{
	
	List<Competition> findByNomCompetition(String nomCompetition);

}