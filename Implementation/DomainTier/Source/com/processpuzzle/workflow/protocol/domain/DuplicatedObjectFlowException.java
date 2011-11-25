/*
Name: 
    - DuplicatedObjectFlowException 

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

/*
 * Created on Nov 30, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class DuplicatedObjectFlowException extends ProcessPuzzleException {
   /**
    * 
    */
   private static final long serialVersionUID = -7115116456117817657L;

   private static String messageBody = "There was a trial to create an object flow with the same parameters.";

   private static String defaultMessagePattern = "There was a trial to create an object flow with the same parameters.";

   private ProtocolCallAction source = null;

   private ProtocolCallAction target = null;

   private ArtifactInstance instance = null;

   DuplicatedObjectFlowException(ProtocolCallAction source, ProtocolCallAction target, ArtifactInstance instance) {
      super(ExceptionHelper.defineMessage(DuplicatedObjectFlowException.class, new Object[] { source, target, instance },
            defaultMessagePattern), null);
      this.source = source;
      this.target = target;
      this.instance = instance;
   }

   public static String getMessageBody() {
      return messageBody;
   }

   public ArtifactInstance getInstance() {
      return instance;
   }

   public ProtocolCallAction getSource() {
      return source;
   }

   public ProtocolCallAction getTarget() {
      return target;
   }
}
