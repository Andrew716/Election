package servlets;

import dao.DAO;
import entities.Person;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;

/**
 * Created by Andrii on 10/21/2015.
 */
@WebServlet(name = "AddPersonServlet")
public class AddPersonServlet extends javax.servlet.http.HttpServlet {

    private final Logger LOGGER = Logger.getLogger("Info logging");
    private String voterName;
    private String candidateName;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("Method processRequest has been started");
        this.voterName = request.getParameter("voter_checkBox");
        this.candidateName = request.getParameter("candidate_checkBox");
        Person person = new Person(request.getParameter("Name"),request.getParameter("Surname"), request.getParameter("Fathers_name"));
        if (request.getParameter("buttonSubmit") != null){
            if (voterName != null && person.getName() != null && person.getSurname() != null && person.getFathersName() != null){
                DAO.addPerson(person, true, false);
            }
            if (candidateName != null && person.getName() != null && person.getSurname() != null && person.getFathersName() != null){
                DAO.addPerson(person, false, true);
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
