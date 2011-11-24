/*
Name: 
    - NoneExistingArtifactTypeException

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

package com.processpuzzle.artifact.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class NoneExistingArtifactTypeException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -7660652032256788739L;
   private static String defaultMessagePattern = "Can't find ArtifactType: '''{0}''' for instantiating Artifact: '''{1}'''.";
   private String artifactTypeName;
   private String artifactName;
   
   public NoneExistingArtifactTypeException( String artifactTypeName, String artifactName ) {
      super( ExceptionHelper.defineMessage( 
                                            NoneExistingArtifactTypeException.class, 
                                            new Object[] { artifactTypeName, artifactName}, 
                                            defaultMessagePattern ));
      this.artifactTypeName = artifactTypeName;
      this.artifactName = artifactName;
   }

   public String getArtifactTypeName() {
      return artifactTypeName;
   }

   public String getArtifactName() {
      return artifactName;
   }

}
