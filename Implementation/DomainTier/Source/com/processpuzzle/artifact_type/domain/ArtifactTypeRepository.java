package com.processpuzzle.artifact_type.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

public class ArtifactTypeRepository extends GenericRepository<ArtifactType> {

   public ArtifactTypeRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public void addArtifactType( DefaultUnitOfWork work, ArtifactType artifactType ) {
      add( work, artifactType );
   }

   public void deleteArtifactType( DefaultUnitOfWork work, ArtifactType artifactType ) {
      delete( work, artifactType );
   }

   public void deleteArtifactType( DefaultUnitOfWork work, Integer id ) {
      ArtifactType artifactType = findById( work, id );
      if( artifactType != null )
         deleteArtifactType( work, artifactType );
   }

   public void updateArtifactTypes( DefaultUnitOfWork work, ArtifactType artifactType ) {
      update( work, artifactType );
   }

   public RepositoryResultSet<ArtifactType> findAllArtifactTypes( DefaultUnitOfWork work ) {
      return findAll( work );
   }

   public ArtifactType findByArtifactClassName( String className ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultQuery query = new DefaultQuery( ArtifactType.class );
      query.getQueryCondition().addAttributeCondition( new TextAttributeCondition( "artifactClassName", className, ComparisonOperators.EQUAL_TO ) );
      RepositoryResultSet<ArtifactType> resultSet = findByQuery( work, query );
      work.finish();
      if( resultSet.size() == 1 )
         return resultSet.getEntityAt( 0 );
      else
         return null;
   }

   public ArtifactType findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public ArtifactType findByName( String typeName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ArtifactType artifactType = findArtifactTypeByName( work, typeName );
      work.finish();
      return artifactType;
   }

   public ArtifactType findArtifactTypeByName( UnitOfWork work, String typeName ) {
      ArtifactTypeIdentity identity = new ArtifactTypeIdentity();
      identity.getQueryContext().addTextValueFor( "nameValue", typeName );
      return findByIdentityExpression( work, identity );
   }

   protected Object findByIdentityExpression( DefaultUnitOfWork work, String identityExpression ) {
      return findArtifactTypeByName( work, identityExpression );
   }

   // public void addArtifactViewType(UnitOfWork work,ArtifactViewType viewType) {
   // add(work, viewType);
   // }

   // @Override
   // protected void setSupportedAggregateRootClass() {
   // // TODO Auto-generated method stub
   //      
   // }
}
