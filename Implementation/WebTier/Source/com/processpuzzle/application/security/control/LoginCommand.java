package com.processpuzzle.application.security.control;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;

public class LoginCommand implements CommandInterface {
   ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
   PartyRepository repository = (PartyRepository) applicationContext.getRepository( PartyRepository.class );
   public static String USER_PARAM_NAME = "userName";
   public static String PASSWORD_PARAM_NAME = "password";
   private String userName;
   private String password;
   private Person signedInUser;
   private UserFactory userFactory;

   private ServletContext servletContext = null;

   public void init( CommandDispatcher dispatcher ) {
      this.servletContext = dispatcher.getServletContext();
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      HttpServletRequest request = dispatcher.getRequest();
      HttpServletResponse response = dispatcher.getResponse();

      if( request.getParameter( "method" ) != null && request.getParameter( "method" ).equals( "logout" ) ){

         String id = "";
         UserSession userSession = (UserSession) dispatcher.getRequest().getSession().getAttribute( "userSession" );
         if( userSession != null )
            id = userSession.getUser().getId().toString();

         request.getSession().setAttribute( "userSession", null );
         if( !(id.equals( "" )) ){
            Collection<String> loggedInUsers = (Collection<String>) dispatcher.getServletContext().getAttribute( "loggedInUsers" );
            for( Iterator<String> iter = loggedInUsers.iterator(); iter.hasNext(); ){
               String element = (String) iter.next();
               if( element.equals( id ) ){
                  loggedInUsers.remove( element );
                  dispatcher.getServletContext().setAttribute( "loggedInUsers", loggedInUsers );
                  break;
               }
            }
         }

         StringBuffer responseXml = new StringBuffer();
         responseXml.append( "<?xml version=\"1.0\" ?>" );
         responseXml.append( "<logoutResponse value='true'>" );
         responseXml.append( "</logoutResponse>" );

         response.setContentType( "text/xml" );
         response.setCharacterEncoding( "UTF-8" );
         response.setHeader( "Cache-Control", "no-cache" );

         response.getWriter().write( responseXml.toString() );
         dispatcher.getServletContext().setAttribute( "haltApplication", "false" );
      }else{
         userName = dispatcher.getProperties().getProperty( USER_PARAM_NAME );
         password = dispatcher.getProperties().getProperty( PASSWORD_PARAM_NAME );
         findUser();
         buildXmlResponse( request, response );
         saveSignedInUserInSession( dispatcher );
      }
      return "";
   }

   // Getters, setters
   public String getName() { return this.getClass().getName(); }

   public ServletContext getServletContext() { return servletContext; }

   // Protected, private helper methods
   private void buildXmlResponse( HttpServletRequest request, HttpServletResponse response ) throws IOException {
      String responseXml = "<?xml version=\"1.0\" ?>";

      if( signedInUser != null ){
         // Collection replacements = signedInUser.getReplacements();
         responseXml += "<loginResponse value='true'>";
         responseXml += "<user>";
         if( signedInUser != null ){
            responseXml += "<id>" + signedInUser.getSystemUser().getId() + "</id>";
            responseXml += "<name>" + signedInUser.getPartyName().getName() + "</name>";
            responseXml += "<location>" + signedInUser.getSystemUser().getLocation() + "</location>";
            if( signedInUser.getSystemUser().getPrefferedLocale() != null )
               responseXml += "<prefferedLanguage>" + signedInUser.getSystemUser().getPrefferedLocale().getLanguage()
                     + "</prefferedLanguage>";
            else{
               ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
               responseXml += "<prefferedLanguage>"
                     + applicationContext.getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getXPathKey() ) + "</prefferedLanguage>";
            }

            // if( !replacements.isEmpty() ){
            // responseXml += "<replacements>";
            // for (Iterator replacementIteraror = replacements.iterator();
            // replacementIteraror
            // .hasNext();) {
            // Person person = (Person) replacementIteraror.next();
            // responseXml += "<substituted>"
            // + "<id>"+person.getId()+"</id>"
            // + "<name>"+person.getPartyName.getName()+"</name>"
            // + "</substituted>";
            // }
            // responseXml += "</replacements>";
            // }
            // responseXml += "</user>";
            // responseXml += "</loginResponse>";
            System.out.println( responseXml );
         }else
            responseXml += "<name></name>";
         responseXml += "</user>";
         responseXml += "</loginResponse>";
      }else{
         responseXml += "<loginResponse value='false'>";
         responseXml += "</loginResponse>";
      }

      response.setContentType( "text/xml" );
      response.setCharacterEncoding( "UTF-8" );
      response.setHeader( "Cache-Control", "no-cache" );

      response.getWriter().write( responseXml );
   }

   private void findUser() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Collection<?> persons = (Collection<?>) repository.findAllOrderedActivePerson( work, "partyName asc" );
      for( Iterator<?> iter = persons.iterator(); iter.hasNext(); ){
         Person person = (Person) iter.next();
         if( person.getSystemUser() != null ){
            if( (person.getSystemUser().getUserName() != null) && (person.getSystemUser().getPassword() != null)
                  && (person.getSystemUser().getUserName().equals( userName )) && (person.getSystemUser().getPassword().equals( password )) ){
               signedInUser = person;
               break;
            }
         }
      }
      work.finish();
   }

   @SuppressWarnings( "unchecked" )
   private void saveSignedInUserInSession( CommandDispatcher dispatcher ) {
      if( signedInUser != null ){
         @SuppressWarnings("unused")
         String fullName = signedInUser.getPartyName().getName();
         String userName = signedInUser.getSystemUser().getUserName();
         String password = signedInUser.getSystemUser().getPassword();
         User user = userFactory.createUser( userName, password );

         if( signedInUser.getSystemUser().getPrefferedLocale() != null ){
            ProcessPuzzleLocale preferedLocale = new ProcessPuzzleLocale( signedInUser.getSystemUser().getPrefferedLocale().getLanguage() );
            user.setPrefferedLocale( preferedLocale );
         }

         // user.setPartyRoleType( signedInUser.getPartyType().getName() );

         dispatcher.getRequest().getSession().setAttribute( "userSession", user );
         Collection<String> loggedInUsers = (Collection<String>) dispatcher.getServletContext().getAttribute( "loggedInUsers" );
         if( loggedInUsers == null )
            loggedInUsers = new HashSet<String>();
         loggedInUsers.add( user.getId().toString() );
         dispatcher.getServletContext().setAttribute( "loggedInUsers", loggedInUsers );
      }
   }
}