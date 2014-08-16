/*
 * Container.java
 *
 * Created on August 4, 2007, 11:31 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.container;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import web.lib.action.Action;

/**
 *
 * @author sjohnr
 */
public class Container
{
    private Hashtable parameters;
    private Hashtable attributes;
    private Hashtable errors;
    
    private static Container instance;
    
    public static Container getInstance()
    {
        if (instance == null)
        {
            instance = new Container();
        }
        
        return instance;
    }
    
    private Container()
    {
        parameters = new Hashtable();
        attributes = new Hashtable();
        errors = new Hashtable();
    }
    
    public void populate(Action actionInstance)
    {
        // add module/action to container
        setParameter("module", actionInstance.getModuleName());
        setParameter("action", actionInstance.getActionName());
        
        // build view path from module, action, and view name
        String view = "/templates/"+actionInstance.getModuleName()+"/"+actionInstance.getTemplate()+actionInstance.getView()+".jsp";
        // set the view path in the application context, for the dispathing servlet
        setParameter("view", view);
        
        // build the layout path
        String layout = "/layouts/"+actionInstance.getLayout()+".jsp";
        // set the layout in the application context, for the decoration process
        setParameter("layout", layout);
        
        // other variables
        setParameter("title", actionInstance.getTitle());
        setParameter("context", ((HttpServletRequest) actionInstance.getRequest()).getContextPath());
    }
    
    public void setParameter(String name, String value)
    {
        parameters.put(name, value);
    }
    
    public String getParameter(String name)
    {
        return (String) parameters.get(name);
    }
    
    public Map getParameters()
    {
        return (Map) parameters;
    }
    
    public void setParameters(Map parameters)
    {
        this.parameters = (Hashtable) parameters;
    }
    
    public Set getParameterNames()
    {
        return parameters.keySet();
    }
    
    public void setAttribute(String name, Object value)
    {
        attributes.put(name, value);
    }
    
    public Object getAttribute(String name)
    {
        return attributes.get(name);
    }
    
    public Map getAttributes()
    {
        return (Map) attributes;
    }
    
    public void setAttributes(Map attributes)
    {
        this.attributes = (Hashtable) attributes;
    }
    
    public Set getAttributeNames()
    {
        return attributes.keySet();
    }
    
    public void setError(String name, String error)
    {
        errors.put(name, error);
    }
    
    public String getError(String name)
    {
        return (String) errors.get(name);
    }
    
    public Map getErrors()
    {
        return (Map) errors;
    }
    
    public void setErrors(Map errors)
    {
        this.errors = (Hashtable) errors;
    }
    
    public Set getErrorNames()
    {
        return errors.keySet();
    }
}
