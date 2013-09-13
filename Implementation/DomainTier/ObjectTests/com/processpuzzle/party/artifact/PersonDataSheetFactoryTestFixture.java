package com.processpuzzle.party.artifact;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.litest.template.FactoryTestEnvironment;
import com.processpuzzle.litest.template.FactoryTestFixture;

public class PersonDataSheetFactoryTestFixture extends FactoryTestFixture<PersonDataSheetFactory, PersonDataSheet> {
   private DefaultArtifactRepository artifactRepository;

   public PersonDataSheetFactoryTestFixture( FactoryTestEnvironment<PersonDataSheetFactory, ?> testEnvironment ) {
      super( testEnvironment );
   }
   
   public PersonDataSheet createTestPersonDataSheet() {
      PersonDataSheet personDataSheet = null;
      String birthDateStr = "1980.10.10.";
      SimpleDateFormat formatter = (SimpleDateFormat) DateFormat.getDateInstance();
      formatter.applyPattern("yyyy.mm.dd.");
      try {
         Date birthDate = formatter.parse( birthDateStr );
         TimePoint birthDateTP = new TimePoint( birthDate );
          
         personDataSheet = sut.create("Keszeg", "József", birthDateTP);
         
      } catch (ParseException e) { // can not occur
      }
      return personDataSheet;
   }
   
   //Properties
   public DefaultArtifactRepository getArtifactRepository() { return artifactRepository; }

   //Protected, private helper methods
   @Override
   protected void configureAfterSutInstantiation() {
      artifactRepository = applicationContext.getRepository(DefaultArtifactRepository.class);
   }

}
