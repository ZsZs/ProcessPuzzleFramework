package com.processpuzzle.artifact_management.control;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.control.control.CommandDispatcher;
import com.processpuzzle.application.control.control.CommandInterface;
import com.processpuzzle.application.security.control.AuthorizationException;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFactory;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactFolderRepository;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.PropertyDefinition;
import com.processpuzzle.persistence.domain.EntityIdentityCollitionException;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class CreateArtifactCommand implements CommandInterface {
   public static final String COMMAND_NAME = "CreateArtifact";
   public static final String ARTIFACT_TYPE_NAME_PARAM = "artifactTypeName";
   public static final String ARTIFACT_FOLDER_NAME_PARAM = "artifactFolderName";
   public static final String ARTIFACT_NAME_PARAM = "artifactName";
   private static final String CONSTRUCTION_METHOD_NAME = "create";
   private String artifactTypeName;
   private String artifactFolderName;
   private String artifactName;
   protected ArtifactFolder artifactFolder;
   private ArtifactType subjectArtifactType;
   private Artifact<?> subjectArtifact;
   private User loggedInUser;
   protected XmlActionResponse actionResponse;
   protected CommandDispatcher dispatcher;
   private ArtifactFolderRepository artifactFolderRepository;
   private ProcessPuzzleContext applicationContext;
   private DefaultArtifactRepository artifactRepository;
   private ArtifactTypeRepository artifactTypeRepository;
   private String errorDescription;

   public String execute( CommandDispatcher dispatcher ) throws Exception {
      doAction();
      buildResponse( dispatcher );
      return "";
   }

   public void init( CommandDispatcher dispatcher ) {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      artifactFolderRepository = applicationContext.getRepository( ArtifactFolderRepository.class );
      artifactTypeRepository = (ArtifactTypeRepository) applicationContext.getRepository( ArtifactTypeRepository.class );
      artifactRepository = (DefaultArtifactRepository) applicationContext.getRepository( DefaultArtifactRepository.class );
      this.dispatcher = dispatcher;
      retrieveRequestParameters( dispatcher.getRequest() );
      findSubjectObjects();
      loggedInUser = UserRequestManager.getInstance().currentUser(); // LoggedUserLocator.locate( dispatcher );
      if( loggedInUser == null )
         throw new AuthorizationException( "Unknown", CreateArtifactCommand.class.getSimpleName() );
      setUpResponse();
   }

   public String getName() {
      return COMMAND_NAME;
   }

   protected void buildResponse( CommandDispatcher dispatcher ) throws IOException {
      HttpServletResponse response = dispatcher.getResponse();
      response.setContentType( "text/xml" );
      response.setCharacterEncoding( "UTF-8" );
      response.setHeader( "Cache-Control", "no-cache" );
      response.getWriter().write( actionResponse.getAsString() );
   }

   protected void doAction() {
      if( subjectArtifactType != null ){
         boolean isFolder = (subjectArtifactType.getName().equals( "ArtifactFolder" ));

         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         determineContainingFolder( work );
         List<Class<?>> parameterTypes = new ArrayList<Class<?>>();
         List<Object> parameterValues = new ArrayList<Object>();

         determineCreationParameters( isFolder, parameterTypes, parameterValues );
         subjectArtifact = instantiateAndSaveNewArtifact( parameterTypes, parameterValues, isFolder, work );
         artifactName = subjectArtifact.getName();
         defineCreationResponse( parameterValues.toArray() );
         work.finish();
      }else{
         defineErroneousResponse();
      }
   }

   protected void retrieveRequestParameters( HttpServletRequest request ) {
      artifactTypeName = (String) request.getParameter( ARTIFACT_TYPE_NAME_PARAM );
      artifactFolderName = (String) request.getParameter( ARTIFACT_FOLDER_NAME_PARAM );
      artifactName = (String) request.getParameter( ARTIFACT_NAME_PARAM );
   }

   private void defineErroneousResponse() {
      String errorDescription;
      errorDescription = "<errorDescription>" + "unknownArtifactType" + "</errorDescription>";
      errorDescription += "<parameter>" + artifactTypeName + "</parameter>";
      actionResponse.addStringToBody( errorDescription );
      actionResponse.setOutcome( false );
   }

   private void defineCreationResponse( Object[] propertyValues ) {
      if( errorDescription != null ){
         errorDescription = "<errorDescription>" + errorDescription + "</errorDescription>";
         for( int j = 1; j < propertyValues.length; j++ ){
            errorDescription += "<parameter>" + propertyValues[j] + "</parameter>";
         }
         actionResponse.addStringToBody( errorDescription );
         actionResponse.setOutcome( false );
      }else
         actionResponse.setOutcome( true );
   }

   private void determineContainingFolder( DefaultUnitOfWork work ) {
      String folderPath = subjectArtifactType.getInstanceFolderPath();
      artifactFolder = artifactFolderRepository.findByPath( work, folderPath );
   }

   private void determineCreationParameters( boolean isFolder, List<Class<?>> parameterTypes, List<Object> parameterValues ) {
      Collection<PropertyDefinition> properties = subjectArtifactType.getPropertyDefinitions();

      try{
         for( Iterator<PropertyDefinition> iter = properties.iterator(); iter.hasNext(); ){
            PropertyDefinition propertyDefinition = (PropertyDefinition) iter.next();
            Object parameterValue = getRequestParameterByName( propertyDefinition.getName() );

            if( parameterValue == null )
               throw new IllegalArgumentException( "Artifact creation parameter: " + propertyDefinition.getName() + "not found in request." );

            Class<?> parameterType = Class.forName( propertyDefinition.getType().getJavaClassName() );
            parameterTypes.add( parameterType );
            parameterValues.add( parameterValue );
         }
         if( isFolder ){
            parameterTypes.add( ArtifactFolder.class );
            parameterValues.add( artifactFolder );
         }
      }catch( SecurityException e ){
         errorDescription = "error";
      }catch( IllegalArgumentException e ){
         errorDescription = "illegalArgument";
      }catch( EntityIdentityCollitionException e ){
         errorDescription = "entityIdentityCollition";
      }catch( AuthorizationException e ){
         errorDescription = "permissionDenied";
      }catch( ClassNotFoundException e ){
         errorDescription = "classNotFound";
      }

   }

   @SuppressWarnings( "unchecked" )
   private Artifact<?> instantiateAndSaveNewArtifact( List<Class<?>> paramTypes, List<Object> paramValues, boolean isFolder, DefaultUnitOfWork work ) {
      Artifact<?> newArtifact = null;
      Class<?>[] parameterTypes = new Class<?>[paramTypes.size()];
      Object[] parameterValues = paramValues.toArray();

      int i = 0;
      for( Class<?> parameterType : paramTypes ){
         parameterTypes[i] = parameterType;
         i++;
      }

      try{
         Class<? extends Artifact<?>> artifactClass = (Class<? extends Artifact<?>>) Class.forName( subjectArtifactType
               .getArtifactClassName() );
         ArtifactFactory<?> factory = (ArtifactFactory<?>) applicationContext.getEntityFactoryByEntityClass( artifactClass );
         Method createMethod = factory.getClass().getDeclaredMethod( CONSTRUCTION_METHOD_NAME, parameterTypes );
         newArtifact = (Artifact<?>) createMethod.invoke( factory, parameterValues );
      }catch( ClassNotFoundException e ){
         errorDescription = "unknownArtifactClass";
      }catch( NoSuchMethodException e ){
         errorDescription = "error";
      }catch( IllegalAccessException e ){
         errorDescription = "error";
      }catch( InvocationTargetException e ){
         errorDescription = "invocationTarget";
      }

      if( !isFolder && artifactFolder == null )
         errorDescription = "missingFolder";
      else if( artifactFolder != null ){
         artifactFolder.addChildArtifact( newArtifact );

         // save related artifacts
         for( Iterator<Artifact<?>> iter = newArtifact.getRelatedArtifacts().iterator(); iter.hasNext(); ){
            Artifact<?> a = iter.next();
            artifactFolder.addChildArtifact( a );
         }

         artifactFolderRepository.update( work, artifactFolder );

         actionResponse.addStringToBody( "<id>" + newArtifact.getId() + "</id>" );
      }else if( artifactFolder == null && isFolder && artifactFolderRepository.findByPath( work, newArtifact.getName() ) == null ){
         artifactRepository.add( work, newArtifact );
         actionResponse.addStringToBody( "<id>" + newArtifact.getId() + "</id>" );
      }else
         errorDescription = "artifactIsExist";

      return newArtifact;
   }

   private void findSubjectObjects() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      subjectArtifactType = artifactTypeRepository.findArtifactTypeByName( work, artifactTypeName );
      artifactFolder = (ArtifactFolder) artifactFolderRepository.findByPath( work, artifactFolderName );
      work.finish();
   }

   private String getRequestParameterByName( String name ) {
      return dispatcher.getRequest().getParameter( name );
   }

   private void setUpResponse() {
      actionResponse = new XmlActionResponse();
   }
 
  
}
 
