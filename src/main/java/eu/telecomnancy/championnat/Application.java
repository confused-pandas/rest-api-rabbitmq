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

}