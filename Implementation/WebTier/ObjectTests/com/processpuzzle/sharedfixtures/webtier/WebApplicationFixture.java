package com.processpuzzle.sharedfixtures.webtier;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mockrunner.mock.web.MockServletContext;
import com.mockrunner.mock.web.WebMockObjectFactory;
import com.mockrunner.servlet.ServletTestModule;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.ApplicationException;
import com.processpuzzle.application.domain.ApplicationUninstallationException;
import com.processpuzzle.application.domain.ProcessPuzzleWebApplication;
import com.processpuzzle.application.domain.WebApplication;
import com.processpuzzle.application.domain.WebApplicationManager;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.user_session.domain.DefaultUserSession;
import com.processpuzzle.user_session.domain.StaticUserSessionHolder;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.user_session.domain.UserSession;
import com.processpuzzle.user_session.domain.UserSessionHolder;

public class WebApplicationFixture {
   private static WebApplicationFixture soleInstance;
   private static MockServletContextFixture contextFixture;
   private String configurationDescriptorPath;
   private boolean isConfigured;
   private WebApplicationManager applicationManager;
   private WebApplication application;
   private MockServletContext servletContext;
   private HttpSession session;
   private WebMockObjectFactory factory;
   private ServletTestModule testModule;
   private Map<Class<? extends Filter>, Filter> filters = new HashMap<Class<? extends Filter>, Filter>();
   
   //Public instantiation methods
   public static WebApplicationFixture getInstance() {
      if( soleInstance == null ) {
         soleInstance = new WebApplicationFixture( WebTierTestConfiguration.CONFIGURATION_DESCRIPTOR_PATH );
      }
      
      return soleInstance;
   }
   
   //Public mutators
   public void addRequestParameter( String key, String value ) {
      testModule.addRequestParameter( key, value );
   }
   
   public void createServlet( Class<? extends HttpServlet> servletClass ) {
      testModule.createServlet( servletClass );
   }

   public void doPost() {
      testModule.doPost();
   }
   
   public void setUp() {
      if( isConfigured ) return;

      setUpServletTestModule();      
      setUpContextFixture();
      setUpApplication();
      createTestUserAndRequestContext();
      
      isConfigured = true;
   }
   
   public void tearDown() {
      if( !isConfigured ) return;
      
      try{
         applicationManager.unInstall( application );
      }catch( ApplicationUninstallationException e ){
         e.printStackTrace();
      }
      
      isConfigured = false;
   }
   
   public ProcessPuzzleContext getApplicationContext() { return application.getContext(); }

   //Public accessors
   public @SuppressWarnings("unchecked") <F extends Filter> F getFilter( Class<F> filterClass ) {
      return (F) filters.get( filterClass );
   }

   public BufferedReader getOutputAsBufferedReader() { return testModule.getOutputAsBufferedReader(); }
   public HttpServletRequest getRequest() { return factory.getWrappedRequest(); }
   public HttpSession getSession() { return session; }
   public ServletContext getServletContext() { return servletContext; }
   public WebMockObjectFactory getWebMockObjectFactory() { return factory; }
   
   //Protected instantiation
   protected WebApplicationFixture( String configurationDescriptorPath ) {
      this.configurationDescriptorPath = configurationDescriptorPath;
      isConfigured = false;
   }
   
   private void createTestUserAndRequestContext() {
      UserFactory userFactory = getApplicationContext().getEntityFactory( UserFactory.class );
      UserRepository userRepository = getApplicationContext().getRepository( UserRepository.class );
      User testUser = userFactory.createUser( "ProcessPuzzleTestUser", "ProcessPuzzle" );
      userRepository.add( testUser );
      
      UserRequestManager requestManager = UserRequestManager.getInstance();
      UserSession userSession = new DefaultUserSession( testUser, application );
      UserSessionHolder sessionHolder = new StaticUserSessionHolder( userSession );
      requestManager.createRequestContext( sessionHolder );
   }

   //Protected, private helper methods
   private void setUpServletTestModule() {
      factory = new WebMockObjectFactory();

      testModule = new ServletTestModule( factory );
      testModule.setDoChain( true );
      
      session = factory.getWrappedRequest().getSession();
      servletContext = (MockServletContext) session.getServletContext();      
   }

   private void setUpApplication() {
      try{
         applicationManager = new WebApplicationManager( contextFixture.getApplicationStoragePath(), contextFixture.getResourceLoader(), contextFixture.getServletContext() );
         application = applicationManager.installWebApplication( "ProcessPuzzle Test Applicaton", ProcessPuzzleWebApplication.class, configurationDescriptorPath );      
      }catch( InstantiationException e ){
         e.printStackTrace();
      }catch( ApplicationException e ){
         e.printStackTrace();
      }
   }

   private void setUpContextFixture() {
      try{ 
         contextFixture = new MockServletContextFixture( servletContext );
         contextFixture.setUp();
      }catch( FileNotFoundException e ){ 
         e.printStackTrace(); 
      }      
   }

}
