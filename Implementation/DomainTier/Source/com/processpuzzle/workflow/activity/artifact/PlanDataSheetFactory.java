/*
Name: 
    - PlanDataSheetFactory 

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

package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.Plan;
import com.processpuzzle.workflow.protocol.domain.CompositeProtocol;
import com.processpuzzle.workflow.protocol.domain.ProtocolRepository;

public class PlanDataSheetFactory extends ArtifactFactory<PlanDataSheet> {

   public PlanDataSheet create( User creator, String planName, String protocolName) {
      ArtifactTypeRepository aTypeRep = (ArtifactTypeRepository) ProcessPuzzleContext.getInstance().getRepository(ArtifactTypeRepository.class);
      ProtocolRepository protocolRep = (ProtocolRepository) ProcessPuzzleContext.getInstance().getRepository(ProtocolRepository.class);
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      ArtifactType type = aTypeRep.findArtifactTypeByName(work,"PlanDataSheet");  
      PlanDataSheet planDataSheet = new PlanDataSheet(planName, type, new Plan(planName, (CompositeProtocol)protocolRep.findByName(work,protocolName)), creator);
      work.finish();
      return planDataSheet;
   }
}


