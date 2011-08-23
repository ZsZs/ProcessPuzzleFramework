/*
 * Created on Oct 20, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.ArrayList;
import java.util.List;

import com.processpuzzle.artifact.domain.ListQueryView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.persistence.query.domain.QueryProperty;

/**
 * @author zsolt.zsuffa
 */
public class PlanList_ListQueryView extends ListQueryView<PlanList> {

   public PlanList_ListQueryView( PlanList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public List<QueryProperty> getQueryProperties() {
      List<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
      queryProperties.add( new QueryProperty( "o.action.actionName", "Megnevezés" ) );
      return queryProperties;
   }

   public String getTargetArtifact() {
      return "PlanDataSheet";
   }
}
