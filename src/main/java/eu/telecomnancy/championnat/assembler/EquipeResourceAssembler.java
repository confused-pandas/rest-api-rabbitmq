package eu.telecomnancy.championnat.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import eu.telecomnancy.championnat.Equipe;
import eu.telecomnancy.championnat.controller.EquipeController;

@Component
public class EquipeResourceAssembler implements ResourceAssembler<Equipe, Resource<Equipe>> {

	@Override
	public Resource<Equipe> toResource(Equipe equipe) {

		return new Resource<>(equipe,
			linkTo(methodOn(EquipeController.class).one(equipe.getId())).withSelfRel(),
			linkTo(methodOn(EquipeController.class).all()).withRel("equipes"));
	}
}
