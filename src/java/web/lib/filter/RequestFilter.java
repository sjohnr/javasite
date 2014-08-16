/*
 * RequestFilter.java
 *
 * Created on July 26, 2007, 10:05 PM
 */

package web.lib.filter;


import java.io.*;
import java.util.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.lib.wrapper.WebHttpServletRequestWrapper;

/**
 *
 * @author  Steve
 * @version
 */

public class RequestFilter implements Filter
{
    private static final String DEFAULT_MODULE = "default";
    private static final String DEFAULT_ACTION = "index";
    
    protected Hashtable parse(String uri)
    {
        Hashtable params = new Hashtable();
        
        // split on "/" character
        String[] elements = uri.split("/");
        
        // match module/action in /:module or /:module/:action
        switch (elements.length)
        {
            case 0:
            case 1:
                params.put("module", DEFAULT_MODULE);
                params.put("action", DEFAULT_ACTION);
                break;
            case 2:
                params.put("module", elements[1]);
                params.put("action", DEFAULT_ACTION);
                break;
            case 3:
            default:
                params.put("module", elements[1]);
                params.put("action", elements[2]);
        }
        
        // match * in /:module/:action/*
        for (int i = 4; i < elements.length; i += 2)
        {
            params.put(elements[i-1], elements[i]);
        }
        
        return params;
    }
    
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        Hashtable params = parse(request.getPathInfo() != null ? request.getPathInfo() : "/");
        
        WebHttpServletRequestWrapper wRequest = new WebHttpServletRequestWrapper(request, params);
        
        chain.doFilter(wRequest, response);
    }
    
    public void destroy()
    {
    }
}
