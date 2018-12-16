package eu.telecomnancy.championnat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="equipe")
public class Equipe {

	@Id
    @SequenceGenerator( name = "equipeSeq", sequenceName = "equipe_seq", allocationSize = 20, initialValue = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="equipeSeq")
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
                "Equipe[id=%d, nomEquipe='%s', villeEquipe='%s', nbPoints=%d]",
                idEquipe, nomEquipe, villeEquipe, nbPoints);
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
    
    public void setIdEquipe(Long idEquipe) {
		this.idEquipe = idEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public void setVilleEquipe(String villeEquipe) {
		this.villeEquipe = villeEquipe;
	}

	public void setNbPoints(int nbPoints) {
		this.nbPoints = nbPoints;
	}

	public void setListIdMatch(long[] listIdMatch) {
		this.listIdMatch = listIdMatch;
	}

}