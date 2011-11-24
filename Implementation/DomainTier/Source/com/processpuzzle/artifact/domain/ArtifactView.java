/*
Name: 
    - ArtifactView

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

/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PersonRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
@XmlRootElement( name = "artifactView" )
@XmlAccessorType( XmlAccessType.NONE )
@XmlSeeAlso({
   PropertyView.class
})
public abstract class ArtifactView<A extends Artifact<?>> {
   protected Logger log = LoggerFactory.getLogger( ArtifactView.class );
   protected A parentArtifact = null;
   private String viewName;
   protected ArtifactViewType type;
   private String codeBase;
   protected User loggedInUser;
   protected ProcessPuzzleContext applicationContext;
   protected ProcessPuzzleLocale defaultLocale;
   protected DefaultArtifactRepository artifactRepository;
   protected PartyRepository partyRepository;
   protected PersonRepository personRepository;
   protected PartyRoleTypeRepository partyRoleTypeRepository;

   //Constructors
   public ArtifactView( A artifact, String viewName, ArtifactViewType type ) {
      this.parentArtifact = artifact;
      this.viewName = viewName;
      this.type = type;
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      defaultLocale = applicationContext.getDefaultLocale();
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      personRepository = applicationContext.getRepository( PersonRepository.class );
      partyRepository = applicationContext.getRepository( PartyRepository.class );
      partyRoleTypeRepository = applicationContext.getRepository( PartyRoleTypeRepository.class );
      loggedInUser = UserRequestManager.getInstance().currentUser();
   }

   public ArtifactView( A artifact, String name ) {
      this( artifact, name, null );
   }
   
   public ArtifactView() {} //Used by JAXB

   @SuppressWarnings("unchecked")
   public void delete( String id ) {
      log.info( "Deleteing Artifact: " + id );
      DefaultArtifactRepository artifactRepository = UserRequestManager.getInstance().getApplicationContext().getRepository( DefaultArtifactRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      A artifact = (A) artifactRepository.findById( work, new Integer( id ) );
      artifactRepository.delete( work, artifact );
      work.finish();
   }

   //Public mutators
   public abstract void initializeView();

   //Properties
   public @XmlElement String getArtifactId() { return this.parentArtifact.getId().toString(); }
   public @XmlElement String getArtifactName() { return parentArtifact.getName(); }
   public String getCodeBase() { return codeBase; }
   public String getData( String method, Map<String, String> parameters ) { return null; }
   public @XmlTransient User getLoggedInUser() { return loggedInUser; }
   public @XmlElement( name = "viewName" ) String getName() { return viewName; }
   public A getParentArtifact() { return parentArtifact; }
   public @XmlElement String getPreferredLanguage() {
      if( this.loggedInUser != null ) return loggedInUser.getPrefferedLocale().getLanguage();
      else return null;
   }
   public @XmlElement( name="viewType" ) String getTypeName() { return type.getName(); }
   public ArtifactViewType getType() { return type; }
   public void setCodeBase( String codeBase ) { this.codeBase = codeBase; }
   public void setLoggedInUser( User user ) { this.loggedInUser = user; }
}