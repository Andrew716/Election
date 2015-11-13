package entities;

/**
 * Created by Andrii on 11/10/2015.
 */
public class Voter extends Person {

    private Candidate candidate;

    public Voter(String name, String surname, String fathersName, String password, Candidate candidate) {
        super(name, surname, fathersName, password);
        this.candidate = candidate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
