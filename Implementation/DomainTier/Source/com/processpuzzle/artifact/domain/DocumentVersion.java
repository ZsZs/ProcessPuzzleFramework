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