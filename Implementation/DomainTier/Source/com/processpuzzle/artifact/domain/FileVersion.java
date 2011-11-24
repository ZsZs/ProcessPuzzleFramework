/*
Name: 
    - FileVersion

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

import java.util.Date;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class FileVersion extends ArtifactVersion {
   private String originalFileName;
   private String source;
   private String contentType;
   private Date uploadDate;
   private Long size;

   public FileVersion() {
      super();
   }

   public FileVersion( String name, User responsible, ArtifactType type ) {
      super( name, responsible );
   }

   public FileVersion( String name, User responsible, Date creationDate ) {
      super( name, responsible, creationDate );
   }

   public FileVersion( String name, User responsible ) {
      super( name, responsible );
   }

   public String getOriginalFileName() {
      return originalFileName;
   }

   public void setOriginalFileName( String originalFileName ) {
      this.originalFileName = originalFileName;
   }

   public String getSource() {
      return source;
   }

   public void setSource( String source ) {
      this.source = source;
   }

   public String getSourceName() {
      return (new java.io.File( source )).getName();
   }

   public String getSourcePath() {
      return (new java.io.File( source )).getParent();
   }

   public String getContentType() {
      return contentType;
   }

   public void setContentType( String contentType ) {
      this.contentType = contentType;
   }

   public Date getUploadDate() {
      return uploadDate;
   }

   public void setUploadDate( Date uploadDate ) {
      this.uploadDate = uploadDate;
   }

   public Long getSize() {
      return size;
   }

   public void setSize( Long size ) {
      this.size = size;
   }
}