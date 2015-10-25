package dao;

import entities.Person;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
public class DAO {

    private final static Logger LOGGER = Logger.getLogger("Info logging");
    private static Connection connection;

    static {
        LOGGER.info("Method getConnection has been started");
        try {
            InitialContext initialContext = new InitialContext();
            DataSource source = (DataSource) initialContext.lookup("java:/comp/env/ElectionDataSource");
            connection = source.getConnection();
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        LOGGER.info("DataSource has been generated");
    }

    public static void addPerson(Person person, boolean flagForVoter, boolean flagForCandidate){
        LOGGER.info("Method addPerson has been started");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        try {
            String insertQueryForVoter = "INSERT INTO voter VALUES ("+ "'"+ person.getSurname()+"'" + "," +"'"+ person.getName()+ "'"+"," +"'"+ person.getFathersName()+"'" + ")";
            String insertQueryForCandidate = "INSERT INTO candidate VALUES ("+ "'"+ person.getSurname()+"'" + "," +"'"+ person.getName()+ "'"+"," +"'"+ person.getFathersName()+"'" + ")";
            if (flagForVoter){
                statement.executeUpdate(insertQueryForVoter);
            }
            if (flagForCandidate){
                statement.executeUpdate(insertQueryForCandidate);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
