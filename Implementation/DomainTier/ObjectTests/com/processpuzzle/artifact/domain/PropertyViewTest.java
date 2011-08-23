/*
 * Created on Apr 13, 2006
 */
package com.processpuzzle.artifact.domain;

import static com.processpuzzle.artifact.domain.IsSameXml.isSameXml;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import hu.itkodex.litest.template.ArtifactTestTemplate;

import java.util.Date;

import org.junit.Test;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;
import com.processpuzzle.util.domain.GeneralService;

public class PropertyViewTest extends ArtifactTestTemplate<TestEntityDataSheet, CommonArtifactTestFixture> {
   private static final String ARTIFACT_VIEW_XSD_PATH = "classpath:com/processpuzzle/artifact/domain/PropertyView.xsd";
   private static final String CREATION_DATE_ELEMENT_NAME = "pp:propertyView/pp:creationDate";
   private static final String LAST_MODIFICATION_DATE_ELEMENT_NAME = "pp:propertyView/pp:lastModificationDate";
   private static final String EXPECTED_XML_PATH = "classpath:com/processpuzzle/artifact/domain/ExpectedPropertyView.xml";
   private PropertyView<TestEntityDataSheet> propertyView;

   public PropertyViewTest() {
      super( DomainTierTestConfiguration.FIXTURE_CONTAINER_DEFINITION_PATH );
   }

   @SuppressWarnings( "unchecked" )
   @Override
   public void beforeEachTest() {
      super.beforeEachTest();
      propertyView = (PropertyView<TestEntityDataSheet>) sut.getView( "PropertyView" );
   }

   @Test
   public void testGetArtifactName() {
      assertThat( "The property view makes available the artifact's name.", propertyView.getArtifactName(), equalTo( CommonArtifactTestFixture.ENTITY_NAME ) );
   }

   @Test
   public void testGetCreationDate() {
      String creationDate = GeneralService.dateToString( sut.getCreation() );
      assertThat( "The property view makes available the artifact's creation date:", propertyView.getCreationDate(), equalTo( creationDate ) );
   }

   @Test
   public void testGetLastModificationDate() {
      String modificationDate = GeneralService.dateToString( sut.getLastModification() );
      assertThat( propertyView.getLastModificationDate(), equalTo( modificationDate ));
   }

   public void testGetResponsibleName() {
      assertEquals( "The property view makes available the artifact's responsible name:", "Gipsz Jakab", propertyView.getResponsibleName() );
   }

   @Test
   public void testGetLastModifierName() {
      assertThat( propertyView.getLastModifierName(), equalTo( sut.getLastModifier().getUserName() ));
   }

   @Test
   public void testMarshalledXml() {
      assertThat( marshallView( propertyView, ARTIFACT_VIEW_XSD_PATH ), isSameXml( readExpectedDocument( EXPECTED_XML_PATH )));
   }
   
   @Override
   protected org.dom4j.Document postProcessExpectedDocument( org.dom4j.Document expectedXml ) {
      org.dom4j.Node creationDateElement = expectedXml.selectSingleNode( CREATION_DATE_ELEMENT_NAME );
      creationDateElement.setText( GeneralService.dateToString( new Date( System.currentTimeMillis() )));
      
      org.dom4j.Node lastModificationDateElement = expectedXml.selectSingleNode( LAST_MODIFICATION_DATE_ELEMENT_NAME );
      lastModificationDateElement.setText( GeneralService.dateToString( new Date( System.currentTimeMillis() )));
      
      return expectedXml;
   }
}