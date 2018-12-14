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

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;



@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public long[] listEquipe = { 0L, 1L, 2L, 3L };
	public long[] listMatch = { 0L, 1L, 2L };
	public long[] listEquipe2 = { 0L, 10L, 11L, 12L };
	public long[] listMatch2 = { 3L, 4L, 5L };
	
    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner initDatabase(MatchRepository repository, EquipeRepository equipeRepository, CompetitionRepository competitionRepository) {
		return (args) -> {
			// save a couple of matches
			repository.save(new Match("PSG", "OM", 1,0, 11L, 12L, Statut.FINI));
			repository.save(new Match("ASNL", "Nîmes Olympique", 2,1,2L,3L, Statut.FINI));
			repository.save(new Match("ASNL", "PSG", 1,3,2L,0L, Statut.FINI));
			repository.save(new Match("PSG", "OL", 3,0, 0L, 10L, Statut.FINI));
			repository.save(new Match("LOSC", "Montpellier", 4,1,11L,12L, Statut.FINI));
			repository.save(new Match("LOSC", "PSG", 2,5,11L,0L, Statut.FINI));
			repository.save(new Match("FC Barcelone", "Real Madrid", 3,3,4L,5L, Statut.ENCOURS));
			repository.save(new Match("AS Roma", "Naples", 4,2,6L,7L, Statut.PAUSE));
			repository.save(new Match("Caen", "Toulouse", 5,3,8L,9L, Statut.REPORTE));
			repository.save(new Match("DFCO", "ASSE", 5,3,8L,9L, Statut.PREVU));
			
			equipeRepository.save(new Equipe("PSG", "Paris", 42));
			equipeRepository.save(new Equipe("OM", "Marseille", 20));
			equipeRepository.save(new Equipe("ASNL", "Nancy", 5));
			equipeRepository.save(new Equipe("Nîmes Olympique", "Nîmes", 9));
			equipeRepository.save(new Equipe("FC Barcelone", "Barcelone", 45));
			equipeRepository.save(new Equipe("Real Madrid", "Madrid", 48));
			equipeRepository.save(new Equipe("AS Roma", "Rome", 6));
			equipeRepository.save(new Equipe("Naples", "Naples", 12));
			equipeRepository.save(new Equipe("Caen", "Caen", 6));
			equipeRepository.save(new Equipe("Toulouse", "Toulouse", 12));
			equipeRepository.save(new Equipe("OL", "Lyon", 34));
			equipeRepository.save(new Equipe("LOSC", "Lille", 28));
			equipeRepository.save(new Equipe("Montpellier", "Montpellier", 3));
			equipeRepository.save(new Equipe("ASSE", "Saint-Etienne", 28));
			equipeRepository.save(new Equipe("DFCO", "Dijon", 21));
			
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


    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

}