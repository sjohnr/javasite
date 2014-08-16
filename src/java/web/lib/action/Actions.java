/*
 * Actions.java
 *
 * Created on July 26, 2007, 10:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.action;

/**
 *
 * @author sjohnr
 */
abstract public class Actions extends Action
{
    /**
     * Test if execute<Action> exists, call it, and return view name
     *
     */
    public void execute() throws Exception
    {
        String actionName = getActionName().substring(0, 1).toUpperCase()+getActionName().substring(1);
        
        getClass().getDeclaredMethod("execute"+actionName, new Class[] {}).invoke(this, new Object[] {});
    }
    
    /**
     * Test if validate<Action> exists, call it, and return result
     *
     */
    public boolean validate()
    {
        String actionName = getActionName().substring(0, 1).toUpperCase()+getActionName().substring(1);
        
        try
        {
            return ((Boolean) getClass().getDeclaredMethod("validate"+actionName, new Class[] {}).invoke(this, new Object[] {})).booleanValue();
        }
        catch (Exception e)
        {
            return super.validate();
        }
    }
    
    /**
     * Test if handleError<Action> exists, and call it
     *
     */
    public void handleError()
    {
        String actionName = getActionName().substring(0, 1).toUpperCase()+getActionName().substring(1);
        
        try
        {
            getClass().getDeclaredMethod("handleError"+actionName, new Class[] {}).invoke(this, new Object[] {});
        }
        catch (Exception e)
        {
            super.handleError();
        }
    }
}