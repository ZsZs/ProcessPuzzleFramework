/*
 * Created on Feb 10, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import hu.itkodex.litest.template.DomainObjectTestTemplate;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PersonTest extends DomainObjectTestTemplate<Person, PersonTestFixture>{

   public PersonTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Test
   public void getPersonName() {
      assumeThat( sut.getPersonName(), instanceOf(PersonName.class));
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person aPerson = fixture.getPersonRepository().findPersonById( work, sut.getId() );
      PersonName personName = aPerson.getPersonName();
      assertThat(personName, notNullValue());
      work.finish();
   }

   @Ignore
   @Test
   public final void testGetPassword() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person aPerson = fixture.getPersonRepository().findPersonByUserName( work, "barczi.beno" );
      assertEquals( "Web get:", "hello", aPerson.getSystemUser().getPassword() );
      work.finish();
   }
}
