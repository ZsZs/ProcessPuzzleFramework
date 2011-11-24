/*
Name: 
    - DocumentVersion

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
import java.util.HashSet;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;

public class DocumentVersion extends ArtifactVersion {
   private String title;
   private String subject;
   private String content;

   public DocumentVersion() {
      super();
   }

   public DocumentVersion( String name, User responsible ) {
      super( name, responsible );
   }

   public DocumentVersion( String name, User responsible, Date creationDate ) {
      super( name, responsible, creationDate );
   }

   public DocumentVersion( String name, User responsible, ArtifactType type ) {
      super( name, responsible );
   }

   public String getContent() {
      return content;
   }

   public void setContent( String content ) {
      this.content = content;
   }

   public String getSubject() {
      return subject;
   }

   public void setSubject( String subject ) {
      this.subject = subject;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle( String title ) {
      this.title = title;
   }

   public Object clone() {
      DocumentVersion version = new DocumentVersion();
      version.setName( getName() );
      version.setCreation( getCreation() );
      version.setModification( getModification() );
      version.setResponsible( getCreator() );
      version.setVersionControlled( getIsVersionControlled() );
      version.setCommentList( getCommentList() );
      version.setRelatedArtifacts( new HashSet<Artifact<?>>( (Set<Artifact<?>>) getRelatedArtifacts() ) );
      version.setNextModification( getNextModification() );
      version.setPreviousModification( getPreviousModification() );
      version.setTitle( this.title );
      version.setContent( this.content );
      version.setSubject( this.subject );
      return version;
   }
}