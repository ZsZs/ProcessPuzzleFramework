/*
Name: 
    - Document

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

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class Document extends Artifact<Document> {
   
   // Constructors
   public Document( String name, ArtifactType type, User responsible ) {
      super( name, type, responsible );
   }

   protected Document() {
      super();
   }

   public @Override DocumentIdentity getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   //Properties
   public String getContent() {
      return ((DocumentVersion) latest()).getContent();
   }

   public void setContent( String content ) {
      ((DocumentVersion) latest()).setContent( content );
   }

   public String getSubject() {
      return ((DocumentVersion) latest()).getSubject();
   }

   public void setSubject( String subject ) {
      ((DocumentVersion) latest()).setSubject( subject );
   }

   public String getTitle() {
      return ((DocumentVersion) latest()).getTitle();
   }

   public void setTitle( String title ) {
      ((DocumentVersion) latest()).setTitle( title );
   }

   public String getAsXml() {
      return super.getAsXml( Document.class, this );
   }

   protected void instantiateVersion( String name, ArtifactType type, User responsible ) {
      getVersions().put( new Integer( versions.size() + 1 ), new DocumentVersion( name, responsible, type ) );
   }

   protected @Override void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", name );
      defaultIdentity = new DocumentIdentity( context );
      identities.add( defaultIdentity );
   }
}