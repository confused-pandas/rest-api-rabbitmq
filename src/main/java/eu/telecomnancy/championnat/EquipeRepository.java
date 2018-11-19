package eu.telecomnancy.championnat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    List<Equipe> findByNomEquipe(String nomEquipe);
    List<Equipe> findByIdEquipe(Long idEquipe);
}
