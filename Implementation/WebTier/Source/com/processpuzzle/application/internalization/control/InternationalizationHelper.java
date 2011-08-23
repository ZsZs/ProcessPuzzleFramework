package com.processpuzzle.application.internalization.control;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;

public class InternationalizationHelper {
   private ProcessPuzzleLocale locale = null;
   private UserSession userSession = null;
   private String value = null;
   private ProcessPuzzleContext applicationContext;

   // Constructors
   public InternationalizationHelper( UserSession userSession ) {
      this.userSession = userSession;
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      determineLocale();
   }
   
   public InternationalizationHelper() {
      this( null );
   }

   // Properties
   public UserSession getUserSession() {
      return userSession;
   }

   public void setUserSession( UserSession userSession ) {
      this.userSession = userSession;
   }

   public void setKey( String key ) {
      determineLocale();
      value = applicationContext.getText( key, locale );
   }

   public String getValue() {
      return value;
   }

   public String getLocale() {
      if( locale == null ) {
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         return (String) applicationContext.getProperty( PropertyKeys.INTERNALIZATION_DEFAULT_LOCALE.getXPathKey() );
      }
      else
         return locale.toString();
   }

   public void setLocale( ProcessPuzzleLocale locale ) {
      this.locale = locale;
   }
   
   public void setLocale( String locale ) {
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      this.locale = applicationContext.getInternalizationContext().findLocaleBySpecifier( locale );
   }

   private void determineLocale() {
      if( userSession != null && userSession.getUser().getPrefferedLocale() != null ) {
         locale = userSession.getUser().getPrefferedLocale();
      } else {
         locale = applicationContext.getDefaultLocale();
      }
   }
}