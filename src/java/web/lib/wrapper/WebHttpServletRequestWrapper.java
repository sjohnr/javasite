/*
 * WebHttpServletRequestWrapper.java
 *
 * Created on July 30, 2007, 11:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.wrapper;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author sjohnr
 */
public class WebHttpServletRequestWrapper extends HttpServletRequestWrapper
{
    private Hashtable parameters;
    private Hashtable errors;
    
    /**
     * Creates a new instance of WebHttpServletRequestWrapper
     *
     */
    public WebHttpServletRequestWrapper(HttpServletRequest request, Map params)
    {
        super(request);
        
        // initialize parameters
        parameters = new Hashtable();
        parameters.putAll(super.getParameterMap());
        parameters.putAll(params);
    }
    
    public String getParameter(String name)
    {
        return (String) parameters.get(name);
    }
    
    public Map getParameterMap()
    {
        return parameters;
    }
    
    public Enumeration getParameterNames()
    {
        return parameters.keys();
    }
}
