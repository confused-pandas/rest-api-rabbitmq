package eu.telecomnancy.championnat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.telecomnancy.championnat.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    List<Equipe> findByNomEquipe(String nomEquipe);
    List<Equipe> findByIdEquipe(Long idEquipe);
}
