package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class PersonalTodoList extends ArtifactList<PersonalTodoList> {

   protected PersonalTodoList() {
      super();
   }

   public PersonalTodoList( String name, ArtifactType type, User creator ) {
      super( name, type, creator );
   }
}
