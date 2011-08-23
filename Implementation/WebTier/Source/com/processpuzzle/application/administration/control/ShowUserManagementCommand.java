/*
 * Created on 2006.07.10.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.administration.control;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;

/**
 * @author peter.krima
 */
public class ShowUserManagementCommand implements CommandInterface {
    public static String COMMAND_NAME = "ShowUserManagement";
    
    public ShowUserManagementCommand(){
        super();
    }
    
    public void init(CommandDispatcher dispatcher) {

    }

    public String getName() {
        return COMMAND_NAME;
    }

    public String execute(CommandDispatcher dispatcher) throws Exception {
        dispatcher.getServletContext().setAttribute("", "false");
        dispatcher.getRequest().setAttribute("messagekey", "userManagement");
        dispatcher.getServletContext().setAttribute("maintainerUserId", null);
        return "/ProcessInstantiation/SystemAdministration/UserManagement.jsp";
    }

}
