package com.processpuzzle.artifact.domain;

import java.util.SortedSet;
import java.util.TreeSet;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class CommentList_EditableTextView extends EditableTextView<CommentList> {

   public CommentList_EditableTextView( CommentList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override protected String retrieveObjectsAsXml() {
      return null;
   }

   @Override protected void updateObjectsFromXml( String data ) {
      // TODO Auto-generated method stub
   }

   @Override public void initializeView() {
      // TODO Auto-generated method stub
   }
   
   public SortedSet<Comment> getComments() {
      SortedSet<Comment> comments = new TreeSet<Comment>( parentArtifact.getComments() ); 
      return comments; 
   }
}
