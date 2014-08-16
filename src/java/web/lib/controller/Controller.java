/*
 * Controller.java
 *
 * Created on July 29, 2007, 6:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package web.lib.controller;

/**
 *
 * @author sjohnr
 */
public interface Controller
{
    public void forward(String moduleName, String actionName) throws Exception;
    public void redirect(String location) throws Exception;
    public String dispatch(String moduleName, String actionName) throws Exception;
}
