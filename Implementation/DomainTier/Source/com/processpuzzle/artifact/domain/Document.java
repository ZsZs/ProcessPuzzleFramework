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