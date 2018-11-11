package eu.telecomnancy.championnat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Equipe {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String place;
    
    protected Equipe() {}
    
    public void Equipe(String name, String place) {
    	this.name = name;
    	this.place = place;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Equipe[id=%d, name='%s', place='%s']",
                id, name, place);
    }

}
