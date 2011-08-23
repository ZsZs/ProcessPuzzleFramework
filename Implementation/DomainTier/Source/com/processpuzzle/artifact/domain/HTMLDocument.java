package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.DocumentType;


public class HTMLDocument extends Document {

   protected HTMLDocument() {
      super();
   }

   public HTMLDocument(String name, DocumentType type, User responsible) {
	   super(name, type, responsible);
   }
}