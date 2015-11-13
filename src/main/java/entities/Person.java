package entities;

/**
 * Created by Andrii on 10/25/2015.
 */
public class Person {
    private String name;
    private String surname;
    private String fathersName;
    private String password;

    public Person(){}

    public Person(String name, String surname, String fathersName, String password) {
        this.name = name;
        this.surname = surname;
        this.fathersName = fathersName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return name + " " + surname + " " + fathersName + " " + password;
    }
}
