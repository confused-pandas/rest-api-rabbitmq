package eu.telecomnancy.championnat.exception;

public class CompetitionNotFoundException extends RuntimeException {
	
	public CompetitionNotFoundException(Long id) {
		super("Could not find competition " + id);
	}

}
