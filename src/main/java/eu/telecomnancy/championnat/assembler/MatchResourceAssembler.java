package eu.telecomnancy.championnat.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import eu.telecomnancy.championnat.Match;
import eu.telecomnancy.championnat.controller.MatchController;

@Component
public class MatchResourceAssembler implements ResourceAssembler<Match, Resource<Match>> {

	@Override
	public Resource<Match> toResource(Match match) {

		return new Resource<>(match,
			linkTo(methodOn(MatchController.class).one(match.getId())).withSelfRel(),
			linkTo(methodOn(MatchController.class).all()).withRel("matches"));
	}
}
