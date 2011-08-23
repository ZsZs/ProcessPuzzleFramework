package com.processpuzzle.artifact.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.party.domain.PersonFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.application.configuration.domain.ConfigurationSetUpException;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.Comment;
import com.processpuzzle.artifact.domain.CommentFactory;
import com.processpuzzle.artifact.domain.CommentList;
import com.processpuzzle.artifact.domain.CommentListFactory;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;

public class CommentListTest {
   private static DefaultArtifactRepository repository;
   private static ProcessPuzzleContext config;
   private CommentListFactory commentListFactory;
   private CommentFactory commentFactory;
   private CommentList commentList;
   private PersonFactory personFactory;

   @BeforeClass public static void beforeAllTests() {
      config = ProcessPuzzleContext.getInstance();
      try {
         config.setUp( Application.Action.start );
      } catch (ConfigurationSetUpException e) {
         e.printStackTrace();
      }
      repository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(DefaultArtifactRepository.class);
   }

   @Before
   public void setUp() throws Exception {
      commentListFactory = config.getEntityFactory( CommentListFactory.class );
      commentList = commentListFactory.createCommentList("commentList");
      
      commentFactory = config.getEntityFactory( CommentFactory.class );
      personFactory = config.getEntityFactory( PersonFactory.class );
   }

   @After
   public void tearDown() throws Exception {
      commentList = null;
   }

   @Ignore
   @Test
   public void appendComment() {
      Comment comment = commentFactory.create( "Teszt", "Text" );
      commentList.appendComment(comment);
      assertTrue("We can find a previously comment", commentList.getAllComments().contains(comment));
   }

   @Ignore
   @Test
   public void update() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      commentList = commentListFactory.createCommentList("commentList");
      Comment comment = commentFactory.create( "Teszt", "Text" );
      commentList.appendComment(comment);
      repository.add(work, commentList);
      assertNotNull("Comment list get id.", ((Comment) commentList.getComments().iterator().next()).getId());
      assertNotNull("Comment list there is in the database.", repository.findByName(work, "commentList"));
      work.finish();
   }
}