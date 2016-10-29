package fr.eventmanager.servlet;

import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.exception.MailAlreadyExistException;
import fr.eventmanager.exception.MailNotFoundException;
import fr.eventmanager.exception.WrongPasswordException;
import fr.eventmanager.model.User;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Login servlet to handle request to /login.
 */
public class LoginController extends Servlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init(this);
        this.userDAO = UserDAOImpl.getInstance();

        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "login"));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("/forgot"), "forgot"));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/signin"), "signin"));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/signup"), "signup"));
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkParameters(req);
        getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
    }

    public void forgot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login/forgot.jsp").forward(req, resp);
    }

    public void signin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            authenticate(req);
            resp.sendRedirect(this.getServletContext().getContextPath() + "/events/myEvents");
        } catch (MailNotFoundException e) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login?wrongMail=true");
        } catch (WrongPasswordException e) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login?wrongPwd=true");
        }
    }

    public void signup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            register(req);
            resp.sendRedirect(this.getServletContext().getContextPath() + "/events/myEvents");
        } catch (MailAlreadyExistException e) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login?usedMail=true");
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

        if (isMailWrong || isPasswordWrong) {
            String wrongCredentialsMSG = "<div id=\"wrong-credentials\">" +
                    "<ul>";

            if (isMailWrong) {
                wrongCredentialsMSG += "<li>Adresse mail incorrecte</li>";
            }
            if (isPasswordWrong) {
                wrongCredentialsMSG += "<li>Mot de passe incorrect</li>";
            }
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
    private boolean authenticate(HttpServletRequest req) throws MailNotFoundException, WrongPasswordException {
        final HttpSession session = req.getSession();
        if (isLogged(session)) {
            return true;
        }

        final String email = req.getParameter("login-email");
        final String password = req.getParameter("login-password");

        try {
            final User user = this.userDAO.findByCredentials(email, password);
            setSessionUser(session, user);
            setSessionLogged(session, true);
            System.out.println("Logging succeeded");
            return true;

        } catch (MailNotFoundException | WrongPasswordException e) {
            final User user = new UserBuilder()
                    .setEmail(email)
                    .setPassword(password)
                    .build();
            setSessionUser(session, user);
            setSessionLogged(session, false);
            System.out.println("Logging failed");
            throw e;
        }
    }

    /**
     * Process registration
     *
     * @param req request
     */
    private boolean register(HttpServletRequest req) throws MailAlreadyExistException {
        final HttpSession session = req.getSession();
        if (isLogged(session)) {
            return true;
        }

        final String email = req.getParameter("subscribe-email");
        final String password = req.getParameter("subscribe-password");
        final String firstname = req.getParameter("subscribe-firstname");
        final String lastname = req.getParameter("subscribe-lastname");

        try {
            final User user = this.userDAO.findByCredentials(email);

            // User already exists
            setSessionLogged(session, false);
            System.out.println("Register failed");
            throw new MailAlreadyExistException();

        } catch (MailNotFoundException ignored) {
        }

        final User user = new UserBuilder()
                .setEmail(email)
                .setPassword(password)
                .setFirstname(firstname)
                .setLastname(lastname)
                .build();
        userDAO.persist(user);
        setSessionUser(session, user);
        setSessionLogged(session, true);
        System.out.println("Register succeeded");
        return true;
    }
}
