package com.processpuzzle.artifact_type.domain;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.processpuzzle.litest.template.DomainObjectTestTemplate;
import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ArtifactTypeTest extends DomainObjectTestTemplate<ArtifactType, ArtifactTypeTestFixture>{

   //Constructor
   public ArtifactTypeTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH  );
   }

   //Test cases
   @Test
   public final void instantiation_requiresNameAndTypeGroupAndArtifactClassName() {
      assertThat( sut.getName(), equalTo( ArtifactTypeTestFixture.ARTIFACT_TYPE_NAME ));
      assertThat( sut.getGroup(), notNullValue() );
      assertThat( sut.getGroup().getName(), equalTo( ArtifactTypeTestFixture.TYPE_GROUP_NAME ));
      assertThat( sut.getArtifactClassName(), equalTo( ArtifactTypeTestFixture.ARTIFACT_CLASS ));
   }
   
   @Test
   public final void addViewType_maintainsCollectionOfViewTypes() {
      assertThat( sut.getViewType( ArtifactTypeTestFixture.PROPERTY_VIEW_TYPE_NAME ), instanceOf( ArtifactPropertyViewType.class ));
      assertThat( sut.getViewType( ArtifactTypeTestFixture.PRINT_VIEW_TYPE_NAME ), instanceOf( ArtifactPrintViewType.class ));
      assertThat( sut.getViewType( ArtifactTypeTestFixture.ACCESSRIGHTS_VIEW_TYPE_NAME ), instanceOf( ArtifactAccessRightsViewType.class ));
      assertThat( sut.getViewType( ArtifactTypeTestFixture.MODIFICATIONS_VIEW_TYPE_NAME ), instanceOf( ArtifactModificationsViewType.class ));
      assertThat( sut.getViewType( ArtifactTypeTestFixture.COMMENTS_VIEW_TYPE_NAME ), instanceOf( ArtifactCommentsViewType.class ));
   }
}