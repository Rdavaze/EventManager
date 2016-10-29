package fr.eventmanager.servlet;

import fr.eventmanager.builder.UserBuilder;
import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.dao.impl.UserDAOImpl;
import fr.eventmanager.model.User;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Login servlet to handle request to /login.
 */
public class LoginController extends Servlet {
    private UserDAO userDAO;

    private String wrongCredentialsParam;

    @Override
    public void init() throws ServletException {
        super.init(this);
        this.userDAO = UserDAOImpl.getInstance();

        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/)?"), "login"));
        registerRoute(HttpMethod.GET, new Route(Pattern.compile("(/forgot)?"), "forgot"));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/connect"), "connect"));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/subscribe"), "subscribe"));
        registerRoute(HttpMethod.POST, new Route(Pattern.compile("/changePwd"), "changePassword"));
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkParameters(req);
        getServletContext().getRequestDispatcher("/WEB-INF/login/login.jsp").forward(req, resp);
    }

    public void forgot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkParameters(req);
        getServletContext().getRequestDispatcher("/WEB-INF/login/forgot.jsp").forward(req, resp);
    }

    public void connect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (authenticate(req)) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/events/myEvents");
        } else {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login" + wrongCredentialsParam);
        }
    }

    public void subscribe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (register(req)) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login");
        } else {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login?usedMail=true");
        }

    }

    public void changePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (modifyPassword(req)) {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login");
        } else {
            resp.sendRedirect(this.getServletContext().getContextPath() + "/login/forgot?mailNotExist=true");
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

        // Oubli
        boolean mailNotExist = Boolean.parseBoolean(req.getParameter("mailNotExist"));


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

        if (mailNotExist) {
            String mailNotExistMSG = "<div id=\"mail-not-exist\"><ul><li>L'adresse mail n'existe pas</li></ul></div>";

            req.setAttribute("mailNotExist", mailNotExistMSG);
        }


    }


    /**
     * Process authentication
     *
     * @param req request
     */
    private boolean authenticate(HttpServletRequest req) {
        final HttpSession session = req.getSession();

        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        final Optional<User> userOptional = this.userDAO.findByCredentials(email, password);
        if (userOptional.isPresent()) {
            session.setAttribute("user", userOptional.get());
            session.setAttribute("logged", true);
            System.out.println("Logging successfull");
            this.wrongCredentialsParam = "";
            return true;
        } else {
            session.setAttribute("user", new UserBuilder()
                    .setEmail(email)
                    .setPassword(password)
                    .build());
            session.setAttribute("logged", false);
            System.out.println("Logging failed");

            boolean emailExists = this.userDAO.emailExists(email);
            boolean passwordExists = this.userDAO.passwordExists(password);

            if (!emailExists && passwordExists)
                wrongCredentialsParam = "?wrongMail=true";
            else if (emailExists && !passwordExists)
                wrongCredentialsParam = "?wrongPwd=true";
            else
                wrongCredentialsParam = "?wrongMail=true&wrongPwd=true";


            return false;
        }
    }

    /**
     * Process registration
     *
     * @param req request
     */
    private boolean register(HttpServletRequest req) {

        String email = req.getParameter("sub-email");
        String firstname = req.getParameter("sub-firstname");
        String name = req.getParameter("sub-name");
        String password = req.getParameter("sub-password");


        if (!this.userDAO.emailExists(email)) {

            this.userDAO.persist(new UserBuilder()
                    .setEmail(email)
                    .setPrenom(firstname)
                    .setNom(name)
                    .setPassword(password)
                    .build());

            return true;
        }


        return false;
    }


    /**
     * Modify the password corresponding to the email
     *
     * @param req request
     * @return indicate if the change has been done
     */
    private boolean modifyPassword(HttpServletRequest req) {

        String email = req.getParameter("forgot-email");
        String password = req.getParameter("forgot-password");

        if (this.userDAO.emailExists(email)) {

            this.userDAO.updatePassword(email, password);
            return true;
        }

        return false;
    }
}
