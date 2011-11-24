/*
Name: 
    - ProcessPuzzleException

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

import java.text.MessageFormat;

public abstract class ProcessPuzzleException extends Exception {
   private static final long serialVersionUID = -1210758244388234902L;
   protected static String formatPattern = null;
   protected String resourceKey;
   protected String text;
   protected Throwable cause = null;

   public ProcessPuzzleException( Object[] arguments, Throwable cause ) {
      super( MessageFormat.format( formatPattern, arguments ), cause );
      this.cause = cause;
   }
   
   public ProcessPuzzleException( ExceptionHelper helper ) {
      this( helper, null );
   }
   
   public ProcessPuzzleException( ExceptionHelper helper, Throwable cause ) {
      super( MessageFormat.format( helper.getMessagePattern(), helper.getArguments() ), cause );
      this.text = MessageFormat.format( helper.getMessagePattern(), helper.getArguments() );
      this.cause = cause;
   }

   public String getText() { return text; }
   
   public String getStackTraceAsText() {
      String stackTraceText = "";
      for (int i = 0; i < cause.getStackTrace().length; i++) {
         stackTraceText += cause.getStackTrace()[i].toString();
      }
      return stackTraceText;
   }
}
