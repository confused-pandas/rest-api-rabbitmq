package eu.telecomnancy.championnat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Match;
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
	Match one(@PathVariable Long id) {

		return repository.findById(id)
			.orElseThrow(() -> new MatchNotFoundException(id));
	}
	
}
