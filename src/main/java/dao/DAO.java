package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
public class DAO {

    private final static Logger LOGGER = Logger.getLogger("Info logging");

    public static void addPerson(Connection connection, String surname, String name, String fathersName, boolean flagForVoter, boolean flagForCandidate){
        LOGGER.info("Method addPerson has been started");
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            LOGGER.info("SQLException has been generated");
        }
        try {
            String insertQueryForVoter = "INSERT INTO voter VALUES ("+ "'"+ surname+"'" + "," +"'"+ name + "'"+"," +"'"+ fathersName+"'" + ")";
            LOGGER.info(insertQueryForVoter);
            String insertQueryForCandidate = "INSERT INTO candidate VALUES ("+ "'"+ surname+"'" + "," +"'"+ name + "'"+"," +"'"+ fathersName+"'" + ")";
            LOGGER.info(insertQueryForCandidate);
            if (flagForVoter){
                statement.executeUpdate(insertQueryForVoter);
            }
            if (flagForCandidate){
                statement.executeUpdate(insertQueryForCandidate);
            }
        } catch (SQLException e) {
            LOGGER.info(e.getMessage());
        }
    }

    public static Connection getConnection(){
        LOGGER.info("Method getConnection has been started");
        Connection connection = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource source = (DataSource) initialContext.lookup("java:/comp/env/ElectionDataSource");
            connection = source.getConnection();
        } catch (NamingException e) {
            LOGGER.info("Naming Exception has been generated");
        } catch (SQLException e) {
            LOGGER.info("SQLException has been generated");
        }
        LOGGER.info("DataSource has been generated");
        return connection;
    }
}
