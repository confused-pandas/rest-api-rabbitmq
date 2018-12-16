
package eu.telecomnancy.championnat.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eu.telecomnancy.championnat.Competition;
import eu.telecomnancy.championnat.Equipe;
import eu.telecomnancy.championnat.Match;
import eu.telecomnancy.championnat.assembler.CompetitionResourceAssembler;
import eu.telecomnancy.championnat.assembler.EquipeResourceAssembler;
import eu.telecomnancy.championnat.assembler.MatchResourceAssembler;
import eu.telecomnancy.championnat.exception.CompetitionNotFoundException;
import eu.telecomnancy.championnat.exception.EquipeNotFoundException;
import eu.telecomnancy.championnat.exception.MatchNotFoundException;
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
	
	
	@GetMapping("/competitions/{id}/equipes")
	public Resources<Resource<Equipe>> res(@PathVariable Long id) {

		List<Equipe> equipes = new ArrayList<Equipe>();
		List<Resource<Equipe>> equipesResources;
		
		Competition competition = repository.findById(id).orElseThrow(() -> new CompetitionNotFoundException(id));
		long[] idEquipe = competition.getListeIdEquipe();

		for (int i = 0; i < idEquipe.length; i++) {
			final int a = i;
			Equipe equipe = equipeRepository.findById(idEquipe[i])
			.orElseThrow(() -> new EquipeNotFoundException(idEquipe[a]));
			equipes.add(equipe);
		}
		equipesResources = equipes.stream().map(equipeAssembler::toResource).collect(Collectors.toList());

		return new Resources<>(equipesResources,
				linkTo(methodOn(EquipeController.class).all()).withSelfRel());
	
	}
	
	@GetMapping("/competitions/{id}/matches")
	public Resources<Resource<Match>> test(@PathVariable Long id) {
		
		List<Match> matches = new ArrayList<Match>();
		List<Resource<Match>> matchesResources;

		Competition competition = repository.findById(id).orElseThrow(() -> new CompetitionNotFoundException(id));
		long[] idMatch = competition.getListeIdMatch();
		
		
		for (int i = 0; i < idMatch.length; i++) {
			final int a = i;
			Match match = matchRepository.findById(idMatch[i])
			.orElseThrow(() -> new MatchNotFoundException(idMatch[a]));
			matches.add(match);
		}
		matchesResources = matches.stream().map(matchAssembler::toResource).collect(Collectors.toList());

		return new Resources<>(matchesResources,
				linkTo(methodOn(MatchController.class).all()).withSelfRel());
	
	}
	
	@GetMapping("/competitions/{id}/classement")
	public Resources<Resource<Equipe>> test2(@PathVariable Long id) {
		
		List<Equipe> equipes = new ArrayList<Equipe>();
		List<Equipe> equipesTriee = new ArrayList<Equipe>();
		List<Integer> points = new ArrayList<Integer>();
		List<Resource<Equipe>> equipesResources2;
		
		
		Competition competition = repository.findById(id).orElseThrow(() -> new CompetitionNotFoundException(id));
		long[] idEquipe = competition.getListeIdEquipe();
				
		for (int i = 0; i < idEquipe.length; i++) {
			final int a = i;
			Equipe equipe = equipeRepository.findById(idEquipe[i])
			.orElseThrow(() -> new EquipeNotFoundException(idEquipe[a]));
			equipes.add(equipe);
			points.add(equipe.getNbPoints());			
		}
		for (int i = 0; i < (idEquipe.length-1); i++) {
			int ind = points.indexOf(Collections.max(points));
			equipesTriee.add(equipes.get(ind));
			points.remove(ind);
			equipes.remove(ind);
		}
		
		equipesTriee.add(equipes.get(0));
		
		equipesResources2 = equipesTriee.stream().map(equipeAssembler::toResource).collect(Collectors.toList());

		return new Resources<>(equipesResources2,
				linkTo(methodOn(EquipeController.class).all()).withSelfRel());
	}
	

	

	
}