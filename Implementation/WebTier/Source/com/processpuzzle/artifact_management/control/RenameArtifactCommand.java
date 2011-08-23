package com.processpuzzle.artifact_management.control;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class RenameArtifactCommand extends ArtifactActionCommand {

   public static final String COMMAND_NAME = "RenameArtifact";
   private static final String NEW_NAME_ATTRIB = "newArtifactName";
   protected Artifact<?> artifact;
   private String newArtifactName = null;

   public void init(CommandDispatcher dispatcher) {
      super.init(dispatcher);
   }

   public String getName() {
      return COMMAND_NAME;
   }

   protected void retrieveRequestParameters(CommandDispatcher dispatcher) {
      super.retrieveRequestParameters(dispatcher);
      newArtifactName = dispatcher.getRequest().getParameter(NEW_NAME_ATTRIB);
   }

   protected void doAction() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      String errorDescription = null;
      if (newArtifactName != null && !newArtifactName.equals("")) {
         if (subjectArtifact != null) {
            ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
            DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
            if (artifactRepository.findByFullName(work, newArtifactName, subjectArtifact.getContainingFolder()) == null) {
               subjectArtifact.renameName(newArtifactName);
               artifactRepository.update(work, subjectArtifact);
               actionResponse.setOutcome(true);
            } else {
               errorDescription = "<errorDescription>" + "newArtifactNameExist" + "</errorDescription>";
               actionResponse.addStringToBody(errorDescription);
               actionResponse.setOutcome(false);
            }
         } else {
            errorDescription = "<errorDescription>" + "missingArtifact" + "</errorDescription>";
            actionResponse.addStringToBody(errorDescription);
            actionResponse.setOutcome(false);
         }
      } else {
         errorDescription = "<errorDescription>" + "missingNewArtifactName" + "</errorDescription>";
         actionResponse.addStringToBody(errorDescription);
         actionResponse.setOutcome(false);
      }
      work.finish();
   }

}
