package com.processpuzzle.address.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.ArtifactFactoryTestTemplate;

import org.junit.Test;

import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.artifact.ArtifactLayerTestConfiguration;

public class SettlementDataSheetFactoryTest extends ArtifactFactoryTestTemplate<SettlementDataSheetFactory, SettlementDataSheetFactoryTestFixture, SettlementDataSheet>{
   
   public SettlementDataSheetFactoryTest() {
      super( ArtifactLayerTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test @Override 
   public void create_ForSuccess() {
      //SETUP: Imlicit setup.
      
      //EXCERCISE:
      SettlementDataSheet settlement = sut.create( templatedFixture.getSettlementName(), templatedFixture.getCountryName() );
      
      //VERIFY:
      assertThat( settlement.getName(), equalTo( templatedFixture.getSettlementName() ));  
   }
   
   @Test (expected = EntityIdentityCollitionException.class ) 
   @Override public void create_ForCollision() {
      SettlementDataSheet settlement = sut.create( templatedFixture.getSettlementName(), templatedFixture.getCountryName() );      
      templatedFixture.getSettlementDataSheetRepository().add( settlement );
      
      //EXCERCISE:
      sut.create( templatedFixture.getSettlementName(), templatedFixture.getCountryName() );
   }
}
