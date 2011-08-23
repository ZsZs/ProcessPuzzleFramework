package com.processpuzzle.user_session.domain;



/**
 * Factory for getting hold of the currently valid request context, ie. the context
 * associated with the current user's current request.
 *
 * @author  kkj
 * @since 26-09-2004 17:29:20
 * @version $Revision: 1.1.1.1 $
 */
public interface UserRequestContextFactory {
    UserRequestContext create( UserSession userSession );
    UserRequestContext create( UserRequestContext userRequestContext );

    UserRequestContext getRequestContext();

    void delete();
}
