package fr.grf.evtmanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login controller to handle request to /login.
 */
public class LoginController extends HttpServlet {

    static final Logger logger = LogManager.getLogger(LoginController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        boolean isMailWrong = Boolean.parseBoolean(req.getParameter("wrongMail"));
        boolean isPasswordWrong = Boolean.parseBoolean(req.getParameter("wrongPwd"));

        if(isMailWrong || isPasswordWrong){

            String wrongCredentialsMSG = "<div id=\"wrong-credentials\">" +
                    "<ul>";

            if(isMailWrong)
                wrongCredentialsMSG += "<li>Adresse mail incorrecte</li>";

            if(isPasswordWrong)
                wrongCredentialsMSG += "<li>Mot de passe incorrecte</li>";

            wrongCredentialsMSG += "</ul></div>";


            req.setAttribute("wrongCredentials", wrongCredentialsMSG);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
    }
}
