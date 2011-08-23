package com.processpuzzle.artifact.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.processpuzzle.business.definition.domain.SystemArtifactsLoaderTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   AccessRightsViewTest.class,
   ArtifactFolderFactoryTest.class,
   ArtifactFolderTest.class,
   ArtifactListViewTest.class,
   ArtifactModificationsViewTest.class,
   ArtifactRepositoryTest.class,
   ArtifactTest.class,
   ArtifactVersionTest.class,
   ArtifactViewTest.class,
   CommentListTest.class,
   CustomFormViewTest.class,
   ListQueryViewTest.class,
   PrintViewTest.class,
   PropertyViewTest.class,
   RelatedArtifactListViewTest.class,
   SystemArtifactsLoaderTest.class
})

public class ArtifactTestSuite {}