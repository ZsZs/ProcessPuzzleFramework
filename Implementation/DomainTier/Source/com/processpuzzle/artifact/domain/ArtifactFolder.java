/*
Name: 
    - ArtifactFolder

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
 * Created on Jun 30, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;

public class ArtifactFolder extends Artifact<ArtifactFolder> {
   public static final String ROOT_ARTIFACT_FOLDER_NAME = "RootArtifactFolder";
   public static final String PATH_SEPARATOR = ".";
   private Set<Artifact<?>> childArtifacts = new HashSet<Artifact<?>>();

   //Constructors
   public ArtifactFolder( String name, ArtifactType type, User creator, ArtifactFolder parentFolder ) {
      super( name, type, creator );
      if (parentFolder != null ) {
         parentFolder.addChildArtifact(this);
      }
   }

   protected ArtifactFolder() {
      super();
   }

   //Public accessors
   public String getAsXml() {
      return super.getAsXml(ArtifactFolder.class, this);
   }
   
   public Set<Artifact<?>> getChildArtifacts() { return childArtifacts; }

   public @Override DefaultIdentityExpression<ArtifactFolder> getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "nameValue", name );
      DefaultIdentityExpression<ArtifactFolder> identity = new ArtifactFolderIdentity( context );
      return identity;
   }
   
   public Artifact<?> findChildArtifact(String artifactName) {
      for( Iterator<Artifact<?>> iter = childArtifacts.iterator(); iter.hasNext();) {
         Artifact<?> child = iter.next();
         if (child.getName().equals(artifactName))
            return child;
      }
      return null;
   }

   //Public mutators
   public void addChildArtifact( Artifact<?> anArtifact ) {
      childArtifacts.add(anArtifact);
      anArtifact.setContainingFolder(this);
   }
}
