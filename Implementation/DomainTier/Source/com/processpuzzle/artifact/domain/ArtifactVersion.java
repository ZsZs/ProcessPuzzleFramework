/*
Name: 
    - ArtifactVersion

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

import hu.itkodex.commons.persistence.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;

public class ArtifactVersion implements Entity, Comparable<ArtifactVersion> {
   private Integer id;
   private String name;
   private Date creation;
   private Date modification;

   private User creator;
   private User responsible;
   private User lastModifier;
   private boolean isVersionControlled = false;
   private boolean reserveStatus;

   private CommentList commentList;
   private Set<Artifact<?>> relatedArtifacts = new HashSet<Artifact<?>>();
   private Modification nextModification;
   private Modification previousModification;
   protected ArtifactFolder containingFolder = null;

   public ArtifactVersion() {
      this.name = "";
      this.creation = new Date( System.currentTimeMillis() );
      this.modification = this.creation;
   }

   public ArtifactVersion( String name, User creator ) {
      this.name = name;
      this.creator = creator;
      this.creation = new Date( System.currentTimeMillis() );
      this.modification = this.creation;
   }

   public ArtifactVersion( String name, User responsible, Date creationDate ) {
      this( name, responsible );
      this.creation = creationDate;
      this.modification = this.creation;
   }

   public ArtifactFolder getContainingFolder() {
      return containingFolder;
   }

   public void setContainingFolder( ArtifactFolder containingFolder ) {
      this.containingFolder = containingFolder;
   }

   public void setLastModifier( User lastModifier ) {
      this.lastModifier = lastModifier;
   }

   public void setReserveStatus( boolean reserveStatus ) {
      this.reserveStatus = reserveStatus;
   }

   public Object clone() {
      ArtifactVersion version = new ArtifactVersion();
      version.setName( this.name );
      if( this.creation != null )
         version.setCreation( new Date( this.creation.getTime() ) );
      else
         version.setCreation( null );
      if( this.modification != null )
         version.setModification( new Date( this.modification.getTime() ) );
      else
         version.setModification( null );
      version.setResponsible( this.creator );
      version.setVersionControlled( this.isVersionControlled );
      version.setCommentList( this.commentList );
      version.setRelatedArtifacts( new HashSet<Artifact<?>>( this.relatedArtifacts ) );
      return version;
   }

   //Properties
   public User getCreator() { return creator; }
   public Integer getId() { return id; }
   public Date getModification() { return modification; }
   public String getName() { return name; }

   public void setName( String name ) { this.name = name; }

   public User getResponsible() { return responsible; }
   public void setResponsible( User responsible ) { this.creator = responsible; }

   public void setModification( Date modification ) { this.modification = modification; }

   public Date getCreation() {
      return creation;
   }

   public void setCreation( Date creation ) {
      this.creation = creation;
   }

   public boolean isVersionControlled() {
      return isVersionControlled;
   }

   public void setVersionControlled( boolean isVersionControlled ) {
      this.isVersionControlled = isVersionControlled;
   }

   public boolean getIsVersionControlled() {// fot hibernate
      return isVersionControlled;
   }

   public void setIsVersionControlled( boolean isVersionControlled ) {// fot
      // hibernate
      this.isVersionControlled = isVersionControlled;
   }

   public Modification getNextModification() {
      return nextModification;
   }

   public void setNextModification( Modification nextVersion ) {
      this.nextModification = nextVersion;
   }

   public Modification getPreviousModification() {
      return previousModification;
   }

   public void setPreviousModification( Modification previousVersion ) {
      this.previousModification = previousVersion;
   }

   // RelatedArtifact
   public Set<Artifact<?>> getRelatedArtifacts() {
      return relatedArtifacts;
   }

   public void setRelatedArtifacts( Set<Artifact<?>> relatedArtifacts ) {
      this.relatedArtifacts = relatedArtifacts;
   }

   public void addRelatedArtifact( Artifact<?> relatedArtifact ) {
      if( relatedArtifact != null )
         relatedArtifacts.add( relatedArtifact );
   }

   public Artifact<?> findRelatedArtifact( String relatedArtifactName ) {
      if( relatedArtifactName == null )
         return null; // throw new NullPointerException();
      for( Iterator<Artifact<?>> iter = relatedArtifacts.iterator(); iter.hasNext(); ){
         Artifact<?> element = (Artifact<?>) iter.next();
         if( relatedArtifactName.equals( element.getName() ) )
            return element;
      }
      return null;
   }

   public void removeRelatedArtifact( Artifact<?> relatedArtifact ) {
      relatedArtifacts.remove( relatedArtifact );
   }

   public void removeRelatedArtifact( String relatedArtifactName ) {
      for( Iterator<Artifact<?>> iter = relatedArtifacts.iterator(); iter.hasNext(); ){
         Artifact<?> element = (Artifact<?>) iter.next();
         if( relatedArtifactName.equals( element.getName() ) ){
            relatedArtifacts.remove( element );
            break;
         }
      }
   }

   // CommentList
   public CommentList getCommentList() {
      return commentList;
   }

   public void setCommentList( CommentList commentList ) {
      this.commentList = commentList;
   }

   public int compareTo( ArtifactVersion subject ) {
      return 0;
   }

   public User getLastModifier() {
      return lastModifier;
   }

   public boolean isReserveStatus() {
      return reserveStatus;
   }
}