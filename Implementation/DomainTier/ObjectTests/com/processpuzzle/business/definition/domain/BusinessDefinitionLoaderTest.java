/*
 * Created on Jul 16, 2006
 */
package com.processpuzzle.business.definition.domain;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.artifact_type.domain.ArtifactPropertyViewType;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.artifact_type.domain.ListQueryViewType;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.commons.persistence.Repository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;

/**
 * @author zsolt.zsuffa
 */
public class BusinessDefinitionLoaderTest extends BusinessDataLoaderTest<BusinessDefinitionLoader> {
   private static final String ARTIFACT_GROUP_NAME = "Business";
   private static PartyType personPartyType;
   private static PartyType companyPartyType;
   private static PartyType noneProfitOrganization;
   private static PartyRoleType fatherRoleType;
   private static PartyRoleType sonRoleType;
   private static PartyRelationshipType fatherSonRelationship;
   private static ArtifactTypeGroup businessArtifacts;
   private static ArtifactType personList;
   private static ArtifactType productCatalogDataSheet;
   private static ArtifactViewType propertyView;
   private static ListQueryViewType queryViewType;
   
   @BeforeClass public static void beforeAllTests() throws Exception {
      xPathKey = PropertyKeys.BUSINESS_DEFINITION.getDefaultKey();
      dataLoaderClass = BusinessDefinitionLoader.class;
      
      BusinessDataLoaderTest.beforeAllTests();
      
      PartyTypeRepository partyTypeRepository = retrieveRepository( PartyTypeRepository.class );
      PartyRoleTypeRepository partyRoleTypeRepository = retrieveRepository( PartyRoleTypeRepository.class );
      PartyRelationshipTypeRepository partyRelationshipTypeRepository = retrieveRepository( PartyRelationshipTypeRepository.class );
      ArtifactTypeGroupRepository groupRepository = retrieveRepository( ArtifactTypeGroupRepository.class );
      ArtifactTypeRepository typeRepository = retrieveRepository( ArtifactTypeRepository.class );

      personPartyType = partyTypeRepository.findByName( "PersonType" );
      companyPartyType = partyTypeRepository.findByName( "CompanyType" );
      noneProfitOrganization = partyTypeRepository.findByName( "NonProfitOrganization" );

      personList = typeRepository.findByName( "PersonList" );
      queryViewType = (ListQueryViewType) personList.findView( "PersonList_QueryView" );
      
      fatherRoleType = partyRoleTypeRepository.findByName( "FatherRole" );
      sonRoleType = partyRoleTypeRepository.findByName( "SonRole" );
      fatherSonRelationship = partyRelationshipTypeRepository.findByName( "FatherSonRelationship" );
      
      businessArtifacts = groupRepository.findByName( ARTIFACT_GROUP_NAME );
      productCatalogDataSheet = typeRepository.findByName( "ProductCatalogDataSheet" );
      propertyView = productCatalogDataSheet.findView( "PropertyView" );
   }
   
   @Test public void verifyLoadFromMultipeleSources() {
      assertThat( "'NonProfitOrganization' party type comes form a second BusinessDefinition.xml.", noneProfitOrganization, notNullValue() );      
   }

   @Test public void verifyPartyTypes() {
      assertThat( personPartyType, notNullValue() );
      assertThat( personPartyType.getName(), equalTo( "PersonType" ));
      assertThat( companyPartyType, notNullValue() );
      assertThat( companyPartyType.getName(), equalTo( "CompanyType" ));
   }
   
   @Test public void verifyPartRoleTypes() {
      assertThat( fatherRoleType.getName(), equalTo( "FatherRole" ));
      assertThat( fatherRoleType.canPlayRole( personPartyType ), is( true ));
      assertThat( sonRoleType.getName(), equalTo( "SonRole" ));
      assertThat( sonRoleType.canPlayRole( personPartyType ), is( true ));
   }
   
   @Test public void verifyPartyRelationshipTypes() {
      assertThat( fatherSonRelationship, notNullValue() );
      assertThat( fatherSonRelationship.getName(), equalTo( "FatherSonRelationship" ));
      assertThat( fatherSonRelationship.canFormRelationship( fatherRoleType, sonRoleType ), is( true ));
   }
   
   @Test public void verifyArtifactTypeGroup() {
      assertThat( "Loading artifact type groups makes them pertistent.", businessArtifacts, notNullValue() );
      assertThat( businessArtifacts.getName(), equalTo( ARTIFACT_GROUP_NAME ));
      assertThat( businessArtifacts.findAccessRightsFor( "XPTeamMember" ).canCreate(), is( true ));
      assertThat( businessArtifacts.findAccessRightsFor( "XPTeamMember" ).canDelete(), is( false ));
   }


    @Test public final void verifyArtifactType () {
       assertThat( "Loading artifact types makes them persistent.", productCatalogDataSheet, notNullValue() );
       assertThat( "'Data sheet's goup should be:", productCatalogDataSheet.getGroup().getName(), equalTo( businessArtifacts.getName() ));
       assertThat( productCatalogDataSheet.getName(), equalTo( "ProductCatalogDataSheet" ));
       assertThat( productCatalogDataSheet.getCaption(), equalTo( "Product Catalog Data Sheet" ));
       //assertThat( productCatalogDataSheet.getArtifactClassName(), equalTo( ProductCatalogDataSheet.class.getName() ));
       assertThat( productCatalogDataSheet.getBaseUri(), equalTo( "baseUri" ));
       assertThat( productCatalogDataSheet.isCreateOnStartup(), is( false ));
       assertThat( productCatalogDataSheet.isSingleton(), is( true ));
       assertThat( productCatalogDataSheet.isSystem(), is( false ));
       assertThat( productCatalogDataSheet.isVersionControlled(), is( false ));
       assertThat( productCatalogDataSheet.isPessimisticLock(), is( true ));
       assertThat( productCatalogDataSheet.isRefreshOnDocumentActivation(), is( true ));
       assertThat( productCatalogDataSheet.isRefreshOnViewActivation(), is( true ));
       
       assertThat( productCatalogDataSheet.findAccessRightsFor( "FatherRole" ).canRead(), is( true ));
       assertThat( productCatalogDataSheet.findAccessRightsFor( "FatherRole" ).canCreate(), is( false ));
       assertThat( productCatalogDataSheet.findAccessRightsFor( "FatherRole" ).canDelete(), is( false ));
       
       assertThat( productCatalogDataSheet.findProperty( "productCatalogName" ).getValue(), equalTo( "Catalog-2008" ));
       
       assertThat( productCatalogDataSheet.findMenu( "Activate" ).getCommand().getName(), equalTo( "ActivateProductCatalog" ));
       
       assertThat( productCatalogDataSheet.findView( "PropertyView" ).getName(), equalTo( "PropertyView" ));
    }
    
    @Test public final void testPropertyView () {
       assertThat( propertyView, instanceOf( ArtifactPropertyViewType.class ));
       assertThat( propertyView.getClientType(), equalTo( "NativeView" ));
       assertThat( propertyView.isDefault(), is( true ));
       assertThat( propertyView.isStatic(), is( false ));
       assertThat( propertyView.getPresentationUri(), equalTo( "uri" ));
       //assertThat( propertyView.getViewClassName(), equalTo( ProductCatalogDataSheet_PropertyView.class.getName() ));
       assertThat( propertyView.getViewCaption(), equalTo( "Property View" ));
       assertThat( propertyView.getViewAccessUrl(), equalTo(  "viewAccessUrl" ));
       assertThat( propertyView.findMenu( "Print" ).getCommand().getName(), equalTo( "PrintProductCatalogProperties" ));
    }
    
    @Test public final void testQueryViewType() {
       assertThat( queryViewType, instanceOf( ListQueryViewType.class ));
       assertThat( queryViewType.getName(), equalTo( "PersonList_QueryView"));
       assertThat( queryViewType.getClientType(), equalTo( "ListQueryView" ));
       assertThat( queryViewType.getTargetPropertyView(), equalTo( "PersonDataSheet_PropertyView" ));
       assertThat( queryViewType.getPreDefindedQueries().size(), greaterThan( 0 ) );
       assertThat( queryViewType.findPredefinedQuery( "NamesStartingWithB" ).getDescription(), equalTo( "Names starting with B" ));
       assertThat( queryViewType.findPredefinedQuery( "NamesStartingWithB" ).getPredefinedStatement(), equalTo("from PersonDataSheet o where o.person.name like 'B'" ));
    }
    
    private static <R extends Repository<?>> R retrieveRepository( Class<R> repositoryClass ) {
      return applicationContext.getRepository( repositoryClass );
    }   
}
