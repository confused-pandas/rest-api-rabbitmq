package eu.telecomnancy.championnat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import eu.telecomnancy.championnat.repository.CompetitionRepository;
import eu.telecomnancy.championnat.repository.EquipeRepository;
import eu.telecomnancy.championnat.repository.MatchRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public long[] listEquipe = { 1L, 2L, 3L, 4L };
	public long[] listMatch = {  1L, 2L, 3L };
	public long[] listEquipe2 = { 1L, 11L, 12L, 13L };
	public long[] listMatch2 = { 4L, 5L, 6L };

    
	// Matches des équipes
	public long[] listMatchPsg = {1L, 3L, 4L, 6L};
	public long[] listMatchOm = {1L};
	public long[] listMatchAsnl = {2L,3L};
	public long[] listMatchNimes = {2L};
	public long[] listMatchBarca = {7L};
	public long[] listMatchReal = {7L};
	public long[] listMatchRoma = {8L};
	public long[] listMatchNaples = {8L};
	public long[] listMatchCaen = {9L};
	public long[] listMatchToulouse = {9L};
	public long[] listMatchOl = {4L};
	public long[] listMatchLosc = {5L, 6L};
	public long[] listMatchMontpel = {5L};
	public long[] listMatchAsse = {10L};
	public long[] listMatchDfco = {10L};

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
        
	}

	@Bean
	public CommandLineRunner initDatabase(MatchRepository repository, EquipeRepository equipeRepository, CompetitionRepository competitionRepository) {
		return (args) -> {
			
			
			
			// save a couple of teams
			equipeRepository.save(new Equipe("PSG", "Paris", 42, listMatchPsg)); //1
			equipeRepository.save(new Equipe("OM", "Marseille", 20, listMatchOm)); //2
			equipeRepository.save(new Equipe("ASNL", "Nancy", 5, listMatchAsnl)); 
			equipeRepository.save(new Equipe("Nîmes Olympique", "Nîmes", 9, listMatchNimes));
			equipeRepository.save(new Equipe("FC Barcelone", "Barcelone", 45, listMatchBarca)); //5
			equipeRepository.save(new Equipe("Real Madrid", "Madrid", 48, listMatchReal)); //6
			equipeRepository.save(new Equipe("AS Roma", "Rome", 6, listMatchRoma));
			equipeRepository.save(new Equipe("Naples", "Naples", 12, listMatchNaples)); //8
			equipeRepository.save(new Equipe("Caen", "Caen", 6, listMatchCaen));
			equipeRepository.save(new Equipe("Toulouse", "Toulouse", 12, listMatchToulouse)); //10
			equipeRepository.save(new Equipe("OL", "Lyon", 34, listMatchOl));
			equipeRepository.save(new Equipe("LOSC", "Lille", 28, listMatchLosc)); //12
			equipeRepository.save(new Equipe("Montpellier", "Montpellier", 3, listMatchMontpel)); //13
			equipeRepository.save(new Equipe("ASSE", "Saint-Etienne", 28, listMatchAsse));
			equipeRepository.save(new Equipe("DFCO", "Dijon", 21, listMatchDfco));
			
			// save a couple of matches
			repository.save(new Match("PSG", "OM", 1,0, 1L, 2L, Statut.FINI)); //1
			repository.save(new Match("ASNL", "Nîmes Olympique", 2,1,3L,4L, Statut.FINI));
			repository.save(new Match("ASNL", "PSG", 1,3,3L,1L, Statut.FINI));
			repository.save(new Match("PSG", "OL", 3,0, 1L, 11L, Statut.FINI));
			repository.save(new Match("LOSC", "Montpellier", 4,1,12L,13L, Statut.FINI)); //5
			repository.save(new Match("LOSC", "PSG", 2,5,12L,1L, Statut.FINI));
			repository.save(new Match("FC Barcelone", "Real Madrid", 3,3,5L,6L, Statut.ENCOURS));
			repository.save(new Match("AS Roma", "Naples", 4,2,6L,7L, Statut.PAUSE)); //8
			repository.save(new Match("Caen", "Toulouse", 5,3,8L,9L, Statut.REPORTE));
			repository.save(new Match("DFCO", "ASSE", 5,3,14L,15L, Statut.PREVU)); //10
			repository.save(new Match("OM", "Nîmes Olympique", 2,0,2L,4L, Statut.FINI));
			

			
			competitionRepository.save(new Competition("Ligue 1", 3, listEquipe, listMatch));
			competitionRepository.save(new Competition("Ligue 2", 3, listEquipe2, listMatch2));

			// fetch all matches
			log.info("Match found with findAll():");
			log.info("-------------------------------");
			for (Match match : repository.findAll()) {
				log.info(match.toString());
			}
			log.info("");
			
			// fetch all equipe
			log.info("Equipe found with findAll():");
			log.info("-------------------------------");
			for (Equipe equipe : equipeRepository.findAll()) {
				log.info(equipe.toString());
			}
			log.info("");
			
			log.info("Competition found with findAll():");
			log.info("-------------------------------");
			for (Competition competition : competitionRepository.findAll()) {
				log.info(competition.toString());
			}
			log.info("");

			
		};
	}
}