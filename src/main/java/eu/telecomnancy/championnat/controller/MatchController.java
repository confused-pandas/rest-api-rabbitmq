package eu.telecomnancy.championnat.controller;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import eu.telecomnancy.championnat.Match;
import eu.telecomnancy.championnat.controller.MatchController;
import eu.telecomnancy.championnat.exception.MatchNotFoundException;
import eu.telecomnancy.championnat.repository.MatchRepository;

@RestController
public class MatchController {
	
	private final MatchRepository repository;
	
	MatchController(MatchRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/matches")
	List<Match> all() {
		return repository.findAll();
	}	
	
	@GetMapping("/matches/{id}")
	Resource<Match> one(@PathVariable Long id) {

		Match match = repository.findById(id)
			.orElseThrow(() -> new MatchNotFoundException(id));

		return new Resource<>(match,
			linkTo(methodOn(MatchController.class).one(id)).withSelfRel(),
			linkTo(methodOn(MatchController.class).all()).withRel("employees"));
	}
	
	
	
}
