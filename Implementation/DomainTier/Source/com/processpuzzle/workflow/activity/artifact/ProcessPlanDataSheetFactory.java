package com.processpuzzle.workflow.activity.artifact;

import java.util.Iterator;

import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.party.domain.OrganizationName;
import com.processpuzzle.party.domain.Project;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.ProcessPlan;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;
import com.processpuzzle.workflow.protocol.domain.LifecyclePhaseProtocol;
import com.processpuzzle.workflow.protocol.domain.LifecycleProtocol;
import com.processpuzzle.workflow.protocol.domain.WorkflowDetailProtocol;

public class ProcessPlanDataSheetFactory extends ArtifactFactory<ProcessPlanDataSheet> {
   protected ActionDataSheetFactory actionDataSheetFactory;
   protected PlanDataSheetFactory planDataSheetFactory;

   ProcessPlanDataSheetFactory() {
      super();
      actionDataSheetFactory = applicationContext.getEntityFactory( ActionDataSheetFactory.class );      
      planDataSheetFactory = applicationContext.getEntityFactory( PlanDataSheetFactory.class );      
   }
   
   public ProcessPlanDataSheet createProcessByProtocol( String processId, LifecycleProtocol lifecycleProtocol ) {
      ProcessPlan processPlan = new ProcessPlan( processId, lifecycleProtocol );
      PartyType projectType = findPartyTypeFor( Project.class );
      Project project = new Project( new OrganizationName( lifecycleProtocol.getName() ), projectType );
      processPlan.setProject( project );
      ArtifactType processPlanType = findTypeFor( ProcessPlanDataSheet.class );
      ProcessPlanDataSheet processPlanDataSheet = new ProcessPlanDataSheet( processId, processPlanType, processPlan, creator );
      
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      for( Iterator<LifecyclePhaseProtocol> iter = lifecycleProtocol.getLifecyclePhases().iterator(); iter.hasNext(); ){
         LifecyclePhaseProtocol lifecyclePhaseProtocol = iter.next();
         PlanDataSheet planDataSheet = planDataSheetFactory.create( creator, processId, lifecyclePhaseProtocol.getName() );

         artifactRepository.add( work, planDataSheet );
         processPlanDataSheet.getPlan().addSubAction( planDataSheet.getPlan() );
         for( Iterator<WorkflowDetailProtocol> iterator1 = lifecyclePhaseProtocol.getWorkFlowDetails().iterator(); iterator1.hasNext(); ){
            WorkflowDetailProtocol workflowDetailProtocol = iterator1.next();
            PlanDataSheet pDataSheet2 = planDataSheetFactory.create( creator, processId, workflowDetailProtocol.getName() );
            artifactRepository.add( work, pDataSheet2 );
            processPlanDataSheet.getPlan().addSubAction( pDataSheet2.getPlan() );
            for( ActivityProtocol activityProtocol : workflowDetailProtocol.getActivities() ){
               ActionDataSheet<?> actionDataSheet = actionDataSheetFactory.create( processId, activityProtocol.getName() );
               artifactRepository.add( work, actionDataSheet );
               pDataSheet2.getPlan().addSubAction( actionDataSheet.getAction() );
            }

         }
         for( ActivityProtocol activityProtocol : lifecyclePhaseProtocol.getActivities() ){
            ActionDataSheet<?> actionDataSheet = actionDataSheetFactory.create( processId, activityProtocol.getName() );
            artifactRepository.add( work, actionDataSheet );
            planDataSheet.getPlan().addSubAction( actionDataSheet.getAction() );
         }

      }
      work.finish();
      return processPlanDataSheet;
   }   

   public ProcessPlanDataSheet create( String pplanName, String pplanSubject, String lifecycleName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      
      LifecycleProtocol lifecycleProtocol = (LifecycleProtocol) protocolRepository.findByName( work, lifecycleName );
      ProcessPlanDataSheet processPlanDataSheet = createProcessByProtocol( pplanName, lifecycleProtocol );
      processPlanDataSheet.getProcessPlan().setSubject( pplanSubject );
      
      work.finish();
      return processPlanDataSheet;
   }

   public ProcessPlanDataSheet create( String processPlanName, String lifecycleName, boolean adhoc ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      
      LifecycleProtocol lifecycleProtocol = protocolRepository.findLifecycleByName( work, lifecycleName );
      ProcessPlan pPlan = new ProcessPlan( processPlanName, lifecycleProtocol );
      PartyType projectType = findPartyTypeFor( Project.class );
      Project project = new Project( new OrganizationName( lifecycleProtocol.getName()), projectType );
      pPlan.setProject( project );
      ArtifactType processPlanDataSheetType = findTypeFor( ProcessPlanDataSheet.class );
      ProcessPlanDataSheet processPlanDataSheet = new ProcessPlanDataSheet( processPlanName, processPlanDataSheetType, pPlan, creator );
      
      work.finish();
      return processPlanDataSheet;
   }   
}
