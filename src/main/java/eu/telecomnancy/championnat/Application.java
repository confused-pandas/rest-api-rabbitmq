package eu.telecomnancy.championnat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public long[] list = { 0L, 1L, 2L, 3L };

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(MatchRepository repository) {
		return (args) -> {
			// save a couple of matches
			repository.save(new Match("PSG", "OM", 1,0, 0L, 1L, Statut.PREVU));
			repository.save(new Match("ASNL", "Nîmes Olympique", 2,1,2L,3L, Statut.FINI));
			repository.save(new Match("FC Barcelone", "Real Madrid", 3,3,4L,5L, Statut.ENCOURS));
			repository.save(new Match("AS Roma", "Naples", 4,2,6L,7L, Statut.PAUSE));
			repository.save(new Match("Caen", "Toulouse", 5,3,8L,9L, Statut.REPORTE));

			// fetch all matches
			log.info("Match found with findAll():");
			log.info("-------------------------------");
			for (Match match : repository.findAll()) {
				log.info(match.toString());
			}
			log.info("");

			// fetch an individual match by ID
			repository.findById(1L)
				.ifPresent(match -> {
					log.info("Match found with findById(1L):");
					log.info("--------------------------------");
					log.info(match.toString());
					log.info("");
				});

			// fetch customers by last name
			log.info("Match found with findByIdEquipeAAndIdEquipeB(2L,3L):");
			log.info("--------------------------------------------");
			repository.findByIdEquipeAAndIdEquipeB(2L,3L).forEach(match -> {
				log.info(match.toString());
			});
			log.info("");
		};
	}
	
	
	@Bean
	public CommandLineRunner demoEquipe(EquipeRepository equipeRepository) {
		return (args) -> {
			// save a couple of equipe
			
			equipeRepository.save(new Equipe("PSG", "Paris", 0));
			equipeRepository.save(new Equipe("OM", "Marseille", 0));
			equipeRepository.save(new Equipe("ASNL", "Nancy", 0));
			equipeRepository.save(new Equipe("Nîmes Olympique", "Nîmes", 0));
			equipeRepository.save(new Equipe("FC Barcelone", "Barcelone", 0));
			equipeRepository.save(new Equipe("Real Madrid", "Madrid", 0));
			equipeRepository.save(new Equipe("AS Roma", "Rome", 0));
			equipeRepository.save(new Equipe("Naples", "Naples", 0));
			equipeRepository.save(new Equipe("Caen", "Caen", 0));
			equipeRepository.save(new Equipe("Toulouse", "Toulouse", 0));

			// fetch all equipe
			log.info("Equipe found with findAll():");
			log.info("-------------------------------");
			for (Equipe equipe : equipeRepository.findAll()) {
				log.info(equipe.toString());
			}
			log.info("");

			// fetch an individual equipe by ID
			log.info("Equipe found with findByIdEquipe(15L)");
			log.info("-------------------------------");
			log.info(equipeRepository.findByIdEquipe(15L).toString());
			log.info("");
				

			// fetch customers by name
			log.info("Equipe found with findByNomEquipe(name):");
			log.info("--------------------------------------------");
			equipeRepository.findByNomEquipe("Toulouse").forEach(equipe -> {
				log.info(equipe.toString());
			});
			log.info("");
		};
	}
	
	@Bean
	public CommandLineRunner demoCompet(CompetitionRepository competitionRepository) {
		return (args) -> {
			// save a couple of competition
			
			competitionRepository.save(new Competition("Ligue 1", 4, list, list));

			// fetch all competition
			log.info("Competition found with findAll():");
			log.info("-------------------------------");
			for (Competition competition : competitionRepository.findAll()) {
				log.info(competition.toString());
			}
			log.info("");

			// fetch an individual compet by ID
			competitionRepository.findById(1L)
				.ifPresent(competition -> {
					log.info("Competition found with findById(1L):");
					log.info("--------------------------------");
					log.info(competition.toString());
					log.info("");
				});

			// fetch customers by name
			log.info("Competition found with findByNomCompetition(name):");
			log.info("--------------------------------------------");
			competitionRepository.findByNomCompetition("Ligue1").forEach(competition -> {
				log.info(competition.toString());
			});
			log.info("");
		};
	}
	


}