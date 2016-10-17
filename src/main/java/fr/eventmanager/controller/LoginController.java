package fr.eventmanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login controller to handle request to /login.
 */
public class LoginController extends HttpServlet {

    static final Logger logger = LogManager.getLogger(LoginController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String URI = req.getRequestURI();

        if ("/login/forgot".matches(URI)){
            getServletContext().getRequestDispatcher("/WEB-INF/login/forgot.jsp").forward(req,resp);
        }
        else{
            checkParameters(req);

            getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String URI = req.getRequestURI();

        if ("/login/connect".matches(URI)) {
            authenticate(req);
            getServletContext().getNamedDispatcher("ProfileController").forward(req, resp);
        } else if ("/login/subscribe".matches(URI)) {
            register(req);
            getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
        }

    }

    /**
     * Check if there are error parameters
     *
     * @param req request
     */
    private void checkParameters(HttpServletRequest req) {

        //Connexion
        boolean isMailWrong = Boolean.parseBoolean(req.getParameter("wrongMail"));
        boolean isPasswordWrong = Boolean.parseBoolean(req.getParameter("wrongPwd"));
        //Inscription
        boolean isMailUsed = Boolean.parseBoolean(req.getParameter("usedMail"));

        if(isMailWrong || isPasswordWrong){

            String wrongCredentialsMSG = "<div id=\"wrong-credentials\">" +
                    "<ul>";

            if(isMailWrong)
                wrongCredentialsMSG += "<li>Adresse mail incorrecte</li>";

            if(isPasswordWrong)
                wrongCredentialsMSG += "<li>Mot de passe incorrect</li>";

            wrongCredentialsMSG += "</ul></div>";

            req.setAttribute("wrongCredentialsLog", wrongCredentialsMSG);
        }

        if (isMailUsed) {
            String wrongCredentialsSubMSG = "<div id=\"wrong-credentials\">" +
                    "<ul><li>Adresse mail déjà utilisée</li></ul></div>";

            req.setAttribute("wrongCredentialsSub", wrongCredentialsSubMSG);

        }
    }


    /**
     * Process authentication
     *
     * @param req request
     */
    private void authenticate(HttpServletRequest req) {

        HttpSession session = req.getSession(true);

        String mail = req.getParameter("email-login");
        String password = req.getParameter("password-login");

        // TODO : authenticate process in the backend & retrieve user info

        session.setAttribute("firstname", "John");
        session.setAttribute("name", "Doe");
        session.setAttribute("mail", mail);

    }

    /**
     * Process registration
     *
     * @param req request
     */
    private void register(HttpServletRequest req) {

        // TODO : register process in the backend

    }

}
