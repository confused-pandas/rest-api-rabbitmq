package eu.telecomnancy.championnat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Equipe;
import eu.telecomnancy.championnat.exception.EquipeNotFoundException;
import eu.telecomnancy.championnat.repository.EquipeRepository;

@RestController
public class EquipeController {
	
	private final EquipeRepository repository;
	
	EquipeController(EquipeRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/equipes")
	List<Equipe> all() {
		return repository.findAll();
	}	
	
	@GetMapping("/equipes/{id}")
	Equipe one(@PathVariable Long id) {

		return repository.findById(id)
			.orElseThrow(() -> new EquipeNotFoundException(id));
	}
	
}
