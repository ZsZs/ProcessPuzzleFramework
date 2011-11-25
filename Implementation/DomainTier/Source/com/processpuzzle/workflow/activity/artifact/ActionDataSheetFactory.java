/*
Name: 
    - ActionDataSheetFactory 

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