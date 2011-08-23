package com.processpuzzle.artifact.domain;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.persistence.domain.GenericFactory;

public class CommentFactory extends GenericFactory<Comment> {

   public CommentFactory() {
      super();
   }
   
   public Comment create( String title, String text ) {
      creator = determineCurrentUser();
      Class<?>[] argumentClasses = new Class[] { User.class, String.class, String.class };
      Object[] arguments = new Object[] { creator, title, text };
      Comment comment = instantiateEntity( argumentClasses, arguments );
      return comment;
   }
}
