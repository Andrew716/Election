package servlets;

import dao.DAO;
import entities.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

/**
 * Created by Andrii on 10/21/2015.
 */
@WebServlet(name = "AddPersonServlet")
public class AddPersonServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger("Info logging");
    private String voterName;
    private String candidateName;
    private String confirmPassword;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("Method processRequest has been started");
        this.voterName = request.getParameter("voter_checkBox");
        this.candidateName = request.getParameter("candidate_checkBox");
        this.confirmPassword = request.getParameter("PasswordConfirm");
        Person person = new Person(request.getParameter("Name"),request.getParameter("Surname"), request.getParameter("Fathers_name"), request.getParameter("Password"));
        if (request.getParameter("vote") != null){
            checkMatch(request, response);
        }
        if (person.getName() != null && person.getSurname() != null && person.getFathersName()!= null  && person.getPassword() != null){
            if (request.getParameter("buttonSubmit") != null){
                if (voterName != null) {
                    DAO.addPerson(person, true, false);
                }
                if (candidateName != null) {
                    DAO.addPerson(person, false, true);
                }
                try {
                    request.getRequestDispatcher("pageElection.jsp").include(request, response);

                }catch (IOException e){
                    LOGGER.log(Level.SEVERE, "IOException has been generated", e);
                }catch (ServletException e){
                    LOGGER.log(Level.SEVERE, "ServletException has been generated", e);
                }
            }
        }
    }

    protected void checkMatch(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("Method checkMatch started");
        Person person = new Person(request.getParameter("Name_match"), request.getParameter("Surname_match"), request.getParameter("Fathers_name_match"), request.getParameter("Password_match"));
        try {
            if (request.getParameter("buttonSubmit_match") != null && doesThePersonInSet(person)){
                LOGGER.info("Checking passed successfully");
                request.getRequestDispatcher("votePage.jsp").include(request, response);

            }
        } catch (ServletException e) {
            LOGGER.log(Level.SEVERE, "Exception in checkMatch method. ServletException has been thrown", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Exception in checkMatch method. IOException has been thrown", e);
        }
    }

    public boolean doesThePersonInSet(Person person){
        boolean flag = false;
        for (Person e: DAO.voterSet()){
            if (doesStringsEqual(e.getName(), person.getName()) && doesStringsEqual(e.getSurname(), person.getSurname()) && doesStringsEqual(e.getFathersName(), person.getFathersName()) && doesStringsEqual(e.getPassword(), person.getPassword())){
                flag = true;
            }
        }
        return flag;
    }

    public boolean doesStringsEqual(String string1, String string2){
        boolean flag = true;
        char[] chars1 = string1.toCharArray();
        char[] chars2 = string2.toCharArray();
        for (int i = 0; i < chars1.length; i++){
            if (chars1[i] != chars2[i]){
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
        checkMatch(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        checkMatch(request, response);
    }
}
