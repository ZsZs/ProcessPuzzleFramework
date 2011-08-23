package com.processpuzzle.party.artifact;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

public class UserDataSheetFactory extends ArtifactFactory<UserDataSheet> {

   public UserDataSheet create( String userName, String password ) {
      try{
         return create( userName, password, null );
      }catch( Exception e ){
         return null;
      }
   }

   public UserDataSheet create( String userName, String password, ProcessPuzzleLocale preferredLocale ) {
      // create User
      UserFactory userFactory = applicationContext.getEntityFactory( UserFactory.class );
      if ( preferredLocale == null ) {
         preferredLocale = applicationContext.getDefaultLocale();
      }
      User user = userFactory.createUser( userName, password, preferredLocale );

      // create UserDS
      ArtifactType dataSheetType = super.findTypeFor( UserDataSheet.class );
      return super.create( userName, dataSheetType, user );
   }
   
   public UserDataSheet create( User user ) {
      // create UserDS
      ArtifactType dataSheetType = super.findTypeFor( UserDataSheet.class );
      return super.create( user.getUserName(), dataSheetType, user );
      
   }

}
