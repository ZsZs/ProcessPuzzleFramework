/*
 * Created on Mar 28, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;

/**
 * @author zsolt.zsuffa
 */
public class CommentList extends Document {
   private Set<Comment> comments = new HashSet<Comment>();

   public CommentList( String name, ArtifactType type, User responsible ) {
      super( name, type, responsible );
   }

   protected CommentList() {
      super();
   }

   public void appendComment( Comment comment ) {
      if( comment.getDivId() == null || (comment.getDivId() != null && comment.getDivId().equals( "" )) )
         comment.setDivId( getGeneratedDivId() );
      comments.add( comment );
   }

   public Set<Comment> getAllComments() {
      return comments;
   }

   public Set<Comment> getComments() {
      return comments;
   }

   public void setComments( Set<Comment> comments ) {
      this.comments = comments;
   }

   public String getGeneratedDivId() {
      String generatedId = null;
      Random rnd = new Random();
      while( existsDivId( generatedId = "editorDiv" + rnd.nextInt( 1000 ) ) )
         ;
      return generatedId;
   }

   private boolean existsDivId( String divId ) {
      if( divId == null ) return true;
      
      Iterator<Comment> it = comments.iterator();
      while( it.hasNext() ){
         Comment comment = (Comment) it.next();
         if( divId.equals( comment.getDivId() ) )
            return true;
      }
      return false;
   }
}