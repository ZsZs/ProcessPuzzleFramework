/*
Name: 
    - LocaleDefinitionNotFoundException

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

package com.processpuzzle.internalization.domain;

import java.text.MessageFormat;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class LocaleDefinitionNotFoundException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -131026769078489734L;
   private static String defaultMessagePattern = "Locale definition for the supported type: ''{0}'_'{1}'' not found!";
   private String language = null;
   private String country = null;

   public LocaleDefinitionNotFoundException( String language, String country ) {
      super( buildMessage( language, country ) );
      this.language = language;
      this.country = country;
   }

   // Properties
   public String getCountry() {
      return country;
   }

   public String getLanguage() {
      return language;
   }

   private static final String buildMessage(  String language, String country ) {
      return MessageFormat.format( defaultMessagePattern, new Object[] {language, country} );
   }
}
