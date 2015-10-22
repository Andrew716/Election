package servlets;

import dao.AddToDataBase;
import packageDB.CreateDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
@WebServlet(name = "ReadFromJSP")
public class ReadFromJSP extends javax.servlet.http.HttpServlet {

    private final Logger LOGGER = Logger.getLogger("Info logging");
    private Connection connection;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("Method processRequest has been started");
        String voterName = (String) request.getParameter("voter_checkBox");
        String candidateName = (String) request.getParameter("candidate_checkBox");
        String name = (String) request.getParameter("name");
        String surname = (String) request.getParameter("surname");
        String fathersName = (String) request.getParameter("fathers_name");
        if (request.getParameter("buttonSubmit") != null){
            if (voterName != null && name != null && surname != null && fathersName != null){
                AddToDataBase.addToDataBase(connection, name, surname, fathersName, true, false);
            }
            if (candidateName != null && name != null && surname != null && fathersName != null){
                AddToDataBase.addToDataBase(connection, name, surname, fathersName, false, true);
            }
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (request.getParameter("buttonSubmit") != null){
            this.connection = CreateDataSource.doCreationDataSource();
            processRequest(request,response);
        }
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }
}
