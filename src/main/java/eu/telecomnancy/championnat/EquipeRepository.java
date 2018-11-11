package eu.telecomnancy.championnat;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EquipeRepository extends CrudRepository<Equipe, Long> {

	List<Equipe> findByName(String name);
}
