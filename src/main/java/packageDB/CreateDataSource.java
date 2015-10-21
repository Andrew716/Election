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
        LOGGER.info("aaaaaaaaaaaaaaaaaa");
        Connection connection = null;
        Statement statement = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource source = (DataSource) initialContext.lookup("java:/comp/env/ElectionDB");
            connection = source.getConnection();
            statement = connection.createStatement();
//            LOGGER.info("\n" + "Preparing to insert" + "\n");
//            statement.execute("INSERT INTO Voter VALUE (1,'Andrii', 'Koropets', 'Anatoliyovich')");
//            LOGGER.info("\n" + "Insert was done successfully" + "\n");
        } catch (NamingException e) {
            LOGGER.info("Naming Exception has been generated");
        } catch (SQLException e) {
            LOGGER.info("SQLException has been generated");
        }
        LOGGER.info("Congratulation! You have just created DataSource!");
        return statement;
    }
}
