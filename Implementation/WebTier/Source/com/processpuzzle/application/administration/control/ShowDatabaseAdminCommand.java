package com.processpuzzle.application.administration.control;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.util.domain.OPDomainStrings;

public class ShowDatabaseAdminCommand implements CommandInterface {
   public static String COMMAND_NAME = "ShowDatabaseAdmin";

   public void init( CommandDispatcher dispatcher ) {}

   public String getName() {
      return COMMAND_NAME;
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      UserSession userSession = (UserSession) ((HttpServletRequest) dispatcher.getRequest()).getSession().getAttribute(
            OPDomainStrings.LOGGED_USER_ATTRIBUTE_NAME_IN_SESSION );

      if( userSession != null ){
         Collection<?> loggedInUsers = (Collection<?>) dispatcher.getServletContext().getAttribute( "loggedInUsers" );
         if( loggedInUsers.size() == 1 ){
            dispatcher.getServletContext().setAttribute( "haltApplication", "true" );
            // dispatcher.getServletContext().setAttribute("maintainerUserId", userSession.getId().toString());
            ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
            File file = new File( (String) applicationContext.getProperty( ProcessPuzzleContext.UPLOADED_FILES_FOLDER ) + "\\"
                  + OPDomainStrings.BACKUP_DIR_NAME + "\\" );
            if( file.exists() && file.isDirectory() ){
               ArrayList<String[]> versionList = new ArrayList<String[]>();
               File[] files = file.listFiles();
               if( (files != null) && (files.length > 0) ){
                  for( int i = 0; i < files.length; i++ ){
                     File[] files1 = files[i].listFiles();
                     for( int j = 0; j < files1.length; j++ ){
                        String[] str = new String[2];
                        str[0] = files[i].getName();
                        str[1] = files1[j].getName();
                        versionList.add( str );
                     }
                  }
               }
               DatabaseAdminHelper helper = new DatabaseAdminHelper();
               helper.retreiveDatas( versionList );
               dispatcher.getRequest().setAttribute( "helper", helper );
            }
         }else{
            dispatcher.getRequest().setAttribute( "messageKey", "tooManySignedInUser" );
            return "/ProcessInstantiation/SystemAdministration/SystemAdministrationMessage.jsp";
         }
         return "/ProcessInstantiation/SystemAdministration/DatabaseAdmin.jsp";
      }
      dispatcher.getRequest().setAttribute( "messageKey", "notLoggedIn" );
      return "/ProcessInstantiation/SystemAdministration/SystemAdministrationMessage.jsp";
   }

}
