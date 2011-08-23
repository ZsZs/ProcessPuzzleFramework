package com.processpuzzle.user_session.domain;

import com.processpuzzle.application.configuration.domain.PersistenceContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.security.domain.SystemAdministrator;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;

public class UserRequestManager {
   private static UserRequestManager soleInstance = null;
   private UserSessionFactory sessionFactory;
   private UserRequestContextFactory requestContextFactory;
   private ThreadLocal<UserRequestContext> currentRequestHolder = new ThreadLocal<UserRequestContext>();
   
   private UserRequestManager( UserSessionFactory sessionFactory, UserRequestContextFactory requestContextFactory ) {
      this.requestContextFactory = requestContextFactory;
      this.sessionFactory = sessionFactory;
   }
   
   //Public mutators
   public static UserRequestManager getInstance() {
      if( soleInstance == null ) {
         soleInstance = new UserRequestManager( new DefaultUserSessionFactory(), new DefaultUserRequestContextFactory() ); 
      }

      return soleInstance;
   }
   
   public UserSession createSession( UserSessionHolder sessionHolder, User user, Application application ) {
      UserSession session = sessionFactory.create( user, application );
      sessionHolder.setSession( session );
      return session;
   }

   public UserSession createAdministratorSession( UserSessionHolder sessionHolder, Application application ) {
      PersistenceContext persistenceContext = application.getContext().getPersistenceContext();
      UserRepository userRepository = persistenceContext.getRepositoryInstance( UserRepository.class );
      SystemAdministrator systemAdministrator = userRepository.findSystemAdministrator();
      
      return createSession( sessionHolder, systemAdministrator, application );
   }

   public void destroySession( UserSession userSession ) {
      // TODO Auto-generated method stub
   }

   public UserRequestContext createRequestContext( UserSessionHolder sessionHolder ) {
      UserRequestContext requestContext = requestContextFactory.create( sessionHolder.getSession() );
      currentRequestHolder.set( requestContext );
      return requestContext;
   }
   
   public void desctroyRequestContext( UserRequestContext requestContext ) {
      // TODO Auto-generated method stub
   }

   public UserRequestContext currentRequestContext() {
      return currentRequestHolder.get();
   }
   
   public UserSession currentUserSession() {
      UserRequestContext requestContext = currentRequestContext();
      return requestContext != null ? currentRequestContext().getUserSession() : null;
   }
   
   public User currentUser() {
      UserSession userSession = currentUserSession();
      return userSession != null ? userSession.getUser() : null;
   }

   public Application getApplicationInContext() {
      if (currentRequestContext() != null) {
         return currentRequestContext().getApplication();
      } else {
         return null; 
      }
   }

   public ProcessPuzzleContext getApplicationContext() {
      if (getApplicationInContext() != null) {
         return getApplicationInContext().getContext();
      } else {
         return null; 
      }
   }

   //Properties
   public UserRequestContextFactory getRequestContextFactory() {
      return requestContextFactory;
   }

   public UserSessionFactory getSessionFactory() {
      return sessionFactory;
   }


   //Private helper methods
}
