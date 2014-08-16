/*
 * CommonFilter.java
 *
 * Created on July 26, 2007, 10:04 PM
 */

package web.lib.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.lib.container.*;

/**
 *
 * @author  Steve
 * @version
 */

public class CommonFilter implements Filter
{
    private FilterConfig filterConfig;
    
    protected void doBeforeProcessing(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        Container container = Container.getInstance();
        request.setAttribute("params", container);
    }
    
    protected void doAfterProcessing(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
    }
    
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        doBeforeProcessing(request, response);
        
        chain.doFilter(request, response);
        
        doAfterProcessing(request, response);
    }
    
    public void destroy()
    {
        filterConfig = null;
    }
}
