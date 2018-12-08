package eu.telecomnancy.championnat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long idEquipe;
    private String nomEquipe;
    private String villeEquipe;
    private int nbPoints;
    private long[] listIdMatch;

    protected Equipe() {}

    public Equipe(String nomEquipe, String villeEquipe, int nbPoints, long[] listIdMatch) {
    	this.nomEquipe = nomEquipe;
    	this.villeEquipe = villeEquipe;
    	this.nbPoints = nbPoints;
    	this.listIdMatch = listIdMatch;
    }

    @Override
    public String toString() {
        return String.format(
                "Equipe[idEquipe=%d, nomEquipe='%s', villeEquipe='%s', nbPoints=%d]",
                idEquipe, nomEquipe, villeEquipe, nbPoints);
    }

	public Long getIdEquipe() {
		return idEquipe;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public String getVilleEquipe() {
		return villeEquipe;
	}

	public int getNbPoints() {
		return nbPoints;
	}

	public Long getId() {
		return idEquipe;
	}
	
	public long[] getIdMatch() {
		return listIdMatch;
	}
    

}