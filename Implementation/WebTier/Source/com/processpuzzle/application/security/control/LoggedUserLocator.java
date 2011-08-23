package com.processpuzzle.application.security.control;

import javax.servlet.http.HttpSession;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.util.domain.OPDomainStrings;

public class LoggedUserLocator {
   private static ProcessPuzzleContext applicationContext;
   private static PersonRepository personRepository;
   
   public LoggedUserLocator() {
   }

   public static Person locate( CommandDispatcher dispatcher ) {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      personRepository = applicationContext.getRepository( PersonRepository.class );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person user = null;
      HttpSession session = dispatcher.getRequest().getSession( true );
      Object attribute = session.getAttribute( OPDomainStrings.LOGGED_USER_ATTRIBUTE_NAME_IN_SESSION );
      if( attribute != null ){
         String userId = null;
         try{
            userId = ((UserSession) attribute).getUser().getId().toString();
         }catch( NumberFormatException nfe ){}
         if( userId != null ){
            ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
            PartyRepository partyRepository = (PartyRepository) applicationContext.getRepository( PartyRepository.class );
            user = personRepository.findByUserId( work, userId );
         }
      }
      work.finish();
      return user;
   }
}