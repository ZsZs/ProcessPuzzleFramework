/*
Name: 
    - ExceptionHelper

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

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.ResourceNotFoundException;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ExceptionHelper {
   private Object[] arguments = null;
   private String messagePattern = null;
   private Class<?> exceptionClass = null;

   private ExceptionHelper ( Class<?> exceptionClass, Object[] arguments, String messagePattern ) {
      this.exceptionClass = exceptionClass;
      this.arguments = arguments;
      this.messagePattern = messagePattern;
   }

   public static ExceptionHelper defineMessage(  Class<?> exceptionClass, Object[] arguments, String defaultMessagePattern ) {
      String formatPattern = retrieveMessagePattern( exceptionClass, defaultMessagePattern);
      return new ExceptionHelper( exceptionClass, arguments, formatPattern );
   }

   private static String retrieveMessagePattern( Class<?> exceptionClass, String defaultMessagePattern ) {
      ProcessPuzzleContext context = null;
      String pattern = null;
      
      UserRequestManager requestManager = UserRequestManager.getInstance();
      if( requestManager != null ) context = requestManager.getApplicationContext();         
      
      if( context != null && context.isConfigured() )
         pattern = context.getText( exceptionClass.getName() );

      if( pattern == null || pattern.equalsIgnoreCase( ResourceNotFoundException.class.getName() ))
         return defaultMessagePattern;
      else return pattern;
   }

   public Object[] getArguments() { return arguments;}
   public Class<?> getExceptionClass() { return exceptionClass; }
   public String getMessagePattern() { return messagePattern; }
}
