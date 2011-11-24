/*
Name: 
    - ZipCodeConstraintViolationException

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

public class ZipCodeConstraintViolationException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 1L;
   private static String[] descriptions = { "The zip code must be in positive and less than 10000." };
   private String zipCode = "";
   private int descriptionIndex;
   private static String defaultMessagePattern = "The zip code must be in positive and less than 10000.";

   public ZipCodeConstraintViolationException(int descriptionIndex, String zipCode) {
      super( ExceptionHelper.defineMessage(ZipCodeConstraintViolationException.class, new Object[] {descriptionIndex, zipCode}, defaultMessagePattern));
      this.descriptionIndex = descriptionIndex;
      this.zipCode = zipCode;
   }

   public static String[] getDescriptions() {
      return descriptions;
   }

   public static void setDescriptions(String[] descriptions) {
      ZipCodeConstraintViolationException.descriptions = descriptions;
   }

   public int getDescriptionIndex() {
      return descriptionIndex;
   }

   public void setDescriptionIndex(int descriptionIndex) {
      this.descriptionIndex = descriptionIndex;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getDescription() {
      return descriptions[descriptionIndex];
   }
}
