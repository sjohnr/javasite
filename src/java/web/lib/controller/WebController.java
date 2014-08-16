/*
 * WebController.java
 *
 * Created on July 27, 2007, 12:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.lib.action.*;
import web.lib.exception.*;
import web.lib.container.*;

import java.util.Stack;

/**
 *
 * @author sjohnr
 */
public class WebController implements Controller
{
    public static final int MAX_FORWARDS = 5;
    
    private ServletContext context;
    private HttpServletRequest request;
    private HttpServletResponse response;
    
    private Stack actionStack;
    
    private int forwards;
    
    /**
     * Creates a new instance of WebController
     *
     */
    public WebController(ServletContext context, HttpServletRequest request, HttpServletResponse response)
    {
        this.context = context;
        this.request = request;
        this.response = response;
        
        actionStack = new Stack();
        forwards = 0;
    }
    
    /**
     * Forward the controller to process a new module/action pair
     *
     * @param String moduleName the new module name
     * @param String actionName the new action name
     * @return String the view name to render
     */
    public void forward(String moduleName, String actionName) throws Exception
    {
        Action actionInstance = getActionInstance(moduleName, actionName);
        
        if (actionInstance == null)
        {
            throw new ForwardException("Action `"+moduleName+"/"+actionName+"` not found.");
        }
        
        // add the current action to the stack
        actionStack.push(actionInstance);
        
        // increment forwards count
        forwards++;
        
        if (forwards > MAX_FORWARDS)
        {
            throw new ForwardException("Too many forwards (>"+MAX_FORWARDS+"): "+forwards);
        }
        
        // initialize the action
        actionInstance.initialize(moduleName, actionName, this, request, response);
        
        // validate, then execute the action, or handle errors
        if (actionInstance.validate())
        {
            // execute pre-filter
            actionInstance.preExecute();
            
            // a StopException exception is thrown when a forward takes place
            try
            {
                // execute action
                actionInstance.execute();
            }
            catch (StopException e)
            {
                return;
            }
            
            // execute post-filter
            actionInstance.postExecute();
        }
        else
        {
            // handle error
            actionInstance.handleError();
        }
    }
    
    /**
     * Permanently redirect the request to another request
     *
     * The redirect location can be relative to the current request (no leading "/"),
     * relative to the root of the project (leading "/") or an absolute location.
     *
     * @param String location the location of the redirect
     */
    public void redirect(String location) throws Exception
    {
        response.sendRedirect(location);
    }
    
    /**
     * Dispatch the request
     *
     * After forwarding to the module/action pair for execution,
     * uses the last action instance in the sequence to build variables for view processing and decoration
     *
     * @return String the view to execute
     */
    public String dispatch(String moduleName, String actionName) throws Exception
    {
        // forward to the module/action
        forward(moduleName, actionName);
        
        // retrieve last action on the stack
        Action actionInstance = (Action) actionStack.peek();
        
        Container container = Container.getInstance();
        
        // populate the container object with execution data
        container.populate(actionInstance);
        
        return container.getParameter("view");
    }
    
    /**
     * Get an instance of an action object, associated with a given module/action pair
     *
     * Allows for two distinct types of scheme for naming the module/action class:
     * - <Module><Action>Action
     *   * specifies an action of a module in a class by itself
     * - <Module>Actions
     *   * specifies a collection of actions (module) in one class together
     *
     * @param String moduleName the parent module name
     * @param String actionName the action of the module
     */
    protected Action getActionInstance(String moduleName, String actionName)
    {
        Action actionInstance;
        
        // upper case first letter of module and action
        String module = moduleName.substring(0, 1).toUpperCase()+moduleName.substring(1);
        String action = actionName.substring(0, 1).toUpperCase()+actionName.substring(1);
        
        // try finding an action instance using the <Module><Action>Action scheme first
        try
        {
            actionInstance = (Action) Class.forName("web.app.action."+module+action+"Action").newInstance();
            
            return actionInstance;
        }
        catch (Exception e)
        {
        }
        
        // try finding an action instance using the <Module>Actions scheme
        try
        {
            actionInstance = (Action) Class.forName("web.app.action."+module+"Actions").newInstance();
            
            return actionInstance;
        }
        catch (Exception e)
        {
        }
        
        return null;
    }
}