/*
 * Action.java
 *
 * Created on July 26, 2007, 10:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.action;

import java.util.Map;
import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import web.lib.container.*;
import web.lib.exception.*;
import web.lib.controller.*;

/**
 *
 * @author sjohnr
 */
abstract public class Action
{
    private String moduleName;
    private String actionName;
    
    private String template;
    private String view;
    private String layout;
    private String title;
    
    private Controller controller;
    private ServletRequest request;
    private ServletResponse response;
    
    private Map attributes;
    private Map errors;
    
    /**
     * Creates a new instance of Action
     *
     */
    public Action()
    {
    }
    
    /**
     * Initialize the action instance
     *
     * This MUST be performed before the action instance can be used
     *
     * @param String moduleName the module name
     * @param String actionName the action name
     * @param Controller controller the controller object
     * @param ServletRequest request the request object
     * @param ServletResponse response the response object
     */
    public void initialize(String moduleName, String actionName, Controller controller, ServletRequest request, ServletResponse response)
    {
        setModuleName(moduleName);
        setActionName(actionName);
        setTemplate(actionName);
        setView("Success");
        setLayout("layout");
        setTitle("Demo");
        setController(controller);
        setRequest(request);
        setResponse(response);
        
        Container container = Container.getInstance();
        
        // assign references of errors and attributes
        this.attributes = container.getAttributes();
        this.errors = container.getErrors();
    }
    
    /**
     * The master execute method, which executes the action specified by <code>actionName</code>
     *
     * There are two distinct schemes for naming the module/action class:
     * - <Module><Action>Action
     *   specifies an action in a class by itself
     * - <module>Actions
     *   specifies a collection of actions in one class together
     *
     * In the first scheme, the "do something" method is simply <code>execute()</code>.
     *
     * In the second scheme, the "do something" method corresponds to the action name,
     * in the form <code>execute<Action>()</code>
     * 
     * The <code>view</code> parameter (default of "Success") is coupled with the 
     * module and action (or template, see <code>setTemplate()</code>) to form a
     * logical view to render the result of the business logic execution performed by the action.
     *
     * Examples of full view names are:
     * - createSuccess (create + Success)
     * - createError (create + Error)
     * - showCsv (show + Csv)
     * - showExcel (show + Excel)
     */
    abstract public void execute() throws Exception;
    
    /**
     * The master validate method, which determines if the action can be run, or an error generated
     *
     * This method is responsible for setting error messages in the request.
     *
     * @return boolean the value representing the occurance of an error in validation
     */
    public boolean validate()
    {
        return true;
    }
    
    /**
     * The master error handling method, which determines what to do in the event of an error in the validation
     *
     * @return String the view name to use for the error event
     */
    public void handleError()
    {
        setView("Error");
    }
    
    /**
     * Gets called before the execution of the action, for module-wide pre-filters
     *
     */
    public void preExecute()
    {
    }
   
    /**
     * Gets called after the execution of the action, for module-wide post-filters
     *
     */
    public void postExecute()
    {
    }
    
    /**
     * Forward the controller to process a new module/action pair
     *
     * @param String moduleName the new module name
     * @param String actionName the new action name
     */
    public void forward(String moduleName, String actionName) throws Exception
    {
        controller.forward(moduleName, actionName);
        
        throw new StopException();
    }
    
    /**
     * Forward if a condition is met
     *
     * @param boolean condition the condition to check
     * @param String moduleName the new module name
     * @param String actionName the new action name
     */
    public void forwardIf(boolean condition, String moduleName, String actionName) throws Exception
    {
        if (condition)
        {
            forward(moduleName, actionName);
        }
    }
    
    /**
     * Forward if a condition is not met
     *
     * @param boolean condition the condition to check
     * @param String moduleName the new module name
     * @param String actionName the new action name
     */
    public void forwardUnless(boolean condition, String moduleName, String actionName) throws Exception
    {
        if (!condition)
        {
            forward(moduleName, actionName);
        }
    }
    
    /**
     * Forward to the default 404 error page
     *
     */
    public void forward404() throws Exception
    {
        forward("default", "error404");
    }
    
    /**
     * Forward to the default 404 error page if a condition is met
     *
     * @param boolean condition the condition to check
     */
    public void forward404If(boolean condition) throws Exception
    {
        if (condition)
        {
            forward404();
        }
    }
    
    /**
     * Forward to the default 404 error page if a condition is not met
     *
     * @param boolean condition the condition to check
     */
    public void forward404Unless(boolean condition) throws Exception
    {
        if (!condition)
        {
            forward404();
        }
    }
    
    /**
     * Permanently redirect the request to another request
     *
     * @param String location the location of the redirect
     */
    public void redirect(String location) throws Exception
    {
        controller.redirect(location);
    }
    
    /**
     * Permanently redirect if a condition is met
     *
     * @param boolean condition the condition to check
     * @param String location the location of the redirect
     */
    public void redirectIf(boolean condition, String location) throws Exception
    {
        if (condition)
        {
            controller.redirect(location);
        }
    }
    
    /**
     * Permanently redirect if a condition is not met
     *
     * @param boolean condition the condition to check
     * @param String location the location of the redirect
     */
    public void redirectUnless(boolean condition, String location) throws Exception
    {
        if (!condition)
        {
            controller.redirect(location);
        }
    }
    
    /**
     * Get the module name
     *
     * @return String the module name for this action instance
     */
    public String getModuleName()
    {
        return moduleName;
    }
    
    /**
     * Set the module name
     *
     * @param String moduleName the module for this action instance
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }
    
    /**
     * Get the action name
     *
     * @return String the action name for this action instance
     */
    public String getActionName()
    {
        return actionName;
    }
    
    /**
     * Set the action name
     *
     * @param String actionName the name for this action instance
     */
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }
    
    /**
     * Get the template name
     *
     * @return String the template name for this action instance
     */
    public String getTemplate()
    {
        return template;
    }
    
    /**
     * Set the template name
     *
     * @param String template the template name action instance
     */
    public void setTemplate(String template)
    {
        this.template = template;
    }
    
    /**
     * Get the view name
     *
     * @return String the view name to render ("Success", "Error", etc.)
     */
    public String getView()
    {
        return view;
    }
    
    /**
     * Set the view name
     *
     * @param String view the view name to render ("Success", "Error", etc.)
     */
    public void setView(String view)
    {
        this.view = view;
    }
    
    /**
     * Get the decoration layout
     *
     * @return String the layout to use to decorate response
     */
    public String getLayout()
    {
        return layout;
    }
    
    /**
     * Set the decoration layout
     *
     * @param String layout the layout to use to decorate the response
     */
    public void setLayout(String layout)
    {
        this.layout = layout;
    }
    
    /**
     * Get the page title
     *
     * @return String the page title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Set the page title
     *
     * @param String title the page title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * Get the controller object
     * 
     * 
     * @return WebController the controller object for this request
     */
    public Controller getController()
    {
        return controller;
    }
    
    /**
     * Set the controller object
     * 
     * 
     * @param Controller controller the controller object for this request
     */
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    
    /**
     * Get the request object
     *
     * @return ServletRequest the request object for this request
     */
    public ServletRequest getRequest()
    {
        return request;
    }
    
    /**
     * Set the request object
     *
     * @param ServletRequest request the request object for this request
     */
    public void setRequest(ServletRequest request)
    {
        this.request = request;
    }
    
    /**
     * Get the response object
     *
     * @return ServletResponse the response object for this request
     */
    public ServletResponse getResponse()
    {
        return response;
    }
    
    /**
     * Set the response object
     *
     * @param ServletResponse response the response object for this request
     */
    public void setResponse(ServletResponse response)
    {
        this.response = response;
    }
    
    /**
     * Get a parameter from the request
     *
     * @param String name the name of the variable
     * @param String dflt the default value if no parameter is present
     * @return String the value from the request, or the default value
     */
    public String getRequestParameter(String name, String dflt)
    {
        return request.getParameter(name) != null ? request.getParameter(name) : dflt;
    }
    
    /**
     * Get a parameter from the request, defaults to null
     *
     * @param String name the name of the variable
     * @return String the value from the request, or null
     */
    public String getRequestParameter(String name)
    {
        return getRequestParameter(name, null);
    }
    
    /**
     * Test if a parameter is available in the request
     *
     * @param String name the name of the variable
     * @return boolean true if the variable is set in the request
     */
    public boolean hasRequestParameter(String name)
    {
        return request.getParameter(name) != null;
    }
    
    /**
     * Get an error
     *
     * @return String the error
     */
    public String getError(String name)
    {
        return (String) errors.get(name);
    }
    
    /**
     * Set an error
     *
     * @param String name the name of the error
     * @param String error the error
     */
    public void setError(String name, String error)
    {
        errors.put(name, error);
    }
    
    /**
     * Get all errors
     *
     * @return Map all errors
     */
    public Map getErrors()
    {
        return errors;
    }
    
    /**
     * Get all error names
     *
     * @return Enumeration all error names
     */
    public Set getErrorNames()
    {
        return errors.keySet();
    }
    
    /**
     * Get a templating variable (attribute) from the request
     *
     * @param String name the name of the variable
     */
    public Object getAttribute(String name)
    {
        return (String) attributes.get(name);
    }
    
    /**
     * Set a templating variable (attribute) in the request
     *
     * @param String name the name of the variable
     * @param Object obj the object variable
     */
    public void setAttribute(String name, Object obj)
    {
        attributes.put(name, obj);
    }
    
    /**
     * Get all attributes
     *
     * @return Map all attributes
     */
    public Map getAttributes()
    {
        return attributes;
    }
    
    /**
     * Get all attribute names
     *
     * @return Enumeration all attribute names
     */
    public Set getAttributeNames()
    {
        return attributes.keySet();
    }
}