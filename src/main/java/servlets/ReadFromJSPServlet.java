package servlets;

import packageDB.CreateDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
@javax.servlet.annotation.WebServlet(name = "ReadFromJSPServlet")
public class ReadFromJSPServlet extends javax.servlet.http.HttpServlet {

    private final Logger LOGGER = Logger.getLogger("Info logging");

    private void addToDataBase(String surname, String name, String fathersName, boolean flagForVoter, boolean flagForCandidate){
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        String voterName = (String) request.getParameter("voter_checkBox");
        LOGGER.info(voterName);
        String candidateName = (String) request.getParameter("candidate_checkBox");
        LOGGER.info(candidateName);
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
        String fathersName = (String) request.getParameter("fathers_name");
        if (voterName != null){
            addToDataBase(name, surname, fathersName, true, false);
        }
        if (candidateName != null){
            addToDataBase(name, surname, fathersName, false, true);
        }

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request,response);
    }
}
