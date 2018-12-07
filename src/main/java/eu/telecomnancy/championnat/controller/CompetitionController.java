package eu.telecomnancy.championnat.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Competition;
import eu.telecomnancy.championnat.Equipe;
import eu.telecomnancy.championnat.assembler.CompetitionResourceAssembler;
import eu.telecomnancy.championnat.assembler.EquipeResourceAssembler;
import eu.telecomnancy.championnat.assembler.MatchResourceAssembler;
import eu.telecomnancy.championnat.exception.CompetitionNotFoundException;
import eu.telecomnancy.championnat.exception.EquipeNotFoundException;
import eu.telecomnancy.championnat.repository.CompetitionRepository;
import eu.telecomnancy.championnat.repository.EquipeRepository;
import eu.telecomnancy.championnat.repository.MatchRepository;

@RestController
public class CompetitionController {
	
	private final CompetitionRepository repository;
	private final CompetitionResourceAssembler assembler;
	private final EquipeRepository equipeRepository;
	private final MatchRepository matchRepository;
	private final MatchResourceAssembler matchAssembler;
	private final EquipeResourceAssembler equipeAssembler;
	
	CompetitionController(CompetitionRepository repository, CompetitionResourceAssembler assembler, EquipeRepository equipeRepository, EquipeResourceAssembler equipeAssembler,
			MatchRepository matchRepository, MatchResourceAssembler matchAssembler) {
		this.repository = repository;
		this.matchRepository = matchRepository;
		this.matchAssembler = matchAssembler;
		this.assembler = assembler;
		this.equipeAssembler = equipeAssembler;
		this.equipeRepository = equipeRepository;
		
	}

	
	@GetMapping("/competitions")
	public Resources<Resource<Competition>> all() {

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
	
	/*@GetMapping("/competitions/{id}/equipes")
	public Resource<Resource<Equipe>> res(@PathVariable Long id) {
		List<Equipe> equipes = new ArrayList<Equipe>();
		List<Resource<Equipe>> equipesResources;
		
		Competition competition = repository.findById(id).orElseThrow(() -> new CompetitionNotFoundException(id));
		long[] idEquipe = competition.getListeIdEquipe();

		for (int i=0; i< idEquipe.length; i++){
			Equipe equipe = repository.findById(idEquipe[i]) .orElseThrow(() -> new EquipeNotFoundException(idEquipe[i]));
			equipes.add(equipe);
		}
		equipesResources = equipes.stream().map(assembler::toResource).collect(Collectors.toList());
		return new Resources<>(equipesResources,
				linkTo(methodOn(EquipeController.class).all()).withSelfRel());
		
		
	}*/

	
}
