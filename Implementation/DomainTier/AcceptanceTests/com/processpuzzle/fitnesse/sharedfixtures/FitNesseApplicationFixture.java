package com.processpuzzle.fitnesse.sharedfixtures;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.litest.template.DefaultApplicationFixture;
import com.processpuzzle.sharedfixtures.domaintier.ApplicationFixture;

public class FitNesseApplicationFixture implements ApplicationFixture<Application> {
   private static FitNesseApplicationFixture soleInstance;
   private static String contextDescriptorPath;
   private boolean isApplicationConfigured = false;
   private DefaultApplicationFixture applicationFixture;
   private Application application;
   private ProcessPuzzleContext configuration;
   private boolean isCatalogConfigured = false;
   private boolean isProductTypeConfigured = false;
   private boolean isPriceTypeConfigured = false;
   private boolean isOrderConfigured = false;
   private boolean isTestSuiteRunning = false;

   public static FitNesseApplicationFixture getInstance( String path ) {
      contextDescriptorPath = path;
      if( soleInstance == null ){
         soleInstance = new FitNesseApplicationFixture();
      }
      return soleInstance;
   }

   public void setUp() {
      if (!isApplicationConfigured) {
         //contextDescriptorPath = DomainComponentsTestConfiguration.FITNESSE_CONFIG_DESCRIPTOR_PATH;
         //applicationFixture = ConfigurableApplicationFixture.createInstance( contextDescriptorPath );
         applicationFixture.setUp();
         application = applicationFixture.getSUT();
         configuration = application.getContext();
   
         isApplicationConfigured = true;
      }
   }
   
   public void setUpTestSuite() {
      setUp();
      isTestSuiteRunning = true;
   }

   public void tearDown() {
      if (!isTestSuiteRunning) {
 System.out.println("Stopping FitnesseApplication: " + contextDescriptorPath);         
          if ( applicationFixture != null ) {
             applicationFixture.tearDown();
             applicationFixture = null;
          }
          application = null;
          configuration = null;
         
          isApplicationConfigured = false;
      }
   }
   
   public void tearDownTestSuite() {
      isTestSuiteRunning = false;
      tearDown();
   }

   public boolean isApplicationConfigured() {
      return isApplicationConfigured;
   }

   public Application getApplication() {
      return application;
   }

   public ProcessPuzzleContext getConfiguration() {
      return configuration;
   }

   public boolean isCatalogConfigured() {
      return isCatalogConfigured;
   }

   public void setCatalogConfigured( boolean isCatalogConfigured ) {
      this.isCatalogConfigured = isCatalogConfigured;
   }

   public boolean isProductTypeConfigured() {
      return isProductTypeConfigured;
   }

   public void setProductTypeConfigured( boolean isProductTypeConfigured ) {
      this.isProductTypeConfigured = isProductTypeConfigured;
      
   }

   public void setPriceTypeConfigured( boolean isPriceTypeConfigured ) {
      this.isPriceTypeConfigured = isPriceTypeConfigured;
      
   }

   public void setOrderConfigured( boolean isOrderConfigured ) {
      this.isOrderConfigured = isOrderConfigured;
     
   }

   @Override
   public <D> D defineDOC( Class<D> dependsOnComponent ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public <E> void defineExpectedValueFor( String propertyName, E expectedValue ) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public Object getExpectedValueFor( String propertyName ) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override public Application getSUT() { return application; }
   @Override public Class<Application> getSUTClass() { return Application.class; }
   @Override public boolean isConfigured() { return isApplicationConfigured; }
}
