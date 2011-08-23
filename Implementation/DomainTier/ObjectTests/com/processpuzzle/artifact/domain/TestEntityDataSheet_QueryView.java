/*
 * Created on Jul 20, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.Date;
import java.util.List;

import com.processpuzzle.artifact.domain.ListQueryView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class TestEntityDataSheet_QueryView extends ListQueryView<TestEntityList> {

   public TestEntityDataSheet_QueryView( TestEntityList artifact, String name, ArtifactViewType type) {
      super(artifact, name, type);
   }

   public String getTextAttribute() {
      return parentArtifact.getTestEntity().getTextAttribute();
   }

   public Integer getNumberAttribute() {
      return parentArtifact.getTestEntity().getNumberAttribute();
   }

   public Date getDateAttribute() {
      return parentArtifact.getTestEntity().getDateAttribute();
   }

   public List<?> getQueryProperties() {
      // TODO Auto-generated method stub
      return null;
   }

   public String getTargetArtifact() { return "TestEntityDataSheet"; }
}
