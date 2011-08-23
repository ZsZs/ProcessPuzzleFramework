package com.processpuzzle.artifact_management.control;

import java.security.InvalidParameterException;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.EditableHTMLDocument;

public class ShowEditableHTMLDocumentCommand extends ShowArtifactCommand {

   private EditableHTMLDocument editableHTMLDocument;

   public void init(CommandDispatcher dispatcher) {
      super.init(dispatcher);

      boolean ccerror=false;
      try {editableHTMLDocument = (EditableHTMLDocument) getArtifact();} 
      catch (ClassCastException e) {ccerror=true;}
      if (ccerror || editableHTMLDocument==null) throw new InvalidParameterException("ShowEditableHTMLDocument: Wrong ID!");
   }

   public EditableHTMLDocument getEditableHTMLDocument() {
      return editableHTMLDocument;
   }

   public String getName() {
      return "ShowEditableHTMLDocument";
   }

   public String execute(CommandDispatcher dispatcher) throws Exception {
      return "";
   }
}