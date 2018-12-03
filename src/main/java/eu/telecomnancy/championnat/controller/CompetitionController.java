package eu.telecomnancy.championnat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Competition;
import eu.telecomnancy.championnat.exception.CompetitionNotFoundException;
import eu.telecomnancy.championnat.repository.CompetitionRepository;

@RestController
public class CompetitionController {
	
	private final CompetitionRepository repository;
	
	CompetitionController(CompetitionRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/competitions")
	public List<Competition> all() {
		return repository.findAll();
	}	
	
	@GetMapping("/competitions/{id}")
	public Competition one(@PathVariable Long id) {

		return repository.findById(id)
			.orElseThrow(() -> new CompetitionNotFoundException(id));
	}
	
}
