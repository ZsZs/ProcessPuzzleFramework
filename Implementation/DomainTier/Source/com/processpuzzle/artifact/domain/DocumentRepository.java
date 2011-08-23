package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.query.OrderSpecifier;
import hu.itkodex.commons.persistence.query.OrderingDirections;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class DocumentRepository extends GenericArtifactRepository<Document> {

   public DocumentRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addDocument( DefaultUnitOfWork work, Document document ) {
      super.add( work, Document.class, document );
   }

   public void updateDocument( DefaultUnitOfWork work, Document document ) {
      update( work, Document.class, document );
   }

   public void delete( DefaultUnitOfWork work, Document document ) {
      delete( work, Document.class, document );
   }

   public void delete( DefaultUnitOfWork work, String documentName ) {
      Document doc = findDocumentById( work, documentName );
      if( doc != null ){
         delete( work, doc );
      }
   }

   public RepositoryResultSet<Document> findAll( DefaultUnitOfWork work ) {
      return findAll( work, Document.class );
   }

   public Document findDocumentById( DefaultUnitOfWork work, String id ) {
      return (Document) findById( work, Document.class, new Integer( id ) );
   }

   public RepositoryResultSet<Document> findAllWithOrdering( DefaultUnitOfWork work, String propertyName ) {
      DefaultQuery q = new DefaultQuery( Document.class );
      q.getQueryOrder().addOrderSpecifier( new OrderSpecifier( propertyName, OrderingDirections.Ascending ) );
      return findByQuery( work, q );
   }

   public Document findByName( DefaultUnitOfWork work, String name ) {
      DefaultQuery query = new DefaultQuery( Document.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", name, ComparisonOperators.EQUAL_TO ) );
      return (Document) findByQuery( work, query );
   }
}
