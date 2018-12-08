package eu.telecomnancy.championnat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="match")
public class Match {

    @Id
    @SequenceGenerator( name = "matchSeq", sequenceName = "match_seq", allocationSize = 20, initialValue = 1 )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="matchSeq")
    private Long id;
    private String nomEquipeA;
    private String nomEquipeB;
    private int scoreA;
    private int scoreB;
	private Long idEquipeA;
    private Long idEquipeB;
    private Statut statut;

    protected Match() {}

    public Match(String nomEquipeA, String nomEquipeB, int scoreA, int scoreB, Long idEquipeA, Long idEquipeB, Statut statut) {
        this.nomEquipeA = nomEquipeA;
        this.nomEquipeB = nomEquipeB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
        this.idEquipeA = idEquipeA;
        this.idEquipeB = idEquipeB;
        this.statut = statut;
    }

    @Override
    public String toString() {
        return String.format(
                "Match[id=%d, nomEquipeA='%s', nomEquipeB='%s', scoreA=%d, scoreB=%d, idEquipeA=%d, idEquipeB=%d, statut='%s']",
                id, nomEquipeA, nomEquipeB, scoreA, scoreB, idEquipeA, idEquipeB, statut.statut());
    }
    
    public Long getId() {
		return id;
	}

	public String getNomEquipeA() {
		return nomEquipeA;
	}

	public String getNomEquipeB() {
		return nomEquipeB;
	}

	public int getScoreA() {
		return scoreA;
	}

	public int getScoreB() {
		return scoreB;
	}

	public Long getIdEquipeA() {
		return idEquipeA;
	}

	public Long getIdEquipeB() {
		return idEquipeB;
	}

	public Statut getStatut() {
		return statut;
	}

}