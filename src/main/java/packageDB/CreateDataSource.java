package packageDB;

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
public class CreateDataSource {
    private final static Logger LOGGER = Logger.getLogger("Info logging");

    public static Statement doCreationDataSource(){
        LOGGER.info("Method doCreationDataSource has been started");
        Connection connection = null;
        Statement statement = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource source = (DataSource) initialContext.lookup("java:/comp/env/ElectionDB");
            connection = source.getConnection();
            statement = connection.createStatement();
        } catch (NamingException e) {
            LOGGER.info("Naming Exception has been generated");
        } catch (SQLException e) {
            LOGGER.info("SQLException has been generated");
        }
        LOGGER.info("DataSource has been generated");
        return statement;
    }
}
