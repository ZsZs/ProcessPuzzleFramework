/*
Name: 
    - Party_RelationshipView

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.artifact.PartyDataSheet;
import com.processpuzzle.party.artifact.PartyDataSheetRepository;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRelationshipFactory;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipConstraint;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class Party_RelationshipView extends CustomFormView<PartyDataSheet<?,?>> {
   private String partyDataSheetId;
   private String vRelationId;
   private String ownRoleId;
   private String partyRoleId;
   private String currenPartyRoleId;
   private String selectedPartyRoleId;
   private String errorMsg;
   private PartyRepository partyRepository;
   private PartyRelationshipTypeRepository partyRelationshipTypeRepository;
   private PartyRoleTypeRepository partyRoleTypeRepository;

   public Party_RelationshipView( PartyDataSheet<?,?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      partyRepository = applicationContext.getRepository( PartyRepository.class );
      partyRelationshipTypeRepository = applicationContext.getRepository( PartyRelationshipTypeRepository.class );
      partyRoleTypeRepository = applicationContext.getRepository( PartyRoleTypeRepository.class );
   }

   public Integer getId() { return parentArtifact.getId(); }

   public List<PartyRole> getPartyRoles() { return parentArtifact.getPartyRoles(); }

   public void setCurrentPartyRole( String id ) { this.currenPartyRoleId = id; }

   public PartyRole getOtherRole() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Party<?> ownParty = partyRepository.findPartyByPartyRoleId( work, currenPartyRoleId );
      PartyRole ownRole = Party.getRoleFromPartyById( ownParty, new Integer( currenPartyRoleId ) );
      Party<?> party = partyRepository.findOtherPartyByPartyRole( work, ownRole.getPartyRelationship().getId(), ownRole.getId() );
      work.finish();
      return Party.getRoleFromPartyById( party, new Integer( currenPartyRoleId ) );
   }

   
   public String getCurrentRelationParty() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Party<?> ownParty = partyRepository.findPartyByPartyRoleId( work, currenPartyRoleId );
      PartyRole ownRole = Party.getRoleFromPartyById( ownParty, new Integer( currenPartyRoleId ) );
      Party<?> party = partyRepository.findOtherPartyByPartyRole( work, ownRole.getPartyRelationship().getId(), ownRole.getId() );
      PartyRole partyRole = Party.getRoleFromPartyById( party, new Integer( currenPartyRoleId ) );
      work.finish();
      
      if( !ownRole.getParty().getName().equals( parentArtifact.getParty().getName() ))
         return ownRole.getParty().getPartyName().getName();
      else{
         return partyRole.getParty().getPartyName().getName();
      }
   }

   public List<PartyDataSheet<?,?>> getPartyDataSheets() {
      List<PartyDataSheet<?,?>> validPartyDataSheets = new ArrayList<PartyDataSheet<?,?>>();
      PartyDataSheetRepository partyDataSheetRepository = applicationContext.getRepository( PartyDataSheetRepository.class );
      RepositoryResultSet<PartyDataSheet<?,?>> partyDataSheets = partyDataSheetRepository.findAll();
      for( Iterator<PartyDataSheet<?,?>> iter = partyDataSheets.iterator(); iter.hasNext(); ){
         PartyDataSheet<?,?> pDataSheet = (PartyDataSheet<?,?>) iter.next();
         if( !pDataSheet.getId().equals( ((PartyDataSheet<?,?>) parentArtifact).getId() ) )
            validPartyDataSheets.add( pDataSheet );
      }
      return validPartyDataSheets;
   }

   public void setPartyDataSheet( String id ) { partyDataSheetId = id; }

   public void setVRelation( String id ) { vRelationId = id; }

   public void setOwnRole( String id ) { ownRoleId = id; }

   public void setPartyRole( String id ) { partyRoleId = id; }

   public void setPartyRoleId( String id ) { this.selectedPartyRoleId = id; }

   public void performAction() { 
      if( method.equals( "newRelationship" ) ){
         createPartyRelationship();
      }else if( method.equals( "delRelationship" ) ){
         removePartyRelationship();
      }
   }

   public void createPartyRelationship() {
      if( partyDataSheetId != null && vRelationId != null && ownRoleId != null && partyRoleId != null ){

         DefaultUnitOfWork work = new DefaultUnitOfWork( true );

         PartyRelationshipType partyRelationshipType = partyRelationshipTypeRepository.findById( work, vRelationId );
         Party<?> party = partyRepository.getPartyByPartyDataSheetId( work, new Integer( partyDataSheetId ) );

         PartyRoleType ownType = partyRoleTypeRepository.findPartyRoleTypeById( work, ownRoleId );
         PartyRoleType partyType = partyRoleTypeRepository.findPartyRoleTypeById( work, partyRoleId );

         for( Iterator<PartyRole> iter = parentArtifact.getParty().getRoles().iterator(); iter.hasNext(); ){
            PartyRole ownPartyRole = (PartyRole) iter.next();
            String relationshipId = ownPartyRole.getPartyRelationship().getId().toString();
            Party<?> otherParty = partyRepository.findOtherPartyByPartyRole( work, relationshipId, ownPartyRole.getId().toString() );
            PartyRole otherPartyRole = Party.getRoleFromPartyById( otherParty, ownPartyRole.getId() );
            if( otherPartyRole.getParty().getName().equals( party.getName() )
                  && ownPartyRole.getPartyRelationship().getRelationshipType().getName().equals( partyRelationshipType.getName() )
                  && ownPartyRole.getRoleType().getName().equals( ownType.getName() )
                  && otherPartyRole.getRoleType().getName().equals( partyType.getName() ) ){
               this.errorMsg = "A kapcsolat már létezik!";
               return;
            }
         }
         work.finish();
         
         if( ownType.getName().equals( partyRelationshipType.getSingleValidRolePair().getClientRoleType().getName() )
               && partyType.getName().equals( partyRelationshipType.getSingleValidRolePair().getSupplierRoleType().getName() ) )
            PartyRelationshipFactory.createPartyRelationship( partyRelationshipType, parentArtifact.getParty(), party );
         else{
            PartyRelationshipFactory.createPartyRelationship( partyRelationshipType, party, parentArtifact.getParty() );
         }
      }
   }

   public void removePartyRelationship() {
      PartyRelationshipFactory.removePartyRelationship( parentArtifact.getParty(), selectedPartyRoleId );
   }

   public void initializeView() {}

   public String getData( String method, Map<String, String> parameters ) {
      StringBuffer responseXml = new StringBuffer();
      String selectedValue = (String) parameters.get( "par0" );

      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( !method.equals( "" ) && !parameters.equals( "-1" ) ){
         if( method.equals( "getValidRelationships" ) ){
            Party<?> selectedParty = partyRepository.getPartyByPartyDataSheetId( work, new Integer( selectedValue ));
            List<PartyRelationshipType> validRelationships = 
               partyRelationshipTypeRepository.findPartyRelationshipTypeByValidTypeOfParty( work, parentArtifact.getParty(), selectedParty );

            responseXml.append( "<validRelationships>" );
            for( Iterator<PartyRelationshipType> iter = validRelationships.iterator(); iter.hasNext(); ){
               PartyRelationshipType type = (PartyRelationshipType) iter.next();
               String typeString = applicationContext.getText( "ui.partyRelationship.relationshipType." + type.getName(), getPreferredLanguage() );
               responseXml.append( "<validRelationship id=\"" + type.getId() + "\">" + typeString + "</validRelationship>" );
            }
            responseXml.append( "</validRelationships>" );
            return responseXml.toString();
         }else if( method.equals( "getValidRoles" ) ){
            Party<?> subjectParty = parentArtifact.getParty();
            Party<?> targetParty = partyRepository.getPartyByPartyDataSheetId( work, (new Integer( (String) parameters.get( "par1" ) )) );
            responseXml.append( "<validRoles>" );
            Set<PartyRelationshipConstraint> validRolePairs = ((PartyRelationshipType) partyRelationshipTypeRepository.findById( work, selectedValue )).getValidRolePairs();
            for( Iterator<PartyRelationshipConstraint> iter = validRolePairs.iterator(); iter.hasNext(); ){
               PartyRelationshipConstraint constraint = (PartyRelationshipConstraint) iter.next();
               PartyRoleType ownValidRoleType = null;
               PartyRoleType targetValidRoleType = null;

               if( constraint.getClientRoleType().canPlayRole( subjectParty.getType() ) ){
                  ownValidRoleType = constraint.getClientRoleType();
               }else if( constraint.getSupplierRoleType().canPlayRole( subjectParty.getType() ) ){
                  ownValidRoleType = constraint.getSupplierRoleType();
               }
               if( constraint.getClientRoleType().canPlayRole( targetParty.getType() ) ){
                  targetValidRoleType = constraint.getClientRoleType();
               }else if( constraint.getSupplierRoleType().canPlayRole( targetParty.getType() ) ){
                  targetValidRoleType = constraint.getSupplierRoleType();
               }
               String ownValidRoleTypeString = ProcessPuzzleContext.getInstance().getText(
                     "ui.partyRelationship.validRoleType." + ownValidRoleType.getName(), getPreferredLanguage() );
               String targetValidRoleTypeString = ProcessPuzzleContext.getInstance().getText(
                     "ui.partyRelationship.validRoleType." + targetValidRoleType.getName(), getPreferredLanguage() );

               responseXml.append( "<ownValidRole id=\"" + ownValidRoleType.getId() + "\">" + ownValidRoleTypeString + "</ownValidRole>" );
               responseXml.append( "<partyValidRole id=\"" + targetValidRoleType.getId() + "\">" + targetValidRoleTypeString
                     + "</partyValidRole>" );
            }
            responseXml.append( "</validRoles>" );
            return responseXml.toString();
         }
      }else{
         work.finish();
         return responseXml.append( "<failure></failure>" ).toString();
      }
      work.finish();
      return null;
   }

   public String getErrorMsg() {
      return errorMsg;
   }
}
