/*
Name: 
    - XmlDataLoaderException

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

package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class XmlDataLoaderException extends DataLoaderException {
   private static final long serialVersionUID = 1L;
   private static String defaultMessagePattern = "Problem ''{0}'' occured during loading data from resource: ''{1}''";
   private XmlDataLoader dataLoader;
   private String problem;
   
   public XmlDataLoaderException( XmlDataLoader loader, String problem ) {
      this(loader, problem, null );
   }

   public XmlDataLoaderException( XmlDataLoader loader, String problem, Throwable cause) {
      super( ExceptionHelper.defineMessage( XmlDataLoaderException.class, 
                                            new Object[] { problem, loader.getResourcePath() }, 
                                            defaultMessagePattern ),
             cause );
      this.dataLoader = loader;
      this.problem = problem;
   }

   public XmlDataLoaderException(Object[] objects, Throwable cause) {
      super(objects, cause);
   }

   public XmlDataLoaderException(ExceptionHelper helper, Throwable cause) {
     super(helper, cause);
   }
   
   public XmlDataLoader getDataLoader() { return dataLoader; }
   public String getProblem() { return problem; }
}
