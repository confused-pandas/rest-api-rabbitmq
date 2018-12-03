package eu.telecomnancy.championnat.exception;

public class MatchNotFoundException extends RuntimeException {

	public MatchNotFoundException(Long id) {
		super("Could not find match " + id);
	}
	
}
