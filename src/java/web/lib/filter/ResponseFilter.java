/*
 * ResponseFilter.java
 *
 * Created on July 26, 2007, 10:05 PM
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
import web.lib.wrapper.*;
import web.lib.container.*;

/**
 *
 * @author  Steve
 * @version
 */

public class ResponseFilter implements Filter
{
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }
    
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        // wrap the response
        WebHttpServletResponseWrapper wResponse = new WebHttpServletResponseWrapper(response);
        
        // do next filter
        chain.doFilter(request, wResponse);
        
        // capture view
        String content = wResponse.getOutput();
        
        // grab the parameter container
        Container container = Container.getInstance();
        
        // set the content in the container
        container.setParameter("content", content);
        
        // get layout path from container
        String layout = container.getParameter("layout");
        
        // forward control to the layout for decoration
        request.getRequestDispatcher(layout).forward(request, response);
    }
    
    public void destroy()
    {
    }
}
