package com.processpuzzle.artifact_management.control;

import java.util.SortedSet;
import java.util.TreeSet;

import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.CommentListFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ShowDocumentNotesCommand extends ArtifactCommand {

   public void addCommentListToArtifact() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );

      CommentListFactory commentListFactory = applicationContext.getEntityFactory( CommentListFactory.class );
      CommentList commentList = commentListFactory.createCommentList( subjectArtifact.getName() + "_CommentList" );
      subjectArtifact.addComments( commentList );
      artifactRepository.update( work, subjectArtifact );
      work.finish();
   }

   public void init( CommandDispatcher dispatcher ) {
      super.init( dispatcher );
      if( subjectArtifact.getCommentList() == null )
         addCommentListToArtifact();
      CommentList commentList = subjectArtifact.getCommentList();
      SortedSet<?> comments = new TreeSet( commentList.getAllComments() );
      dispatcher.getRequest().setAttribute( "commentListId", commentList.getId() );
      dispatcher.getRequest().setAttribute( "commentListName", commentList.getName() );
      dispatcher.getRequest().setAttribute( "comments", comments );
   }

   public String getName() {
      return "ShowDocumentNotes";
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return "/ProcessInstantiation/ProjectAdministration/Artifact_Comments.jsp";
   }

   protected void retrieveRequestParameters( CommandDispatcher dispatcher ) {
   // TODO Auto-generated method stub

   }

   protected void retrieveResponseDocument() {
   // TODO Auto-generated method stub
   }
}