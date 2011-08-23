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


