package eu.telecomnancy.championnat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipe {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idEquipe;
    private String nomEquipe;
    private String villeEquipe;
    private int nbPoints;
    
    protected Equipe() {}
    
    public Equipe(String nomEquipe, String villeEquipe, int nbPoints) {
    	this.nomEquipe = nomEquipe;
    	this.villeEquipe = villeEquipe;
    	this.nbPoints = nbPoints;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Equipe[id=%d, nomEquipe='%s', villeEquipe='%s', nbPoints=%d]",
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

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

}
