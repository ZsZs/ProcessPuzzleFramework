/*
 * Created on Mar 29, 2006
 */
package com.processpuzzle.artifact_management.control;

import java.security.InvalidParameterException;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact.domain.GenericArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeNotFoundException;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

/**
 * @author zsolt.zsuffa
 */
public class ArtifactLocator {
   public static final String ARTIFACT_IDENTIFIERNAME = "artifactId";
   public static final String ARTIFACTNAME_IDENTIFIERNAME = "artifactName";
   public static final String ARTIFACTTYPE_IDENTIFIERNAME = "artifactType";
   private ProcessPuzzleContext applicationContext;
   private CommandDispatcher dispatcher;
   private Integer artifactId;
   private String artifactName;
   private String artifactTypeName;
   private GenericArtifactRepository<?> artifactRepository;
   private ArtifactTypeRepository artifactTypeRepository;
   private ArtifactType artifactType;
   private Class<? extends Artifact<?>> artifactClass;

   public ArtifactLocator( CommandDispatcher dispatcher ) {
      this.dispatcher = dispatcher;
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      artifactTypeRepository = applicationContext.getRepository( ArtifactTypeRepository.class );
      retrieveStandardRequestAttributes();
      determineArtifactRepository();
   }

   public Artifact<?> findById( DefaultUnitOfWork work ) {
      if( artifactId == null )
         throw new InvalidParameterException( "The parameter '" + ARTIFACT_IDENTIFIERNAME + "' is required!" );
      Artifact<?> artifact = artifactRepository.findById( work, artifactId );
      return artifact; // null allowed
   }

   public Artifact<?> findByName( DefaultUnitOfWork work ) {
      if( artifactName == null )
         throw new InvalidParameterException( "The parameter '" + ARTIFACTNAME_IDENTIFIERNAME + "' is required!" );

      /*
       * RepositoryResultSet<Artifact> artifacts = artifactRepository.findAll(work); for (Iterator<?> iterator =
       * artifacts.iterator(); iterator.hasNext();) { Artifact<?> artifact = (Artifact<?>) iterator.next();
       * System.out.println("ArtifactLocator: - " + artifact.getPath()); }
       */

      Artifact<?> artifact = artifactRepository.findByName( work, artifactName );
      return artifact; // null allowed
   }

   public Artifact<?> findByType( DefaultUnitOfWork work ) {
      if( artifactTypeName == null )
         throw new InvalidParameterException( "The parameter '" + ARTIFACTTYPE_IDENTIFIERNAME + "' is required!" );
      Artifact<?> artifact = null;
      if( artifactType != null ) {
         artifact = artifactRepository.findByName( work, artifactTypeName );
         
         /*
         String artifactClassName = artifactType.getArtifactClassName();
         int lastDot = artifactClassName.lastIndexOf( "." );
         artifactClassName = artifactClassName.substring( lastDot + 1 );
         Class<? extends Artifact<?>> artifactClass = Class.forName( artifactClassName );
         */
      }
      return artifact; // null allowed
   }

   public Artifact<?> findArtifact( DefaultUnitOfWork work ) {
      boolean found = false;
      Artifact<?> artifact = null;
      try{
         artifact = findByType( work );
         if( artifact != null ) found = true;         
      }catch( InvalidParameterException e ){}
      
      if( !found ){
         try{
            artifact = findByName( work );
            if( artifact != null ) found = true;
         }catch( InvalidParameterException e ){}
      }

      if( !found ){
         try{
            artifact = findById( work );
            if( artifact != null ) found = true;
         }catch( InvalidParameterException e ){}
      }
      return artifact;
   }

   @SuppressWarnings("unchecked")
   private void determineArtifactClass() {
      artifactType = artifactTypeRepository.findByName( artifactTypeName );
      if( artifactType == null ) throw new ArtifactTypeNotFoundException( artifactTypeName );
      
      String artifactClassName = artifactType.getArtifactClassName();
      try{
         artifactClass = (Class<? extends Artifact<?>>) Class.forName( artifactClassName );
      }catch( ClassNotFoundException e ){}
   }

   @SuppressWarnings("unchecked")
   private void determineArtifactRepository() {
      if( artifactTypeName != null && artifactClass != null ) {
         artifactRepository = (GenericArtifactRepository) applicationContext.getRepositoryByEntityClass( artifactClass );
      }else
         artifactRepository = (GenericArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
   }

   private void retrieveStandardRequestAttributes() {
      retrieveArtifactId();
      retrieveArtifactName();      
      retrieveArtifactType();
   }

   private void retrieveArtifactType() {
      String artifactTypeIdentifier = dispatcher.getProperties().getProperty( ARTIFACTTYPE_IDENTIFIERNAME );
      if( artifactTypeIdentifier != null && !artifactTypeIdentifier.equals( "" )) {
         artifactTypeName = artifactTypeIdentifier;
         determineArtifactClass();         
      }
   }

   private void retrieveArtifactName() {
      String artifactNameIdentifier = dispatcher.getProperties().getProperty( ARTIFACTNAME_IDENTIFIERNAME );
      if( artifactNameIdentifier != null && !artifactNameIdentifier.equals( "" ) )
         artifactName = artifactNameIdentifier;
   }

   private void retrieveArtifactId() {
      String artifactIdentifier = dispatcher.getProperties().getProperty( ARTIFACT_IDENTIFIERNAME );
      if( artifactIdentifier != null && !artifactIdentifier.equals( "" )) {
         Integer artifactIdentifierInteger = null;
         try{
            artifactIdentifierInteger = new Integer( artifactIdentifier );
         }catch( NumberFormatException nfe ){
            throw new InvalidParameterException( "The parameter '" + ARTIFACT_IDENTIFIERNAME + "' is not set properly!" );
         }         
         artifactId = artifactIdentifierInteger;
      }
   }
}