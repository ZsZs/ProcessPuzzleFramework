package com.processpuzzle.application.configuration.control;

import com.processpuzzle.application.administration.control.DatabaseAdministrationCommand;
import com.processpuzzle.application.administration.control.ShowDatabaseAdminCommand;
import com.processpuzzle.application.administration.control.ShowUndoMaintenanceCommand;
import com.processpuzzle.application.control.control.BuildXmlCommand;
import com.processpuzzle.application.control.control.UnknownFrontCommand;
import com.processpuzzle.application.security.control.LoginCommand;
import com.processpuzzle.artifact_management.control.AddRelatedArtifactCommand;
import com.processpuzzle.artifact_management.control.CreateArtifactCommand;
import com.processpuzzle.artifact_management.control.DeleteArtifactCommand;
import com.processpuzzle.artifact_management.control.NewArtifactCommand;
import com.processpuzzle.artifact_management.control.ReleaseArtifactCommand;
import com.processpuzzle.artifact_management.control.RemoveRelatedArtifactCommand;
import com.processpuzzle.artifact_management.control.ReserveArtifactCommand;
import com.processpuzzle.artifact_management.control.RetrieveArtifactDataCommand;
import com.processpuzzle.artifact_management.control.RetrieveArtifactFolderStructure;
import com.processpuzzle.artifact_management.control.RetrieveArtifactViewDataCommand;
import com.processpuzzle.artifact_management.control.SaveArtifactCommand;
import com.processpuzzle.artifact_management.control.SaveCommentCommand;
import com.processpuzzle.artifact_management.control.SaveHTMLTextCommand;
import com.processpuzzle.artifact_management.control.ShowArtifactCommand;
import com.processpuzzle.artifact_management.control.ShowArtifactListViewCommand;
import com.processpuzzle.artifact_management.control.ShowArtifactPrintViewCommand;
import com.processpuzzle.artifact_management.control.ShowArtifactViewCommand;
import com.processpuzzle.artifact_management.control.ShowDocumentNotesCommand;
import com.processpuzzle.artifact_management.control.ShowMessageWallCommand;
import com.processpuzzle.artifact_management.control.UpdateArtifactViewCommand;



public class ProcessPuzzleCommandMapping extends CommandMapping {

   private Object[] commands = new Object[] {
	  "UnknownFrontCommand", UnknownFrontCommand.class,
      "Show", ShowArtifactCommand.class,
      "Save", SaveArtifactCommand.class,
      DeleteArtifactCommand.COMMAND_NAME, DeleteArtifactCommand.class,
      "New", NewArtifactCommand.class,
      "ShowView", ShowArtifactViewCommand.class,
      "ShowListView", ShowArtifactListViewCommand.class,
      "SaveHTMLText", SaveHTMLTextCommand.class,
      "ShowPrintView", ShowArtifactPrintViewCommand.class,
      "SaveComment", SaveCommentCommand.class,
      "ShowDocumentNotes", ShowDocumentNotesCommand.class,
      //"ShowArtifactListViewMock", ShowArtifactListViewMockCommand.class,
      //"ShowArtifactViewMock", ShowArtifactViewMockCommand.class,
      //"ShowToDoList", ShowToDoListCommand.class,
      "ShowMessageWall", ShowMessageWallCommand.class,
      "BuildXml", BuildXmlCommand.class,
      ShowDatabaseAdminCommand.COMMAND_NAME, ShowDatabaseAdminCommand.class,
      ReserveArtifactCommand.COMMAND_NAME, ReserveArtifactCommand.class,
      ReleaseArtifactCommand.COMMAND_NAME, ReleaseArtifactCommand.class,   
      AddRelatedArtifactCommand.ADD_RELATED_ARTIFACT_COMMAND_NAME, AddRelatedArtifactCommand.class,
      RemoveRelatedArtifactCommand.REMOVE_RELATED_ARTIFACT_COMMAND_NAME, RemoveRelatedArtifactCommand.class,
      "RetrieveArtifactFolderStructure", RetrieveArtifactFolderStructure.class,
      ShowUndoMaintenanceCommand.COMMAND_NAME, ShowUndoMaintenanceCommand.class,
      DatabaseAdministrationCommand.COMMAND_NAME, DatabaseAdministrationCommand.class,
      "Login", LoginCommand.class,
      RetrieveArtifactDataCommand.COMMAND_NAME, RetrieveArtifactDataCommand.class,
      RetrieveArtifactViewDataCommand.COMMAND_NAME, RetrieveArtifactViewDataCommand.class,
      UpdateArtifactViewCommand.COMMAND_NAME, UpdateArtifactViewCommand.class,
      CreateArtifactCommand.COMMAND_NAME, CreateArtifactCommand.class,
      ApplicationInstallerCommand.COMMAND_NAME, ApplicationInstallerCommand.class
   };

   public ProcessPuzzleCommandMapping() {
      this.appendMappings(commands);
   }
}