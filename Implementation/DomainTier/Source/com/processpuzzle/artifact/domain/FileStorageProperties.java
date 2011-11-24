/*
Name: 
    - FileStorageProperties

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

import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.util.domain.GeneralService;

public class FileStorageProperties<F extends FileStorage> extends PropertyView<F> {

   public FileStorageProperties( F artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
   }

   public String getSourceName() {
      return parentArtifact.getSourceName();
   }

   public String getSourcePath() {
      return parentArtifact.getSourcePath();
   }

   public String getContentType() {
      return parentArtifact.getContentType();
   }

   public String getOriginalFileName() {
      return parentArtifact.getOriginalFileName();
   }

   public String getSize() {
      return parentArtifact.getSize().toString();
   }

   public String getSource() {
      return parentArtifact.getSource();
   }

   public String getUploadDate() {
      return GeneralService.dateToString( parentArtifact.getUploadDate() );
   }
}
