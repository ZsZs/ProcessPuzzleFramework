/*
Name: 
    - ApplicationInstantiationException

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

package com.processpuzzle.application.domain;

import java.text.MessageFormat;

public class ApplicationInstantiationException extends RuntimeException {
   private static final long serialVersionUID = -7123658907911484362L;
   private static final String message = "Application: ''{0}'' cannot be instantiated. Look at it\'s constructor. Be sure the application is not abstract. '{1}'";
   private Class<? extends Application> applicationClass;
   
   public ApplicationInstantiationException( Class<? extends Application> applicationClass, Throwable cause ) {
      this( applicationClass, "", cause );
   }

   public ApplicationInstantiationException( Class<? extends Application> applicationClass, String auxiliaryMessage, Throwable cause ) {
      super( MessageFormat.format( message, new Object[] {applicationClass.getName(), auxiliaryMessage} ), cause );
      this.applicationClass = applicationClass;
   }

   public Class<? extends Application> getApplicationClass() {
      return applicationClass;
   }
}
