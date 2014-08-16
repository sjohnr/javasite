/*
 * ForwardException.java
 *
 * Created on August 2, 2007, 10:15 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.exception;

/**
 *
 * @author sjohnr
 */
public class ForwardException extends java.lang.Exception
{
    /**
     * Creates a new instance of <code>ForwardException</code> without detail message.
     */
    public ForwardException()
    {
    }
    
    
    /**
     * Constructs an instance of <code>ForwardException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ForwardException(String msg)
    {
        super(msg);
    }
}
