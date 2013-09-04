/*
Name: 
    - OrganizationUnitList_ListView

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

package com.processpuzzle.party.artifact;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.util.domain.BusinessObject;
import com.processpuzzle.util.domain.BusinessObjectRepository;

public class OrganizationUnitList_ListView extends ArtifactListView<OrganizationUnitList> {

   public OrganizationUnitList_ListView( OrganizationUnitList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void delete( String id ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(
            DefaultArtifactRepository.class );
      BusinessObjectRepository businessObjectRepository = (BusinessObjectRepository) ProcessPuzzleContext.getInstance().getRepository(
            BusinessObjectRepository.class );
      PartyRepository partyRepository = (PartyRepository) ProcessPuzzleContext.getInstance().getRepository( PartyRepository.class );
      List<PartyRole> toDelownRoles = new ArrayList<PartyRole>();
      Artifact<?> a = artifactRepository.findById( work, new Integer( id ) );
      OrganizationUnitDataSheet cDataSheet = (OrganizationUnitDataSheet) a;
      for( Iterator<?> iter = cDataSheet.getOrganizationUnit().getRoles().iterator(); iter.hasNext(); ){
         PartyRole ownRole = (PartyRole) iter.next();
         Party<?> party = partyRepository.findOtherPartyByPartyRole( work, ownRole.getPartyRelationship().getId(), ownRole.getId() );
         PartyRole partyRole = Party.getRoleFromPartyById( party, ownRole.getId() );
         ownRole.getPartyRelationship();
         try{
            toDelownRoles.add( ownRole );
            partyRole.getParty().removePartyRole( partyRole );
         }catch( Exception e ){
            e.printStackTrace();
         }

         partyRepository.updateParty( work, party );
         partyRepository.updateParty( work, ownRole.getParty() );
         // partyRepository.updatePartyRole(work,ownRole);
         // partyRepository.updatePartyRole(work,partyRole);
         // partyRepository.deletePartyRole(work,ownRole);
         // partyRepository.deletePartyRole(work,partyRole);
         //
         // partyRepository.deletePartyRelationship(work,pr);
         ownRole.setPartyRelationship( null );
         partyRole.setPartyRelationship( null );

      }

      for( Iterator<PartyRole> iter = toDelownRoles.iterator(); iter.hasNext(); ){
         PartyRole pRole = (PartyRole) iter.next();
         cDataSheet.getOrganizationUnit().getRoles().remove( pRole );
      }

      RepositoryResultSet<BusinessObject> bos = businessObjectRepository.findBusinessObjectsByCommissionerOrganizationUnitId( work, cDataSheet
            .getOrganizationUnit().getId().toString() );
      for( Iterator<BusinessObject> iter = bos.iterator(); iter.hasNext(); ){
         BusinessObject bo = (BusinessObject) iter.next();
         bo.setCommissionerOrganizationUnit( null );
         businessObjectRepository.updateBusinessObject( work, bo );
      }

      artifactRepository.delete( work, a );
      work.finish();
   }

   public void initializeView() {
   // TODO Auto-generated method stub
   }
}
