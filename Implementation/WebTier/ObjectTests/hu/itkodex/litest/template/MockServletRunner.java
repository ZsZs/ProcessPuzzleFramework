package hu.itkodex.litest.template;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.io.FileNotFoundException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Maps;
import com.mockrunner.mock.web.MockServletContext;
import com.mockrunner.mock.web.WebMockObjectFactory;
import com.mockrunner.servlet.ServletTestModule;
import com.processpuzzle.application.configuration.control.ApplicationLifecycleListener;
import com.processpuzzle.application.configuration.control.ProcessPuzzleCommandMapping;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.application.configuration.domain.WebApplicationContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.application.domain.WebApplication;
import com.processpuzzle.application.security.domain.AnonymousUser;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.litest.fixture.GenericTestFixture;
import com.processpuzzle.litest.fixture.TransientFreshFixture;
import com.processpuzzle.sharedfixtures.webtier.MockServletContextFixture;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class MockServletRunner extends GenericTestFixture<Object> implements TransientFreshFixture<Object> {
   public static final String DEFAULT_SKIN_PATH = "Default";
   public static final String DEFAULT_SKIN_NAME = "ProcessPuzzle";
   private MockServletContextFixture contextFixture;
   private WebMockObjectFactory factory;
   private Map<Class<? extends Filter>, Filter> filters = Maps.newHashMap();
   private WebApplication mockApplication;
   private WebApplicationContext mockApplicationContext;
   private AnonymousUser mockAnonymousUser;
   private User mockUser;
   private MockServletContext servletContext;
   private Map<Class<? extends Servlet>, Servlet> servlets = Maps.newHashMap();
   private HttpSession session;
   private ServletTestModule testModule;
   private PropertyContext mockPropertyContext;

   public MockServletRunner() {
   }

   //Public mutators
   public void addRequestParameter( String parameterName, String parameterValue ){
      testModule.addRequestParameter( parameterName, parameterValue );
   }
   
   public void doDelete() { testModule.doDelete(); }
   public void doFilter() { testModule.doFilter(); }
   public void doGet() { testModule.doGet(); }
   public void doHead() { testModule.doHead(); }
   public void doOptions() { testModule.doOptions(); }
   public void doPost() { testModule.doPost(); }
   public void doPut() { testModule.doPut(); }
   public void doTrace() { testModule.doTrace(); }
   
   //Properties
   public User getAnonymousUser() { return mockAnonymousUser; }
   public Application getApplication() { return mockApplication; }
   public ProcessPuzzleContext getApplicationContext() { return mockApplicationContext; }
   public WebMockObjectFactory getFactory() { return factory; }
   @SuppressWarnings("unchecked") public <F extends Filter> F getFilter( Class<F> filterClass ) { return (F) filters.get( filterClass ); }
   public User getMockUser() { return mockUser; }
   public HttpServletResponse getResponse() { return factory.getWrappedResponse(); }
   public HttpServletRequest getRequest() { return factory.getWrappedRequest(); }
   public Object getRequestAttribute( String attributeName ) { return getRequest().getAttribute( attributeName ); }
   public RequestDispatcher getRequestDispathcer( String pageName ){ return factory.getMockServletContext().getRequestDispatcher( pageName ); }
   public String getRequestDispatcherPath( String pageName ) {  return factory.getMockServletContext().getContextPath(); }
   public HttpSession getSession() { return session; }
   public ServletContext getServletContext() { return servletContext; }
   public WebMockObjectFactory getWebMockObjectFactory() { return factory; }

   public void setFilter( Class<? extends Filter> filterClass ) { Filter filter = testModule.createFilter( filterClass ); filters.put( filterClass, filter ); }
   public void setServletContextAttribute( String attributeName, Object attributeValue ) { servletContext.setAttribute( attributeName, attributeValue ); }
   public void setServlet( Class<? extends Servlet> servletClass ) { Servlet servlet = testModule.createServlet( servletClass ); servlets.put( servletClass, servlet ); }

   //Private helper methods
   private void createMockAnonymousUser() {
      mockAnonymousUser = mock( AnonymousUser.class );
      mockUser = mock( User.class );
   }

   private void createMockApplicationContext() {
      mockApplicationContext = mock( WebApplicationContext.class );
      when( mockApplication.getContext() ).thenReturn( mockApplicationContext );
      
      UserRepository mockUserRepository = mock( UserRepository.class );
      when( mockUserRepository.findAnonymousUser() ).thenReturn( mockAnonymousUser );
      when( mockApplicationContext.getRepository( UserRepository.class )).thenReturn( mockUserRepository );
   }

   private void createMockApplication() {
      mockApplication = mock( WebApplication.class );
      servletContext.setAttribute( ApplicationLifecycleListener.APPLICATION_OBJECT, mockApplication );
   }
   
   private void createMockPropertyContext(){
      mockPropertyContext = mock( PropertyContext.class );
      when( mockPropertyContext.getProperty( PropertyKeys.PRESENTATION_DEFALT_SKIN_NAME.getDefaultKey() )).thenReturn( DEFAULT_SKIN_NAME );
      when( mockPropertyContext.getProperty( PropertyKeys.PRESENTATION_DEFALT_SKIN_PATH.getDefaultKey() )).thenReturn( DEFAULT_SKIN_PATH );
      when( mockPropertyContext.getProperty( PropertyKeys.COMMAND_MAPPING.getDefaultKey() )).thenReturn( ProcessPuzzleCommandMapping.class.getName() );

      when( mockApplicationContext.getPropertyContext() ).thenReturn( mockPropertyContext );
      
   }
   
   private void createUserRequestContext() {
      UserRequestManager requestManager = UserRequestManager.getInstance();
      UserSessionHolder sessionHolder = new StaticUserSessionHolder();
      requestManager.createSession( sessionHolder, mockUser, mockApplication );
      requestManager.createRequestContext( sessionHolder );
   }

   @Override
   protected void configureBeforeSutInstantiation() {
      factory = new WebMockObjectFactory();

      testModule = new ServletTestModule( factory );
      testModule.setDoChain( true );
      
      session = factory.getWrappedRequest().getSession();
      servletContext = (MockServletContext) session.getServletContext();
      
      contextFixture = new MockServletContextFixture( servletContext );
      try{
         contextFixture.setUp();
      }catch( FileNotFoundException e ){
         e.printStackTrace();
      }
      
      createMockAnonymousUser();
      createMockApplication();
      createMockApplicationContext();
      createMockPropertyContext();
      createUserRequestContext();
   }

   @Override
   protected void configureAfterSutInstantiation() {
   }

   @Override protected Object instantiateSUT() {
      return null;
   }

   @Override
   protected void releaseResources() {
      testModule.releaseFilters();
      testModule = null;
      
      filters.clear();
      servlets.clear();
      
      contextFixture.tearDown();
      factory = null;
      servletContext = null;
   }
}
