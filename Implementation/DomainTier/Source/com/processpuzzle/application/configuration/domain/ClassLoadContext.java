/*
Name: 
    - ClassLoadContext

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

package com.processpuzzle.application.configuration.domain;

// ----------------------------------------------------------------------------
/**
 * Information context for {@link IClassLoadStrategy#getClassLoader(ClassLoadContext)}.
 * 
 * @author (C) <a href="http://www.javaworld.com/columns/jw-qna-index.shtml">Vlad Roubtsov</a>, 2003
 */
public class ClassLoadContext {
   // public: ................................................................

   /**
    * Returns the class representing the caller of {@link ClassLoaderResolver} API. Can be used to retrieve the caller's
    * classloader etc (which may be different from the ClassLoaderResolver's own classloader).
    */
   public final Class<?> getCallerClass() {
      return m_caller;
   }

   // protected: .............................................................

   // package: ...............................................................

   /**
    * This constructor is package-private to restrict instantiation to {@link ClassLoaderResolver} only.
    */
   ClassLoadContext( final Class<?> caller ) {
      m_caller = caller;
   }

   // private: ...............................................................

   private final Class<?> m_caller;

}
