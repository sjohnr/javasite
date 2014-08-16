/*
 * DispatcherServlet.java
 *
 * Created on July 26, 2007, 9:51 PM
 */

package web.lib.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.lib.controller.WebController;

/**
 *
 * @author sjohnr
 * @version
 */
public class DispatcherServlet extends HttpServlet
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ServletContext context = getServletConfig().getServletContext();
        WebController controller = new WebController(context, request, response);
        
        // get the module and action
        String moduleName = (String) request.getParameter("module");
        String actionName = (String) request.getParameter("action");
        
        String view;
        try
        {
            view = doDispatch(controller, moduleName, actionName);
        }
        catch (ServletException e)
        {
            if (getInitParameter("debug").equals("on"))
            {
                throw e;
            }
            else
            {
                view = doDispatch(controller, "default", "error500");
            }
        }
        
        request.getRequestDispatcher(view).include(request, response);
    }
    
    /**
     * Dispatch a request from the main processRequest function.
     *
     * @param WebController controller
     * @param String moduleName
     * @param String actionName
     */
    protected String doDispatch(WebController controller, String moduleName, String actionName) throws ServletException
    {
        try
        {
            return controller.dispatch(moduleName, actionName);
        }
        catch (Exception e)
        {
            throw new ServletException("Dispatch failed: "+e.getClass().toString()+": "+e.getMessage());
        }
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
        return "Dispatcher Servlet";
    }
}