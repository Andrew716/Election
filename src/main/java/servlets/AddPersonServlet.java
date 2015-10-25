package servlets;

import dao.DAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
@WebServlet(name = "AddPersonServlet")
public class AddPersonServlet extends javax.servlet.http.HttpServlet {

    private final Logger LOGGER = Logger.getLogger("Info logging");
    private Connection connection;
    private String voterName;
    private String candidateName;
    private String name;
    private String surname;
    private String fathersName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("Method processRequest has been started");
        this.voterName = request.getParameter("voter_checkBox");
        this.candidateName = request.getParameter("candidate_checkBox");
        this.name = request.getParameter("Name");
        this.surname = request.getParameter("Surname");
        this.fathersName = request.getParameter("Fathers_name");
        if (request.getParameter("buttonSubmit") != null){
            this.connection = DAO.getConnection();
            if (voterName != null && name != null && surname != null && fathersName != null){
                DAO.addPerson(connection, name, surname, fathersName, true, false);
            }
            if (candidateName != null && name != null && surname != null && fathersName != null){
                DAO.addPerson(connection, name, surname, fathersName, false, true);
            }
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            processRequest(request,response);

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }
}
