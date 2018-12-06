package eu.telecomnancy.championnat.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Competition;
import eu.telecomnancy.championnat.assembler.CompetitionResourceAssembler;
import eu.telecomnancy.championnat.exception.CompetitionNotFoundException;
import eu.telecomnancy.championnat.repository.CompetitionRepository;

@RestController
public class CompetitionController {
	
	private final CompetitionRepository repository;
	private final CompetitionResourceAssembler assembler;
	
	CompetitionController(CompetitionRepository repository, CompetitionResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	public
	Resources<Resource<Competition>> all() {

		List<Resource<Competition>> competition = repository.findAll().stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(competition,
			linkTo(methodOn(CompetitionController.class).all()).withSelfRel());
	}
	
	@GetMapping("/competitions/{id}")
	public Resource<Competition> one(@PathVariable Long id) {
		
		Competition competition = repository.findById(id)
			.orElseThrow(() -> new CompetitionNotFoundException(id));
		return assembler.toResource(competition);
	}
	
	//@GetMapping("/Competitions/{id}/matches")

	
}
