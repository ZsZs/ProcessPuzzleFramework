/*
Name: 
    - DocumentRepository

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.artifact.domain;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.query.OrderSpecifier;
import com.processpuzzle.commons.persistence.query.OrderingDirections;
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
