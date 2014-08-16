/*
 * DefaultActions.java
 *
 * Created on July 28, 2007, 3:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.app.action;

import web.lib.action.Actions;

/**
 *
 * @author sjohnr
 */
public class DefaultActions extends Actions
{
    /**
     * Execute before every action in this module
     *
     */
    public void preExecute()
    {
        setLayout("default");
    }
    
    /**
     * Default project created page
     *
     */
    public void executeIndex()
    {
    }
    
    /**
     * Default module created page
     *
     */
    public void executeModule()
    {
    }
    
    /**
     * Default error 404 page (page not found)
     *
     */
    public void executeError404()
    {
    }
    
    /**
     * Default error 500 page (internal server error)
     *
     */
    public void executeError500()
    {
    }
    
    /**
     * Default secure area page (credentials required)
     *
     */
    public void executeSecure()
    {
    }
    
    /**
     * Default secure area page (login required)
     *
     */
    public void executeLogin()
    {
    }
    
    /**
     * Default module disabled page
     *
     */
    public void executeDisabled()
    {
    }
    
    /**
     * Default site unavailable page
     *
     */
    public void executeUnavailable()
    {
    }
}