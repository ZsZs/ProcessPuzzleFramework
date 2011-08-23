/*
 * Created on Jul 10, 2006
 */
package com.processpuzzle.artifact_type_group.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.domain.RepositoryException;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactTypeGroupRepository extends GenericRepository<ArtifactTypeGroup> {

   public ArtifactTypeGroupRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
      // TODO Auto-generated constructor stub
   }

   public void addArtifactTypeGroup( DefaultUnitOfWork work, ArtifactTypeGroup typeGroup ) {
      add( work, typeGroup );
   }

   public void deleteArtifactTypeGroup( DefaultUnitOfWork work, ArtifactTypeGroup typeGroup ) {
      delete( work, typeGroup );
   }

   public void updateArtifactTypeGroup( DefaultUnitOfWork work, ArtifactTypeGroup typeGroup ) {
      update( work, typeGroup );
   }

   public ArtifactTypeGroup findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public RepositoryResultSet<ArtifactTypeGroup> findAllArtifactTypeGroups( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public ArtifactTypeGroup findByName( String groupName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactTypeGroup group = findByName( work, groupName );
      work.finish();
      return group;
   }

   public ArtifactTypeGroup findByName( DefaultUnitOfWork work, String groupName ) {
      DefaultQuery query = new DefaultQuery( ArtifactTypeGroup.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "name", groupName, ComparisonOperators.EQUAL_TO ) );
      ArtifactTypeGroup typeGroup = null;
      try{
         RepositoryResultSet<ArtifactTypeGroup> resultSet = findByQuery( work, query );
         if( resultSet.size() == 1 )
            typeGroup = resultSet.getEntityAt( 0 );
      }catch( RepositoryException e ){
         log.debug( e.getMessage() );
         return null;
      }
      return typeGroup;
   }

   protected Object findByIdentityExpression( String identityExpression ) {
      // TODO Auto-generated method stub
      return null;
   }

}
