package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public class CommentListRepository extends GenericArtifactRepository<CommentList> {

   public CommentListRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( DefaultUnitOfWork work, CommentList commentList ) {
      return super.add( work, commentList );
   }

   public CommentList findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public CommentList findByName( DefaultUnitOfWork work, String name ) {
      return super.findByName( work, name );
   }

   public RepositoryResultSet<CommentList> findByQuery( DefaultUnitOfWork work, DefaultQuery query ) {
      return super.findByQuery( work, query );
   }

   public void update( DefaultUnitOfWork work, CommentList commentList ) {
      update( work, CommentList.class, commentList );
   }
}
