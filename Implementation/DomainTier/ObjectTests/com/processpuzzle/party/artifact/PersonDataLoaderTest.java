package com.processpuzzle.party.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.dom4j.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.address.artifact.SettlementDataLoader;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.party.artifact.PersonDataLoader;
import com.processpuzzle.party.artifact.PersonDataSheet;
import com.processpuzzle.party.artifact.PersonDataSheetRepository;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.BusinessDefinitionFixture;
import com.processpuzzle.sharedfixtures.domaintier.SystemArtifactsFixture;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class PersonDataLoaderTest {
   
   private static final String personXMLSourcePath = "classpath:com/itcodex/objectpuzzle/party_management/party/artifact/TestPerson.xml";
   private static final String personXMLSchemePath = "classpath:com/itcodex/objectpuzzle/party_management/party/artifact/Person.xsd";
   private static final String settlementXMLSourcePath = "classpath:com/itcodex/objectpuzzle/address/settlement/artifact/TestSettlement.xml";
   private static final String settlementXMLSchemePath = "classpath:com/itcodex/objectpuzzle/address/settlement/artifact/Settlement.xsd";
   private static ProcessPuzzleContext applicationContext;
   private static PersonDataLoader dataLoader = null;
   private static SettlementDataLoader settlementDataLoader = null;
   private static BusinessDefinitionFixture businessDefinitionFixture;
   private static SystemArtifactsFixture systemArtifactsFixture;
   private static PersonDataSheetRepository personDataSheetRepository;
   
   @BeforeClass public static void beforeAllTests() throws Exception {
      businessDefinitionFixture = BusinessDefinitionFixture.getInstance();
      businessDefinitionFixture.setUp();
      applicationContext = businessDefinitionFixture.getApplicationContext();

      saveCurrentUser();
      
      systemArtifactsFixture = SystemArtifactsFixture.getInstance( applicationContext );
      systemArtifactsFixture.setUp();
      
      personDataSheetRepository = applicationContext.getRepository(PersonDataSheetRepository.class);
      
      settlementDataLoader = new SettlementDataLoader( settlementXMLSourcePath,  settlementXMLSchemePath );
      settlementDataLoader.loadData();
      
      dataLoader = new PersonDataLoader( personXMLSourcePath,  personXMLSchemePath );
      dataLoader.loadData();
   }

   @Before
   public void beforeEachTests() {
      
   }
   
   @After
   public void afterEachTests() {
   }

   @Test
   public void testLoadData_ForDocumentLoad() {
      Document xmlDocument = dataLoader.getDocument();
      assertTrue( "XmlDataLoader reads xml in and instantiates a DOM object", xmlDocument instanceof Document );
      assertTrue("The readed document has content.", xmlDocument.hasContent() );
   }
   
   @Test
   public void loadData() throws Exception {
      List<PersonDataSheet> savedPersons = dataLoader.getSavedPeople();
    
    for (PersonDataSheet savedPersonDataSheet : savedPersons) {
       System.out.println("PersonDataLoaderTest - savedPersonDataSheet.getName(): " + savedPersonDataSheet.getName());
       DefaultUnitOfWork work = new DefaultUnitOfWork(true);
       PersonDataSheet reloadedPersonDataSheet = personDataSheetRepository.findById( work, savedPersonDataSheet.getId() );

       assertThat(reloadedPersonDataSheet, notNullValue());
       assertThat(reloadedPersonDataSheet.getPerson(), notNullValue());
       assertThat(reloadedPersonDataSheet.getPerson().getName(), equalTo(savedPersonDataSheet.getName()));
       
       Person person = reloadedPersonDataSheet.getPerson();
       assertThat(person.getDefaultGeographicAddress(), notNullValue());
//      assertThat(person.getDefaultTelecomAddress(), notNullValue());
       work.finish();
      
    }
      //assertThat(dataLoader.getCountOfSavedPeople(), is(2));
      
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
