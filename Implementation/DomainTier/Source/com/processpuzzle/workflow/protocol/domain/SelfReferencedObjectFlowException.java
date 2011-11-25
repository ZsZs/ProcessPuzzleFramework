/*
Name: 
    - SelfReferencedObjectFlowException 

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
 * Created on Dec 1, 2006
 */
package com.processpuzzle.workflow.protocol.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class SelfReferencedObjectFlowException extends ProcessPuzzleException {

   private static final long serialVersionUID = 5918821684850131623L;

   private static String defaultMessagePattern = "There was a trial to add self referenced object flow.";

   private String parent = "";

   private String sourceStep = "";

   private String targetStep = "";

   public SelfReferencedObjectFlowException(String parent, String sourceStep, String targetStep) {
      super(ExceptionHelper.defineMessage(SelfReferencedObjectFlowException.class, new Object[] {parent, sourceStep, targetStep}, defaultMessagePattern), null);
      this.parent = parent;
      this.sourceStep = sourceStep;
      this.targetStep = targetStep;
   }

   public String getParent() {
      return parent;
   }

   public String getSourceStep() {
      return sourceStep;
   }

   public String getTargetStep() {
      return targetStep;
   }
}
