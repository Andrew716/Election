package dao;

import packageDB.CreateDataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
public class AddToDataBase {

    private final static Logger LOGGER = Logger.getLogger("Info logging");

    public static void addToDataBase(String surname, String name, String fathersName, boolean flagForVoter, boolean flagForCandidate){
        LOGGER.info("Method addToDataBase has been started");
        Statement statement = CreateDataSource.doCreationDataSource();
        String insertQueryForVoter = "INSERT INTO Voter VALUES (" + surname + "," + name + "," + fathersName + ")";
        String insertQueryForCandidate = "INSERT INTO Candidate VALUES (" + surname + "," + name + "," + fathersName + ")";
        try {
            if (flagForVoter){
                statement.execute(insertQueryForVoter);
            }
            if (flagForCandidate){
                statement.execute(insertQueryForCandidate);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}