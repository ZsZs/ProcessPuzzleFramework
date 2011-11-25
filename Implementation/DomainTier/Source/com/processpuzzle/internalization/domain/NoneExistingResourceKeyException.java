/*
Name: 
    - NoneExistingResourceKeyException

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


public class NoneExistingResourceKeyException extends InternalizationException {
   private static final long serialVersionUID = 7380209350697785819L;
   protected static String defaultMessagePattern = "Resource bundle files: '''{0}'''.* doesn't contains value for key: '''{1}''' in locale: '''{2}'''";

   protected NoneExistingResourceKeyException( String key, String resourcePath, ProcessPuzzleLocale locale ) {
      //Note: In this case we can't use ExceptionHelper because it calls ResourceBundle which can throw
      // 'NoneExistingResourceKeyException' again. This can cause stack overflow!
      super( defineMessage( key, resourcePath, locale) );
   }

   protected NoneExistingResourceKeyException( String key ) {
      //Note: In this case we can't use ExceptionHelper because it calls ResourceBundle which can throw
      // 'NoneExistingResourceKeyException' again. This can cause stack overflow!
      super( defineMessage(key) );
   }

   protected static Object[] defineMessage( String key, String resourcePath, ProcessPuzzleLocale locale ) {
      formatPattern = "Resource bundle files: '''{0}'''.* doesn't contains value for key: '''{1}''' in locale: '''{2}'''";
      Object[] arguments = {key, resourcePath, locale.getLanguage() + ", " + locale.getCountry() + ", " + locale.getVariant() };
      return arguments;
   }

   protected static Object[] defineMessage( String key ) {
      formatPattern = "Resource bundle files doesn't contains value for key: '''{0}'''";
      Object[] arguments = {key};
      return arguments;
   }
}
