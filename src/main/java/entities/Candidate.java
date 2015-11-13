package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrii on 11/10/2015.
 */
public class Candidate extends Person{

    private Set<Voter> voters = new HashSet<Voter>();

    public Candidate(String name, String surname, String fathersname, String password, Set<Voter> voters){
        super(name, surname, fathersname, password);
        this.voters = voters;
    }

    public Set<Voter> getVoters() {
        return voters;
    }

    public void setVoters(Set<Voter> voters) {
        this.voters = voters;
    }
}
