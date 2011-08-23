package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.Entity;

import java.util.Date;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.user_session.domain.UserRequestManager;
import com.processpuzzle.workflow.protocol.domain.ProtocolRepository;

public abstract class ArtifactFactory<A extends Artifact<?>> extends GenericFactory<A> {
   protected DefaultArtifactRepository artifactRepository;
   protected ArtifactTypeRepository artifactTypeRepository;
   protected PartyTypeRepository partyTypeRepository;
   protected ProtocolRepository protocolRepository;
   protected ArtifactType subjectArtifactType;
   protected Class<A> artifactClass;

   @SuppressWarnings( "unchecked" )
   public ArtifactFactory() {
      super();
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );
      protocolRepository = applicationContext.getRepository( ProtocolRepository.class );
      artifactClass = (Class<A>) GenericTypeParameterInvestigator.getTypeParameter( this.getClass(), 0 );
      subjectArtifactType = findTypeFor( artifactClass );
   }

   public A create( String artifactName ) {
      if( subjectArtifactType == null )
         throw new NoneExistingArtifactTypeException( artifactClass.getSimpleName(), artifactName );

      return create( artifactName, subjectArtifactType );
   }

   public A create( String artifactName, ArtifactType type ) {
      creator = UserRequestManager.getInstance().currentUser();
      Class<?>[] argumentClasses = new Class[] { String.class, ArtifactType.class, User.class };
      Object[] arguments = new Object[] { artifactName, type, creator };
      A newArtifact = instantiateEntity( argumentClasses, arguments );
      checkEntityIdentityCollition( newArtifact.getDefaultIdentity() );
      return newArtifact;
   }

   public A create( String artifactName, Entity referencedDomainObject ) {
      return this.create( artifactName, subjectArtifactType, referencedDomainObject );
   }
   
   public A create( String artifactName, ArtifactType type, Entity referencedDomainObject ) {
      if( type.getDomainClassName() == null ){ return create( artifactName, type ); }
      
      try{
         creator = UserRequestManager.getInstance().currentUser();
         Class<?>[] argumentClasses = new Class[] { String.class, ArtifactType.class, User.class, Class.forName( type.getDomainClassName() ) };
         Object[] arguments = new Object[] { artifactName, type, creator, referencedDomainObject };
         A newArtifact = instantiateEntity( argumentClasses, arguments );
         checkEntityIdentityCollition( newArtifact.getDefaultIdentity() );
         return newArtifact;
      }catch( ClassNotFoundException e ){
         throw new NoneExistingEntityForCreationArtifactException( type.getDomainClassName(), artifactName );
      }
   }

   public static ArtifactVersion createArifactVersion( String name, User responsible, Date creation ) {
      return new ArtifactVersion( name, responsible, creation );
   }

   // Protected, private helper methods
   protected ArtifactType findTypeFor( Class<? extends Artifact<?>> artifactClass ) {
      return artifactTypeRepository.findByArtifactClassName( artifactClass.getName() );
   }

   protected PartyType findPartyTypeFor( Class<? extends Party<?>> partyClass ) {
      return partyTypeRepository.findByPartyClassName( partyClass.getName() );
   }
}