/*
Name: 
    - HibernatePersistenceProviderPropertyKey 

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

package com.processpuzzle.persistence.domain;

import java.text.MessageFormat;

public enum HibernatePersistenceProviderPropertyKey {
   DRIVER_CLASS( "pr:hibernate.pr:connection.pr:driver_class", "hibernate/connection/driver_class" ),
   CONNECTION_URL( "pr:hibernate.pr:connection.pr:url", "hibernate/connection/url" ),
   USER_NAME( "pr:hibernate.pr:connection.ge:username", "hibernate/connection/username" ),
   PASSWORD( "pr:hibernate.pr:connection.ge:password", "hibernate/connection/password" );

   HibernatePersistenceProviderPropertyKey( String defaultKey, String xPathKey ) { 
      this.defaultKey = defaultKey; 
      this.xPathKey = xPathKey; 
   }
   
   public static String createKey( String keyFormat, Object[] parameters ) {
      MessageFormat selector = new MessageFormat( keyFormat );      
      return selector.format( parameters );
   }
   
   public String getDefaultKey() { return this.defaultKey; }
   public String getXPathKey() { return this.xPathKey; }

   private String xPathKey;
   private String defaultKey;
}
