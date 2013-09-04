package com.processpuzzle.party.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hu.itkodex.litest.template.RepositoryTestTemplate;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.commons.rdbms.NoDataAvailableException;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class PersonRepositoryTest extends RepositoryTestTemplate<PersonRepository, PersonRepositoryTestFixture, Person> {

   public PersonRepositoryTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @Override
   @Test
   public void testFindById() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Person savedPerson = sut.findById( work, root.getId() );

      assertThat( savedPerson.getId(), equalTo(root.getId())  );
      assertThat( savedPerson.getPartyName(), notNullValue() );
      assertThat( savedPerson.getPersonName().getName(), equalTo( root.getPersonName().getName() ) );
      work.finish();

   }

   @Override
   @Test
   public void testAdd_ForOwnedAttributesAndComponents() {
      assertThat( databaseSpy.retrieveColumnFromRow( "T_PARTY_NAME", root.getPartyName().getId(), String.class, "name" ), equalTo( root.getPartyName().getName() ) );
   }

   @Override
   @Test
   public void testAdd_ForReferencedAggregateRoots() {}

   @Override
   @Test
   public void testDelete_ForOwnedAttributesAndComponents() {
      Person testPerson = fixture.createTestPerson();
      Integer partyNameForeignKey = testPerson.getPartyName().getId();

      DefaultUnitOfWork deleteWork = new DefaultUnitOfWork( true );
      repository.delete( deleteWork, testPerson );
      deleteWork.finish();

      try{
         databaseSpy.retrieveColumnFromRow( "T_PARTY_NAME", partyNameForeignKey, String.class, "name" );
         fail();
      }catch( NoDataAvailableException e ){
         assertTrue( "Party name is deleted", true );
      }
   }

   @Test
   public void findPersonByPartyName() {
      UnitOfWork work = new DefaultUnitOfWork( true );
      String expectedPartyName = "Bárczi Benõ";
      List<Party> parties = fixture.getPartyRepository().findPartiesByPartyName( work, expectedPartyName );
      work.finish();

      assertThat( parties, notNullValue() );
      assertThat( parties.size(), is( 1 ) );
   }
   
   @Test
   public void testFindByPartyTypeName() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      String partyTypeName = root.getType().getName();
      RepositoryResultSet<Person> result = sut.findByPartyTypeName( work, partyTypeName );
      Person p = (Person) result.iterator().next();
      assertEquals( "Person found by PartyType name", root.getId(), p.getId() );
      work.finish();
   }


   @Override
   @Test
   public void testFindAll_ForResultCount() {
      assertEquals( 1, repository.findAll( testWork ).size() );
   }



   @Override
   public void testFindById_ForEagerLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testFindById_ForLazyLoadedComponents() {
   // TODO Auto-generated method stub

   }

   @Override
   @Test
   public void testFindByQuery_ForComponentAttributes() {
    }

   @Override
   public void testFindByQuery_ForDirectAttributes() {
   // TODO Auto-generated method stub

   }

   @Override
   public void testUpdate_ForOwnedAttributesAndComponents() {
      root.renameName( "newname" );
      root.setValid( new TimePeriod( new TimePoint( 2008, 1, 1 ), new TimePoint( 2008, 12, 31 ) ) );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.update( work, root );

      assertEquals( "New name is updated", root.getName(), repository.findById( work, root.getId() ).getName() );
      work.finish();

      assertThat( "Valid TimePeriod is updated", databaseSpy.retrieveColumnFromRow( "T_PARTY", root.getId(), Timestamp.class,
            "validBegin" ), equalTo( new Timestamp( root.getValid().getBegin().getValue().getTime() ) ) );
      assertThat( "Valid TimePeriod is updated", databaseSpy.retrieveColumnFromRow( "T_PARTY", root.getId(), Timestamp.class,
            "validEnd" ), equalTo( new Timestamp( root.getValid().getEnd().getValue().getTime() ) ) );

   }

   @Override
   @Test
   public void testUpdate_ForReferencedAggregateRoots() {

      // SETUP
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyType type = new PartyType( "OtherType" );
      fixture.getPartyTypeRepository().add( work, type );

      // EXERCISE
      //root.setPartyType( type );
      repository.update( work, root );
      work.finish();

      // VERIFY
      assertEquals( "PartyType is updated", root.getType().getName(), databaseSpy.retrieveColumnFromRow( "T_PARTY_TYPE", root.getType().getId(), String.class, "name" ) );

      // TEARDOWN
      DefaultUnitOfWork work2 = new DefaultUnitOfWork( true );
      //root.setPartyType( partyType );
      repository.update( work2, root );
      fixture.getPartyTypeRepository().delete( work2, type );
      work2.finish();
   }

}
