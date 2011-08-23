package com.processpuzzle.party.artifact;

import java.util.List;

import com.processpuzzle.artifact.domain.ListQueryView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonList_QueryView extends ListQueryView<PersonList> {

   public PersonList_QueryView( PersonList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override
   public List getQueryProperties() {
      return null;
   }

   @Override
   public String getTargetArtifact() {
      return null;
   }

}
