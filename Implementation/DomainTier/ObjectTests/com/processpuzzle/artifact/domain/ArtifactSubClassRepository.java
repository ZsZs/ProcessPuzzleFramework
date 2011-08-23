/*
 * Created on May 4, 2006
 */
package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;
import com.processpuzzle.persistence.query.domain.ComparisonOperators;
import com.processpuzzle.persistence.query.domain.DefaultQuery;
import com.processpuzzle.persistence.query.domain.TextAttributeCondition;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactSubClassRepository extends GenericRepository<ArtifactSubClass> {

   public ArtifactSubClassRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext) {
      super(strategy, applicationContext);
   }

   public void addArtifactSubClass(ArtifactSubClass artifactSubClass) {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      add(work, ArtifactSubClass.class, artifactSubClass);
      work.finish();
   }

   public void deletedArtifactSubClass(ArtifactSubClass artifactSubClass) {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      delete(work, ArtifactSubClass.class, artifactSubClass);
      work.finish();
   }

   public ArtifactSubClass findArtifactSubClassByName(String name) {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      DefaultQuery query = new DefaultQuery(ArtifactSubClass.class);
      query.getQueryCondition().addAttributeCondition(new TextAttributeCondition("name", name, ComparisonOperators.EQUAL_TO));
      RepositoryResultSet<ArtifactSubClass> resultSet = findByQuery(work, query);
      work.finish();
      return resultSet.getEntityAt(0);
   }

   public void deleteArtifactSubClass(ArtifactSubClass a) {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      delete(work, ArtifactSubClass.class, a);
      work.finish();
   }

   protected Object findByIdentityExpression(String identityExpression) {
      // TODO Auto-generated method stub
      return null;
   }
}