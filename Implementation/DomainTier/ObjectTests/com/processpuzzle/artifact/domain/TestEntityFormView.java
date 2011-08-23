/*
 * Created on Jul 12, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.Date;

import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

/**
 * @author zsolt.zsuffa
 */
public class TestEntityFormView extends CustomFormView<TestEntityDataSheet> {
   private TestEntity testEntity = null;

   public TestEntityFormView( TestEntityDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      this.testEntity = ((TestEntityDataSheet) parentArtifact).getTestEntity();
   }

   public String getName() {
      return testEntity.getName();
   }

   public String getTextAttribute() {
      return testEntity.getTextAttribute();
   }

   public void setTextAttribute( String textValue ) {
      testEntity.setTextAttribute( textValue );
   }

   public Integer getNumberAttribute() {
      return testEntity.getNumberAttribute();
   }

   public void setNumberAttribute( Integer numberValue ) {
      testEntity.setNumberAttribute( numberValue );
   }

   public Date getDateAttribute() {
      return testEntity.getDateAttribute();
   }

   public void setDateAttribute( Date dateValue ) {
      testEntity.setDateAttribute( dateValue );
   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub

   }
}
