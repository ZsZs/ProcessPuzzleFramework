package com.processpuzzle.sharedfixtures.domaintier;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class PartyTypeFixture {
   public static final String PERSON_TYPE_NAME = "PersonType";
   public static final String COMPANY_TYPE_NAME = "CompanyType";
   protected PartyTypeFactory partyTypeFactory;
   protected PartyType personType;
   protected PartyType companyType;

   public void setUp() {
      partyTypeFactory = new PartyTypeFactory();
      
      personType = partyTypeFactory.create( PERSON_TYPE_NAME );
      personType.setDescription( "Represents a natural person." );
      
      companyType = partyTypeFactory.create( COMPANY_TYPE_NAME );
      companyType.setDescription( "Represents the type of all legal business entities." );      
   }

   public void tearDown() {
      personType = null;
      companyType = null;
   }
   
   public void savePersonType( ProcessPuzzleContext applicationContext ) { 
      if( personType.getId() == null ) saveAggregateRoot( personType, applicationContext ); 
   }

   public void deletePersonType( ProcessPuzzleContext applicationContext ) {
      if( personType != null && personType.getId() != null ) {
         deleteAggregateRoot( personType, applicationContext );
         personType = null;
      }
   }

   public PartyType getPersonType() { return personType; }
   public PartyType getCompanyType() { return companyType; }

   @SuppressWarnings("unchecked")
   protected void saveAggregateRoot( AggregateRoot aggregateRoot, ProcessPuzzleContext applicationContext ) {
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.add( work, aggregateRoot );
      work.finish();
   }

   @SuppressWarnings("unchecked")
   protected void deleteAggregateRoot( AggregateRoot aggregateRoot, ProcessPuzzleContext applicationContext ) {
      Repository repository = applicationContext.getRepositoryByEntityClass( aggregateRoot.getClass() );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.delete( work, aggregateRoot );
      work.finish();
   }
}
