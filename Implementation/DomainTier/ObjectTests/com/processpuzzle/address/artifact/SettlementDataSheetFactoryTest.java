package com.processpuzzle.address.artifact;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.ArtifactFactoryTestTemplate;

import org.junit.Test;

import com.processpuzzle.address.artifact.SettlementDataSheet;
import com.processpuzzle.address.artifact.SettlementDataSheetFactory;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class SettlementDataSheetFactoryTest extends ArtifactFactoryTestTemplate<SettlementDataSheetFactory, SettlementDataSheetFactoryTestFixture, SettlementDataSheet>{
   private SettlementDataSheet budapest;
   
   public SettlementDataSheetFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test
   @Override 
   public void create_ForSuccess() {
      //SETUP: Imlicit setup.
      
      //EXCERCISE:
      SettlementDataSheet budapest = sut.create( "Budapest" );
      
      //VERIFY:
      assertThat( budapest.getName(), equalTo( "Budapest" ));  
   }
   
   @Test (expected = EntityIdentityCollitionException.class ) 
   @Override public void create_ForCollision() {
      budapest = sut.create( "Budapest" );      
      templatedFixture.getSettlementDataSheetRepository().add( budapest );
      
      //EXCERCISE:
      sut.create( "Budapest" );
   }
}
