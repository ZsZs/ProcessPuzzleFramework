/*
Name: 
    - CircleNumberConstraintViolationException

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

package com.processpuzzle.address.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class CircleNumberConstraintViolationException extends ProcessPuzzleProgrammingException {

   private static final long serialVersionUID = 1L;
   private static String[] descriptions = { "The circle number must be in positive." };
   private String circleNumber = "";
   private int descriptionIndex;
   private static String defaultMessagePattern = "The circle number must be in positive.";

   public CircleNumberConstraintViolationException(String circleNumber, int descriptionIndex) {
      super(ExceptionHelper.defineMessage(CircleNumberConstraintViolationException.class, new Object[] { circleNumber, descriptionIndex },
            defaultMessagePattern));
      this.circleNumber = circleNumber;
      this.descriptionIndex = descriptionIndex;
   }

   public static String[] getDescriptions() {
      return descriptions;
   }

   public static void setDescriptions(String[] descriptions) {
      CircleNumberConstraintViolationException.descriptions = descriptions;
   }

   public String getCircleNumber() {
      return circleNumber;
   }

   public void setCircleNumber(String circleNumber) {
      this.circleNumber = circleNumber;
   }

   public int getDescriptionIndex() {
      return descriptionIndex;
   }

   public void setDescriptionIndex(int descriptionIndex) {
      this.descriptionIndex = descriptionIndex;
   }

}
