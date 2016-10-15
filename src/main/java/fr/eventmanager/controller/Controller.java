package fr.eventmanager.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String pathInfo = request.getPathInfo();
        final String path = (pathInfo != null) ? pathInfo : "/";


        // ApplicationResources provides a simple API for retrieving constants and other preconfigured values
       /* ApplicationResources resource = ApplicationResources.getInstance();
        try {
            // Use a helper object to gather parameter specific information.
            RequestHelper helper = new RequestHelper(request);

            // Command helper perform custom operation
            Command cmdHelper = helper.getCommand();
            page = cmdHelper.execute(request, response);

        } catch (Exception e) {
            LogManager.getLogger().log(Level.ERROR, "EmployeeController:exception : " + e.getMessage());
            request.setAttribute(resource.getMessageAttr(), "Exception occurred : " + e.getMessage());
            page = resource.getErrorPage(e);
        }
        // dispatch control to view
        dispatch(request, response, page);*/
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Front Controller Pattern Servlet";
    }

    /**
     * Dispatch & Forward request.
     *
     * @param request  servlet request
     * @param response servlet response
     * @param page     string reference to the jsp view
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(page)
                .forward(request, response);
    }
}
