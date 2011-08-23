package com.processpuzzle.address.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.DomainObjectTestTemplate;

import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class DistrictTest extends DomainObjectTestTemplate<District, DistrictTestFixture> {

   public DistrictTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Test public void instantiation_Requires_Name() {
      assertThat( sut.getName(), equalTo( DistrictTestFixture.DISTRIC_NAME ) );
   }
}
