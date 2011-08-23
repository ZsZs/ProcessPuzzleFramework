package com.processpuzzle.party.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.address.artifact.SettlementDataLoader;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.party.artifact.CompanyDataLoader;
import com.processpuzzle.party.artifact.CompanyDataSheet;
import com.processpuzzle.party.artifact.CompanyDataSheetRepository;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.BusinessDefinitionFixture;
import com.processpuzzle.sharedfixtures.domaintier.SystemArtifactsFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class CompanyDataLoaderTest {

   private static final String companyXMLSourcePath = "classpath:com/itcodex/objectpuzzle/party_management/party/artifact/TestCompany.xml";
   private static final String companyXMLSchemePath = "classpath:com/itcodex/objectpuzzle/party_management/party/artifact/Company.xsd";
   private static final String settlementXMLSourcePath = "classpath:com/itcodex/objectpuzzle/address/settlement/artifact/TestSettlement.xml";
   private static final String settlementXMLSchemePath = "classpath:com/itcodex/objectpuzzle/address/settlement/artifact/Settlement.xsd";
   private static ProcessPuzzleContext applicationContext;
   private static CompanyDataLoader dataLoader = null;
   private static SettlementDataLoader settlementDataLoader = null;
   private static BusinessDefinitionFixture businessDefinitionFixture;
   private static SystemArtifactsFixture systemArtifactsFixture;
   private static CompanyDataSheetRepository companyDataSheetRepository;
   
   @BeforeClass public static void beforeAllTests() throws Exception {
      businessDefinitionFixture = BusinessDefinitionFixture.getInstance();
      businessDefinitionFixture.setUp();
      applicationContext = businessDefinitionFixture.getApplicationContext();

      saveCurrentUser();

      systemArtifactsFixture = SystemArtifactsFixture.getInstance( applicationContext );
      systemArtifactsFixture.setUp();

      companyDataSheetRepository = applicationContext.getRepository(CompanyDataSheetRepository.class);

      settlementDataLoader = new SettlementDataLoader( settlementXMLSourcePath,  settlementXMLSchemePath );
      settlementDataLoader.loadData();
      
      dataLoader = new CompanyDataLoader( companyXMLSourcePath,  companyXMLSchemePath );
      dataLoader.loadData();
   }

   @Before
   public void beforeEachTests() {
      
   }
   
   @After
   public void afterEachTests() {
   }

   @Test
   public void loadData() throws Exception {
      List<CompanyDataSheet> savedCompanies = dataLoader.getSavedCompanies();
      
      for (CompanyDataSheet savedCompanyDataSheet : savedCompanies) {
         System.out.println("CompanyDataLoaderTest - savedCompanyDataSheet.getName(): " + savedCompanyDataSheet.getName());
         DefaultUnitOfWork work = new DefaultUnitOfWork(true);
         CompanyDataSheet reloadedCompanyDataSheet = companyDataSheetRepository.findById( work, savedCompanyDataSheet.getId() );

         assertThat(reloadedCompanyDataSheet, notNullValue());
         assertThat(reloadedCompanyDataSheet.getCompany(), notNullValue());
         assertThat(reloadedCompanyDataSheet.getCompany().getName(), equalTo(savedCompanyDataSheet.getName()));
         
         Company reloadedCompany = reloadedCompanyDataSheet.getCompany();
         assertThat(reloadedCompany.getDefaultGeographicAddress(), notNullValue());
         
         work.finish();
        
      }
   }
   
   @AfterClass public static void afterAllTests() throws Exception {
      businessDefinitionFixture.tearDown();
   }
   
   private static void saveCurrentUser() {
      User currentUser = UserRequestManager.getInstance().currentUser();
      UserRepository userRepository = applicationContext.getRepository( UserRepository.class );
      userRepository.add( currentUser );
   }
}
