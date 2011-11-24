/*
Name: 
    - ProcessPuzzleParseException

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

package com.processpuzzle.fundamental_types.domain;


public class ProcessPuzzleParseException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -8017178918256385768L;

   public ProcessPuzzleParseException( String source, String formatExpression, Throwable cause) {
      super( defineMessage( source, formatExpression ), cause );
   }

   protected static Object[] defineMessage( String source, String formatExpression ) {
      formatPattern = "Parsing source: ''{0}'' with formatter: ''{1}'' caused error.";
      Object[] arguments = {source, formatExpression};
      return arguments;
   }
}
