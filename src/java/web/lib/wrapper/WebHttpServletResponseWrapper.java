/*
 * WebHttpServletResponseWrapper.java
 *
 * Created on July 31, 2007, 7:03 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author sjohnr
 */
public class WebHttpServletResponseWrapper extends HttpServletResponseWrapper
{
    private PrintWriter pw;
    private OutputStream os;
    
    /**
     * Creates a new instance of WebHttpServletResponseWrapper
     *
     */
    public WebHttpServletResponseWrapper(HttpServletResponse response)
    {
        super(response);
        
        os = new ByteArrayOutputStream();
        pw = new PrintWriter(os);
    }
    
    public PrintWriter getWriter() throws IOException
    {
        return pw;
    }
    
    public String getOutput()
    {
        pw.flush();
        pw.close();
                
        String content = os.toString();
        
        os = new ByteArrayOutputStream();
        pw = new PrintWriter(os);
        
        return content;
    }
}