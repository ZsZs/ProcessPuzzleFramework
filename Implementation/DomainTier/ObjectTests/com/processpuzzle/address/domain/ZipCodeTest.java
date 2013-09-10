package com.processpuzzle.address.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.litest.template.DomainObjectTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ZipCodeTest extends DomainObjectTestTemplate<ZipCode, ZipCodeTestFixture<ZipCode>> {

   public ZipCodeTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test public void instantiation_Requires_ZipValue() {
      ZipCode zipCode = new ZipCode(  ZipCodeTestFixture.ZIP_CODE_VALUE );
      assertThat( zipCode.getZipCode(), equalTo( ZipCodeTestFixture.ZIP_CODE_VALUE ) );
   }

   @Test( expected = ZipCodeConstraintViolationException.class )
   public void instantiation_WhenValueIsNegative_ThrowsException() {
      new ZipCode( new Integer( -1 ) );
   }

   @Test( expected = ZipCodeConstraintViolationException.class )
   public void instantiation_WhenValueIsLargerThanMaxValue_ThrowsException() {
      new ZipCode( new Integer( ZipCode.MAX_ZIP_VALUE +1 ) );
   }

   @Test public void setZipCode_RequiresZipValue() {
      ZipCode zipCode = templatedFixture.getZipCode(); 
      zipCode.setZipCode( ZipCodeTestFixture.ANOTHER_ZIP_CODE_VALUE );
      assertThat( templatedFixture.getZipCode().getZipCode(), equalTo( ZipCodeTestFixture.ANOTHER_ZIP_CODE_VALUE ));
   }

   @Test( expected = ZipCodeConstraintViolationException.class )
   public void setZipCode_WhenValueIsNegative_ThrowsExeption() {
      templatedFixture.getZipCode().setZipCode( new Integer( -1 ) );
   }

   @Test( expected = ZipCodeConstraintViolationException.class )
   public void setZipCode_WhenValueIsLargerThanMaxValue_ThrowsException() {
      templatedFixture.getZipCode().setZipCode( ZipCode.MAX_ZIP_VALUE +1 );
   }

   @Test public void setSettlement_AssociatesZipWithSettlement() {
      templatedFixture.getZipCode().setSettlement( templatedFixture.getSettlement() );
      assertThat( templatedFixture.getZipCode().getSettlement(), equalTo( templatedFixture.getSettlement() ));
   }

   @Test public void asString_ConvertsToZipValueToText() {
      assertEquals( templatedFixture.getZipCode().asString(), ZipCodeTestFixture.ZIP_CODE_TEXT );
   }
}
