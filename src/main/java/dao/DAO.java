package dao;

import entities.Person;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
public class DAO {

    private final static Logger LOGGER = Logger.getLogger("Info logging");
    private static Connection connection;

    static {
        LOGGER.info("Generating DataSource");
        try {
            InitialContext initialContext = new InitialContext();
            DataSource source = (DataSource) initialContext.lookup("java:/comp/env/ElectionDataSource");
            connection = source.getConnection();
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, "Connection failed InitialContext", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection failed DataSource", e);
        }
        LOGGER.info("DataSource has been generated");
    }

    public static Set<Person> voterSet(){
        Set<Person> personSet = new HashSet<Person>();
        Statement statement = null;
        String query = "SELECT * FROM voter";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Person person = new Person();
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setFathersName(resultSet.getString("fathersname"));
                person.setPassword(resultSet.getString("password"));
                personSet.add(person);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in voterSet method. SQLException", e);
        }
        return personSet;
    }

    public static Set<Person> candidateSet(){
        Set<Person> personSet = new HashSet<Person>();
        String query = "SELECT * FROM candidate";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Person person = new Person();
                person.setName(resultSet.getString("name"));
                person.setSurname(resultSet.getString("surname"));
                person.setFathersName(resultSet.getString("fathersname"));
                person.setPassword(resultSet.getString("password"));
                personSet.add(person);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Exception in candidateSet method. SQLException", e);
        }
        return personSet;
    }

    public static void output(Set<Person> personSet){
        Iterator iterator = personSet.iterator();
        while (iterator.hasNext()){
            Person person = (Person) iterator.next();
            LOGGER.info((person.toString()));
        }
    }

    public static void addPerson(Person person, boolean flagForVoter, boolean flagForCandidate){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection failed in method addPerson", e);
        }
        try {
            String insertQueryForVoter = "INSERT INTO voter VALUES ("+ "'"+ person.getName()+"'" + "," +"'"+ person.getSurname()+ "'"+"," +"'"+ person.getFathersName()+"'" + ","+ "'"+person.getPassword()+"'"+ ")";
            String insertQueryForCandidate = "INSERT INTO candidate VALUES ("+ "'"+ person.getName()+"'" + "," +"'"+ person.getSurname()+ "'"+"," +"'"+ person.getFathersName()+"'" + ","+ "'"+person.getPassword()+"'"+ ")";
            if (flagForVoter){
                statement.executeUpdate(insertQueryForVoter);
            }
            if (flagForCandidate){
                statement.executeUpdate(insertQueryForCandidate);
            }
            if (flagForCandidate && flagForVoter){
                statement.execute(insertQueryForCandidate);
                statement.execute(insertQueryForVoter);
            }
            LOGGER.info("Person added successfully");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Insert failed", e);
        }
    }
}
