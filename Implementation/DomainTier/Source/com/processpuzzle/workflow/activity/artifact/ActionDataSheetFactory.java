package com.processpuzzle.workflow.activity.artifact;

import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.ActionFactory;
import com.processpuzzle.workflow.protocol.domain.Protocol;

public class ActionDataSheetFactory extends ArtifactFactory<ActionDataSheet<?>> {

   ActionDataSheetFactory() {
      super();
   }

   public ActionDataSheet<?> create( String actionName, String protocolName ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Protocol protocol = protocolRepository.findByName( work, protocolName );
      Action<?> action = ActionFactory.createSubAction( actionName, protocol );
      ActionDataSheet<?> sheet = new ActionDataSheet<ActionDataSheet<?>>( actionName, subjectArtifactType, creator, action );
      work.finish();
      return sheet;
   }
}