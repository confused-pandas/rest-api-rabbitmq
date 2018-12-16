package eu.telecomnancy.championnat;


import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="competition")
public class Competition {
	
    @Id
    @SequenceGenerator( name = "competitionSeq", sequenceName = "competition_seq", allocationSize = 20, initialValue = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="competitionSeq")
    private Long idCompetition;
    private String nomCompetition;
    private int nbMatch;
    private long[] listeIdEquipe;
    private long[] listeIdMatch;
    
    protected Competition() {}
        
    public Competition(String nomCompetition, int nbMatch, long[] listeIdEquipe,
    		long[]listeIdMatch) {
		this.nomCompetition = nomCompetition;
		this.nbMatch = nbMatch;
		this.listeIdEquipe = listeIdEquipe;
		this.listeIdMatch = listeIdMatch;
	}

	@Override
	public String toString() {
		return "Competition [idCompetition=" + idCompetition + ", nomCompetition=" + nomCompetition + ", nbMatch="
				+ nbMatch + ", listeIdEquipe=" + listeIdEquipe + ", listeIdMatch=" + listeIdMatch + "]";
	}

	public Long getIdCompetition() {
		return idCompetition;
	}
	
	public String getNomCompetition() {
		return nomCompetition;
	}
	public void setNomCompetition(String nomCompetition) {
		this.nomCompetition = nomCompetition;
	}
	public int getNbMatch() {
		return nbMatch;
	}
	public void setNbMatch(int nbMatch) {
		this.nbMatch = nbMatch;
	}

	public long[] getListeIdEquipe() {
		return listeIdEquipe;
	}

	public void setListeIdEquipe(long[] listeIdEquipe) {
		this.listeIdEquipe = listeIdEquipe;
	}

	public long[] getListeIdMatch() {
		return listeIdMatch;
	}

	public void setListeIdMatch(long[] listeIdMatch) {
		this.listeIdMatch = listeIdMatch;
	}

	public Long getId() {
		return idCompetition;
	}

	public void setIdCompetition(Long idCompetition) {
		this.idCompetition = idCompetition;
	}

    
    
}
