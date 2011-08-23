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
