/*
Name: 
    - FrameworkRepositories

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

package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.address.artifact.SettlementDataSheetRepository;
import com.processpuzzle.address.domain.CountryRepository;
import com.processpuzzle.address.domain.SettlementRepository;
import com.processpuzzle.address.domain.ZipCodeRepository;
import com.processpuzzle.application.domain.ApplicationEventRepository;
import com.processpuzzle.application.security.domain.UserRepository;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.CommentListRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.DocumentRepository;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.fundamental_types.uniqueidentifier.domain.LastIdNumberRepository;
import com.processpuzzle.party.artifact.CompanyDataSheetRepository;
import com.processpuzzle.party.artifact.PersonDataSheetRepository;
import com.processpuzzle.party.artifact.UserDataSheetRepository;
import com.processpuzzle.party.domain.CompanyRepository;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PersonRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.persistence.domain.RepositoryClassList;
import com.processpuzzle.resource.resourcetype.domain.ResourceTypeRepository;
import com.processpuzzle.util.domain.AutoIdentifierRepository;
import com.processpuzzle.util.domain.BusinessObjectRepository;
import com.processpuzzle.workflow.activity.artifact.PlanListRepository;
import com.processpuzzle.workflow.activity.domain.WorkflowRepository;
import com.processpuzzle.workflow.protocol.domain.ProtocolRepository;

public class FrameworkRepositories extends RepositoryClassList {

   public FrameworkRepositories() {
      super();
   }

   @Override
   protected void defineRepositories() {
      repositories.add( ApplicationEventRepository.class );
      repositories.add( ArtifactFolderRepository.class );
      repositories.add( ArtifactTypeGroupRepository.class );
      repositories.add( ArtifactTypeRepository.class );
      repositories.add( AutoIdentifierRepository.class );
      repositories.add( BusinessObjectRepository.class );
      repositories.add( CommentListRepository.class );
      repositories.add( CompanyDataSheetRepository.class );
      repositories.add( CompanyRepository.class );
      repositories.add( CountryRepository.class );
      repositories.add( DefaultArtifactRepository.class );
      repositories.add( DocumentRepository.class );
      repositories.add( LastIdNumberRepository.class );
      repositories.add( PartyRepository.class );
      repositories.add( PartyTypeRepository.class );
      repositories.add( PartyRoleTypeRepository.class );
      repositories.add( PartyRelationshipTypeRepository.class );
      repositories.add( PersonDataSheetRepository.class );
      repositories.add( PersonRepository.class );
      repositories.add( PlanListRepository.class );
      repositories.add( ProtocolRepository.class );
      repositories.add( ResourceTypeRepository.class );
      repositories.add( SettlementDataSheetRepository.class );
      repositories.add( SettlementRepository.class );
      repositories.add( UserDataSheetRepository.class );
      repositories.add( UserRepository.class );
      repositories.add( WorkflowRepository.class );
      repositories.add( ZipCodeRepository.class );
   }
}
