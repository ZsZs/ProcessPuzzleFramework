/*
 * Created on Nov 21, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import hu.itkodex.commons.persistence.RepositoryResultSet;
import hu.itkodex.commons.persistence.UnitOfWork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.Activity;

/**
 * @author zsolt.zsuffa
 */
public class PlanDataSheet_ActionList extends ArtifactListView<PlanDataSheet> {

   public PlanDataSheet_ActionList( PlanDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {}

   public List<PropertyView<ActionDataSheet<?>>> getActionDataSheetsPropertyViews() {
      return getActivities();
   }

   @SuppressWarnings("unchecked")
   private List<PropertyView<ActionDataSheet<?>>> getActivities() {
      List<PropertyView<ActionDataSheet<?>>> propertyViews = new ArrayList<PropertyView<ActionDataSheet<?>>>();
      Set<Action<?>> activities = (Set<Action<?>>) parentArtifact.getPlan().findAllLeafActions();
      DefaultArtifactRepository artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );

      for( Iterator<Action<?>> iter = activities.iterator(); iter.hasNext(); ){
         Activity activity = (Activity) iter.next();
         UnitOfWork work = new DefaultUnitOfWork( true );
         RepositoryResultSet<Artifact> dataSheets = artifactRepository.findDataSheetsByActionId( work, activity.getId() );
         work.finish();
         if( dataSheets.size() == 1 ){
            ActionDataSheet dataSheet = (ActionDataSheet) dataSheets.iterator().next();
            propertyViews.add( dataSheet.getPropertyView() );
         }else
            throw new RuntimeException();
      }

      return propertyViews;
   }
}
