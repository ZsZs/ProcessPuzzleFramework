package com.processpuzzle.artifact_management.control;

import java.util.SortedSet;
import java.util.TreeSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.CommentListFactory;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ShowMessageWallCommand implements CommandInterface {
   private CommentListFactory commentListFactory;
   private ProcessPuzzleContext applicationContext;

   public void init( CommandDispatcher dispatcher ) {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      commentListFactory = applicationContext.getEntityFactory( CommentListFactory.class );
//      CommentList messageList = commentListFactory.getTheMessageWall();
//      SortedSet messages = new TreeSet( messageList.getAllComments() );
//      dispatcher.getRequest().setAttribute( "messageListId", messageList.getId() );
//      dispatcher.getRequest().setAttribute( "messages", messages );
   }

   public String getName() {
      return "ShowMessageWall";
   }

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      return "./MessageWall/MessageWall.jsp";
   }
}