/*
Name: 
    - CrossParentControlFlowException 

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
 * Created on Nov 29, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class CrossParentControlFlowException extends ProcessPuzzleProgrammingException {

   private static final long serialVersionUID = 1920644240245977045L;

   private static String defaultMessagePattern = "A control flow can't bind two steps in different parents.";

   private String sourceParent = "";

   private String targetParent = "";

   public CrossParentControlFlowException(String sourceParent, String targetParent) {
      super(ExceptionHelper.defineMessage(CrossParentControlFlowException.class, new Object[] {sourceParent, targetParent}, defaultMessagePattern));
      this.sourceParent = sourceParent;
      this.targetParent = targetParent;
   }

   public String getSourceParent() {
      return sourceParent;
   }

   public String getTargetParent() {
      return targetParent;
   }
}
