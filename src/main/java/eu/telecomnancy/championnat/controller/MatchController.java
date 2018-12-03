package eu.telecomnancy.championnat.controller;

import java.util.List;
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
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import eu.telecomnancy.championnat.Match;
import eu.telecomnancy.championnat.controller.MatchController;
import eu.telecomnancy.championnat.exception.MatchNotFoundException;
import eu.telecomnancy.championnat.repository.MatchRepository;
import eu.telecomnancy.championnat.assembler.MatchResourceAssembler;

@RestController
public class MatchController {
	
	private final MatchRepository matchRepository;
	private final MatchResourceAssembler assembler;
	
	MatchController(MatchRepository matchRepository, MatchResourceAssembler assembler) {
		this.matchRepository = matchRepository;
		this.assembler = assembler;
	}
	
	@GetMapping("/matches")
	public
	Resources<Resource<Match>> all() {

		List<Resource<Match>> matches = matchRepository.findAll().stream()
			.map(assembler::toResource)
			.collect(Collectors.toList());

		return new Resources<>(matches,
			linkTo(methodOn(MatchController.class).all()).withSelfRel());
	}
	
	@GetMapping("/matches/{id}")
	public Resource<Match> one(@PathVariable Long id) {
		Match match = matchRepository.findById(id)
			.orElseThrow(() -> new MatchNotFoundException(id));
		return assembler.toResource(match);
	}	
	
}
