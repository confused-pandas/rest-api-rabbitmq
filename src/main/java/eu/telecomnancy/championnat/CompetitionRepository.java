package eu.telecomnancy.championnat;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionRepository extends CrudRepository<Competition, Long>{
	
	List<Competition> findByName(String nomCompetition);

}
