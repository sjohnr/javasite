/*
 * StopException.java
 *
 * Created on July 29, 2007, 2:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.exception;

/**
 *
 * @author sjohnr
 */
public class StopException extends java.lang.Exception
{    
    /**
     * Creates a new instance of <code>StopException</code> without detail message.
     *
     */
    public StopException()
    {
    }
    
    
    /**
     * Constructs an instance of <code>StopException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public StopException(String msg)
    {
        super(msg);
    }
}