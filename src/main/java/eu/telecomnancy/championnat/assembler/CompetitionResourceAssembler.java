package eu.telecomnancy.championnat.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import eu.telecomnancy.championnat.Competition;
import eu.telecomnancy.championnat.controller.CompetitionController;

@Component
public class CompetitionResourceAssembler implements ResourceAssembler<Competition, Resource<Competition>> {

	@Override
	public Resource<Competition> toResource(Competition competition) {

		return new Resource<>(competition,
			linkTo(methodOn(CompetitionController.class).one(competition.getId())).withSelfRel(),
			linkTo(methodOn(CompetitionController.class).all()).withRel("competitions"));
	}
}

