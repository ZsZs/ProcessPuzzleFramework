package com.processpuzzle.user_session.domain;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class DefaultRequestContextTest {
   private static UserSession userSession;
   private UserRequestContext requestContext;
   private UserRequestContextFactory requestContextFactory;
   
   @BeforeClass
   public static void beforeAllTests() {
      MockitoAnnotations.initMocks( DefaultRequestContextTest.class );
      
//      userSession = StaticUserSessionHolder.getUserSession( mockUser );
   }
   
   @Before public void beforeEachTests() {
      requestContextFactory = new DefaultUserRequestContextFactory();
      requestContext = requestContextFactory.create( userSession );
   }
   
   @Test public void create() {
      assertThat( requestContext, notNullValue() );
      assertThat( requestContext.getId(), notNullValue() );
      assertThat( "Factory sores the newly created UserRequestContext in a thread local variable.", requestContextFactory.getRequestContext(), sameInstance( requestContext ));
      assertThat( "User session to which this reqest belongs can be retrieved.", requestContext.getUserSession(), sameInstance( userSession ));
   }
   
   @Test public void create_ShouldBypassRecreation() {
      String previousId = requestContext.getId();
      UserRequestContext returnedContext = requestContextFactory.create( requestContext );
      assertThat( returnedContext, sameInstance( requestContext ));
      assertThat( "Creates new id", returnedContext.getId(), not( equalTo( previousId )));
      assertThat( "Previously created ids are stored.", returnedContext.getIds().length, equalTo(2) );
   }
   
   @Test public void getRequestContext() {
      assertThat( "Request context can be retrieved from factory.", requestContextFactory.getRequestContext(), sameInstance( requestContext ));      
   }   
}
