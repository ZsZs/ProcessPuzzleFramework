package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.litest.template.ApplicationObjectTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PrefixedIncNumberedFactoryTest extends ApplicationObjectTestTemplate<PrefixedIncNumberedIdFactory, PrefixedIncNumberedFactoryTestFixture> {
   
   public PrefixedIncNumberedFactoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Ignore
   @Test
   public void createNewIdentifier() {
      UniqueIdentifier identifier = sut.createNewIdentifier();
      assertThat(identifier, notNullValue());
      assertThat(identifier, instanceOf(TestUniqueIdentifier.class));
   }
   
   @Ignore
   @Test
   public void createNewIdentifier_withDinamicPrefix() {
      String dinamicPrefix = "XXXX";
      UniqueIdentifier identifier = sut.createNewIdentifier(dinamicPrefix);

      assertThat(identifier, notNullValue());
      assertThat(identifier, instanceOf(TestUniqueIdentifier.class));
      assertThat(identifier.getIdentifier().startsWith( dinamicPrefix ), is(true));
   }
}
