/*
 * Created on May 18, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.processpuzzle.application.security.domain.UserFactory;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactModificationsView extends ArtifactView<Artifact<?>> {
   private List<Modification> modifications = new ArrayList<Modification>();
   private UserFactory userFactory;

   public ArtifactModificationsView( Artifact<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      userFactory = applicationContext.getEntityFactory( UserFactory.class );
   }

   public void initializeView() {
      collectModifications();
   }

   public List<Modification> getModifications() {
      return modifications;
   }

   private void collectModifications() {
      //Fine this is not a real world implementation. It just helps to test browser interface.
      Locale huLocale = new Locale( "hu" );

      Modification modificationOne = new Modification( null, null, null );
      modificationOne.setId( new Integer( 1 ) );
      modificationOne.setModificationPeriod( new TimePeriod( new TimePoint( 1998, 11, 22 ), new TimePoint( 1998, 11, 22 ) ) );
      modificationOne.setModifier( userFactory.createUser( "Nagy Attila", "ProcessPuzzle" ) );
      modificationOne.setComment( "comment1" );

      modifications.add( modificationOne );

      Modification modificationTwo = new Modification( null, null, null );
      modificationTwo.setId( new Integer( 2 ) );
      modificationTwo.setModificationPeriod( new TimePeriod( new TimePoint( 1998, 11, 22 ), new TimePoint( 1998, 11, 22 ) ) );
      modificationTwo.setModifier( userFactory.createUser( "Bárczi Benõ", "ProcessPuzzle" ) );
      modificationTwo.setComment( "comment2" );

      modifications.add( modificationTwo );
   }

   public Date getCurrentDate() {
      return new Date( System.currentTimeMillis() );
   }
}
