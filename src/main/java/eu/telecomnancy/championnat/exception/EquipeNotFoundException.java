package eu.telecomnancy.championnat.exception;

public class EquipeNotFoundException extends RuntimeException {

	public EquipeNotFoundException(Long id) {
		super("Could not find equipe " + id);
	}
}
