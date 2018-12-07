package eu.telecomnancy.championnat.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Equipe;
import eu.telecomnancy.championnat.Match;
import eu.telecomnancy.championnat.assembler.EquipeResourceAssembler;
import eu.telecomnancy.championnat.assembler.MatchResourceAssembler;
import eu.telecomnancy.championnat.exception.EquipeNotFoundException;
import eu.telecomnancy.championnat.exception.MatchNotFoundException;
import eu.telecomnancy.championnat.repository.EquipeRepository;
import eu.telecomnancy.championnat.repository.MatchRepository;

@RestController
public class EquipeController {
	
	private final EquipeRepository equipeRepository;
	private final MatchRepository matchRepository;
	private final MatchResourceAssembler matchAssembler;
	private final EquipeResourceAssembler assembler;
	
	EquipeController(EquipeRepository equipeRepository, EquipeResourceAssembler assembler, MatchRepository matchRepository, MatchResourceAssembler matchAssembler) {
		this.equipeRepository = equipeRepository;
		this.matchRepository = matchRepository;
		this.matchAssembler = matchAssembler;
		this.assembler = assembler;
	}
	
	@GetMapping("/equipes")
	public Resources<Resource<Equipe>> all() {

		List<Resource<Equipe>> equipes = equipeRepository.findAll().stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(equipes,
			linkTo(methodOn(EquipeController.class).all()).withSelfRel());
	}
	
	@GetMapping("/equipes/{id}")
	public Resource<Equipe> one(@PathVariable Long id) {

		Equipe equipe = equipeRepository.findById(id)
			.orElseThrow(() -> new EquipeNotFoundException(id));

		return assembler.toResource(equipe);
	}	
	
	@GetMapping("/matches/{id}/equipes")
	public Resources<Resource<Equipe>> findEquipeMatch(@PathVariable Long id) {

		Match match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException(id));
		Long idEquipeA = match.getIdEquipeA();
		Long idEquipeB = match.getIdEquipeB();
		System.out.println("Equipe A: "+idEquipeA);
		
		Equipe equipeA = equipeRepository.findById(idEquipeA)
				.orElseThrow(() -> new EquipeNotFoundException(idEquipeA));
		Equipe equipeB = equipeRepository.findById(idEquipeB)
				.orElseThrow(() -> new EquipeNotFoundException(idEquipeB));
		List<Equipe> equipes = new ArrayList<Equipe>();
		List<Resource<Equipe>> equipesResources;
		equipes.add(equipeA);
		equipes.add(equipeB);
		equipesResources = equipes.stream().map(assembler::toResource).collect(Collectors.toList());

		return new Resources<>(equipesResources,
				linkTo(methodOn(EquipeController.class).all()).withSelfRel());
	
	}	
	
	@GetMapping("/equipes/{id}/matches")
	public Resources<Resource<Match>> test(@PathVariable Long id) {

		Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new EquipeNotFoundException(id));
		long[] idMatch = equipe.getIdMatch();
		List<Match> matches = new ArrayList<Match>();
		List<Resource<Match>> matchesResources;
		
		for (int i = 0; i < idMatch.length; i++) {
			final int a = i;
			Match match = matchRepository.findById(idMatch[i])
			.orElseThrow(() -> new EquipeNotFoundException(idMatch[a]));
			matches.add(match);
		}
		matchesResources = matches.stream().map(matchAssembler::toResource).collect(Collectors.toList());

		return new Resources<>(matchesResources,
				linkTo(methodOn(MatchController.class).all()).withSelfRel());
	
	}	
}
