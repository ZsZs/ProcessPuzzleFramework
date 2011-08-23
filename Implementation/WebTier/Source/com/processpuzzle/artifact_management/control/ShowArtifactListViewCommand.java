package com.processpuzzle.artifact_management.control;

import java.security.InvalidParameterException;

import org.hibernate.exception.ConstraintViolationException;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.domain.ArtifactListView;

public class ShowArtifactListViewCommand extends ShowArtifactViewCommand {
   public static final String QUERY_PARAMETER_NAME = "query";
   public static final String START_PARAMETER_NAME = "start";
   public static final String COUNT_PARAMETER_NAME = "count";
   public static final String ORDER_BY = "orderBy";
   public static final String ORDER = "order";
   private String query = null;
   private Integer start = null;
   private Integer count = null;
   private String orderBy = null;
   private String order = null;
   private ArtifactList<?> artifactList;

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      runQuery();
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
      super.retrieveRequestParameters( dispatcher );
      query = dispatcher.getProperties().getProperty( QUERY_PARAMETER_NAME );
      String startParameter = dispatcher.getProperties().getProperty( START_PARAMETER_NAME );
      start = (startParameter == null) ? new Integer( 0 ) : new Integer( startParameter );

      String countParameter = dispatcher.getProperties().getProperty( COUNT_PARAMETER_NAME );
      count = (countParameter == null) ? new Integer( 25 ) : new Integer( countParameter );

      String orderByParameter = dispatcher.getProperties().getProperty( ORDER_BY );
      orderBy = orderByParameter;

      String orderParameter = dispatcher.getProperties().getProperty( ORDER );
      order = orderParameter;
   }

   protected void runQuery() {
      try{
         artifactList = (ArtifactList<?>) subjectArtifact;
      }catch( ClassCastException e ){
         throw new InvalidParameterException( "The given subjectArtifact is not an ArtifactList!" );
      }

      ArtifactListView<?> listView;
      try{
         listView = (ArtifactListView<?>) artifactList.getListView();
      }catch( ClassCastException e ){
         throw new InvalidParameterException( "The founded view is not an ArtifactListView!" );
      }

      try{
         listView.query( query, start, count );
      }catch( ConstraintViolationException e ){
         String problem = "You can't delete an artifact with existing relationships. Fist delete those artifact relationships!";
         throw new ListQueryException( problem, query, start, count, e );
      }catch( Throwable e ){
         throw new ListQueryException( query, start, count, e );
      }
   }

   public String getName() {
      return "ShowListView";
   }

   public String getQuery() {
      return query;
   }
}