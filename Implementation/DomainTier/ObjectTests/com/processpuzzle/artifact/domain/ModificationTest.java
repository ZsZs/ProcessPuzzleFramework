package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonFactory;
import com.processpuzzle.party.domain.PersonName;

public class ModificationTest {
   private PersonFactory personFactory;
   private Person person;

   @Before public void setUp() throws Exception {
      personFactory = new PersonFactory();
      person = personFactory.create(new PersonName("Kis", "Nagy"), new TimePoint(new Date(System.currentTimeMillis())));
   }

   @After
   public void tearDown() throws Exception {}

   @Ignore
   @Test
   public final void testCreateModification_withEmptyConstructor() {
      Modification modification = new Modification();
      assertNotNull( "A modification object is created.", modification );
   }
}
