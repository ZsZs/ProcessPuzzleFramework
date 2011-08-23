package com.processpuzzle.user.session.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;

/**
 * Utility for storing the user session in an HttpSession. Caveat: This utility expects the RequestContext to be of type DefaultRequestContext.
 * Do not use with custom implementations of RequestContext.
 * 
 * @author kkj
 * @version $Revision: 1.1.1.1 $
 * @since 17-01-2005 12:59:01
 */
public class HttpSessionUserSessionHolder implements UserSessionHolder{
   public static final String USER_SESSION = "processpuzzle.user.session";
   private HttpServletRequest httpServletRequest;

   public HttpSessionUserSessionHolder( HttpServletRequest httpServletRequest ) {
      this.httpServletRequest = httpServletRequest;
   }

   public UserSession getSession() {
      HttpSession httpSession = httpServletRequest.getSession(true);
      UserSession userSession = (UserSession) httpSession.getAttribute( USER_SESSION );

      if (userSession == null) {
//         UserRepository userRepository = (UserRepository) ProcessPuzzleContext.getInstance().getRepository( UserRepository.class );
//         User anonymousUser = userRepository.findUserByName( PredefinedUser.ANONYMOUS.getUserName() );
//         userSession = new DefaultUserSession( anonymousUser );
//         setUserSession(httpServletRequest, userSession);
      }

      return userSession;
   }

   public void setSession( UserSession userSession ) {
      HttpSession httpSession = httpServletRequest.getSession(true);
      httpSession.setAttribute(USER_SESSION, userSession);
   }
}
