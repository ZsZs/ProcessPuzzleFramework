package com.processpuzzle.artifact.domain;


public class CommentListFactory extends ArtifactFactory<CommentList> {
   static String theMessageWallName = "TheMessageWall";

   public CommentListFactory(){
      super();
   }
   
   public CommentList createCommentList( String name ) {
      if( theMessageWallName.equals( name ) ) throw new IllegalArgumentException( "The name '" + theMessageWallName + "' is used!" );
      return super.create( name );
   }

/*   public CommentList getTheMessageWall() {
      UnitOfWork work = new UnitOfWork( true );
      CommentList theMessageWall = commentListRepository.findCommentListByName( work, theMessageWallName );
      if( theMessageWall == null ){
         theMessageWall = new CommentList( theMessageWallName, artifactTypeRepository.findArtifactTypeByName( work, "MessageWall" ) );
         commentListRepository.addCommentList( work, theMessageWall );
      }
      work.finish();
      return theMessageWall;
   }
*/
}
