/*
Name: 
    - PrintView

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

/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public abstract class PrintView<A extends Artifact<?>> extends ArtifactView<A> {
   protected String xmlContent;
   protected String characterEntitiesDtd;

   public PrintView( A artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {
      // buildXml();
      convertXmlToFo();
      convertFoToPdf();
   }

   public String getCharacterEntitiesDtd() {
      return characterEntitiesDtd;
   }

   public void setCharacterEntitiesDtd( String characterEntitiesDtd ) {
      this.characterEntitiesDtd = characterEntitiesDtd;
   }

   public abstract String buildXml();

   protected void convertXmlToFo() {}

   protected void convertFoToPdf() {}
}