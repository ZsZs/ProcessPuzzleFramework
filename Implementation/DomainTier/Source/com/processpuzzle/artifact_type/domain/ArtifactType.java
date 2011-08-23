package com.processpuzzle.artifact_type.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.application.configuration.domain.RunTimeClassHierarchyAnalyser;
import com.processpuzzle.application.security.domain.DefaultAccessRight;
import com.processpuzzle.artifact.artifact.ArtifactList;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactFolder;
import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact.domain.ArtifactView;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.business.definition.domain.SystemArtifact;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;
import com.processpuzzle.util.domain.GeneralService;

@XmlRootElement( name = "artifactType" )
public class ArtifactType extends GenericEntity<ArtifactType> implements AssetType {
   @XmlAttribute @XmlID protected String name;
   @XmlAttribute protected String artifactClassName;
   @XmlAttribute protected String domainClassName;
   @XmlAttribute protected String instanceFolder;
   @XmlElement protected String description;
   @XmlElement protected String caption;
   @XmlElement protected String baseUri;
   @XmlAttribute protected boolean isSystem = false;
   @XmlAttribute( name = "createOnStartUp" ) protected boolean createOnStartup = false;
   @XmlAttribute protected boolean isSingleton = false;
   @XmlAttribute protected boolean pessimisticLock = false;
   @XmlAttribute protected boolean isVersionControlled = false;
   @XmlAttribute protected boolean refreshOnDocumentActivation = false;
   @XmlAttribute protected boolean refreshOnViewActivation = false;
   @XmlElementWrapper( name = "availableViews" ) @XmlElementRef
   protected List<ArtifactViewType> availableViews = new ArrayList<ArtifactViewType>();
   @XmlElementWrapper( name = "creationProperties" ) @XmlElement( name = "creationProperty" )
   private List<PropertyDefinition> propertyDefinitions = new ArrayList<PropertyDefinition>();
   @XmlElementWrapper( name = "defaultAccessRights" ) @XmlElement( name = "accessRight" )
   private List<DefaultAccessRight> defaultAccessRights = new ArrayList<DefaultAccessRight>();
   @XmlElementWrapper( name = "associatedMenuItems" ) @XmlElement( name = "menuItem" )
   private List<ArtifactMenu> associatedMenuItems = new ArrayList<ArtifactMenu>();
   @XmlIDREF @XmlAttribute( name = "group" ) private ArtifactTypeGroup group;

   ArtifactType( String typeName, ArtifactTypeGroup typeGroup, Class<? extends Artifact<?>> artifactClass ) {
      this.name = typeName ;
      this.group = typeGroup;
      this.artifactClassName = artifactClass != null ? artifactClass.getName() : null;
   }
   
   ArtifactType( String typeName, ArtifactTypeGroup typeGroup ) {
      this( typeName, typeGroup, null );
   }

   protected ArtifactType() {}

   public ArtifactType( String typeName ) {
      this( typeName, null );
   }

   // Public accessors
   public boolean canPlayRole( String what, String partyRoleTypeName ) {
      for( Iterator<DefaultAccessRight> iter = defaultAccessRights.iterator(); iter.hasNext(); ){
         DefaultAccessRight defaultAccessRight = iter.next();
         if( defaultAccessRight.getUserRoleName().equals( partyRoleTypeName ) ){
            what.substring( 0, 1 ).toUpperCase();
            Method method = null;
            try{
               method = DefaultAccessRight.class.getDeclaredMethod( "isCan" + what, new Class[] {} );
            }catch( SecurityException e ){
               e.printStackTrace();
            }catch( NoSuchMethodException e ){
               e.printStackTrace();
            }
            try{
               return ((Boolean) method.invoke( defaultAccessRight, new Object[] {} )).booleanValue();
            }catch( IllegalArgumentException e ){
               e.printStackTrace();
            }catch( IllegalAccessException e ){
               e.printStackTrace();
            }catch( InvocationTargetException e ){
               e.printStackTrace();
            }
         }
      }
      return false;
   }

   public DefaultAccessRight findAccessRightsFor( String partyRoleName ) {
      for( DefaultAccessRight anAccessRight : defaultAccessRights ){
         if( anAccessRight.getUserRoleName().equals( partyRoleName ) )
            return anAccessRight;
      }
      return null;
   }

   public ArtifactMenu findMenu( String name ) {
      for( ArtifactMenu menu : associatedMenuItems ){
         if( menu.getName().equals( name ) )
            return menu;
      }
      return null;
   }

   public PropertyDefinition findProperty( String propertyName ) {
      for( PropertyDefinition property : propertyDefinitions ){
         if( property.getName().equals( propertyName ) )
            return property;
      }
      return null;
   }

   public ArtifactViewType findView( String name ) {
      for( ArtifactViewType viewType : availableViews ){
         if( viewType.getName().equals( name ) )
            return viewType;
      }
      return null;
   }

   // todo: improve exception handling
   @SuppressWarnings("unchecked")
   public void instantiateViewsFor( Artifact<?> artifact ) {
      for( Iterator<ArtifactViewType> iter = availableViews.iterator(); iter.hasNext(); ){
         ArtifactViewType aViewType = iter.next();
         String viewClassName = aViewType.getViewClassName();
         
         if( viewClassName != null && !viewClassName.equals( "" ) ){
            String viewName = GeneralService.getLastToken( viewClassName, "." );

            Class<? extends ArtifactView<?>> artifactViewClass = null;
            Class<? extends Artifact<?>> artifactClass = null;
            ArtifactView<?> artifactView = null;
            
            try{
               artifactViewClass = (Class<? extends ArtifactView<?>>) Class.forName( viewClassName );
               artifactClass = (Class<? extends Artifact<?>>) Class.forName( artifactClassName );
               Class[] argumentClasses = new Class[] { artifactClass, String.class, ArtifactViewType.class };
               Object[] arguments = new Object[] { artifact, viewName, aViewType };
               Constructor viewConstructor = determineViewConstructor( artifactViewClass, argumentClasses );
               artifactView = instantiateView( viewConstructor, argumentClasses, arguments );
            }catch( ClassNotFoundException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }catch( InstantiationException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }catch( IllegalAccessException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }catch( SecurityException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }catch( IllegalArgumentException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }catch( InvocationTargetException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }catch( NoSuchMethodException e ){
               throw new ArtifactViewInstantiationException( artifact, viewClassName, e );
            }
            
            artifact.addView( viewName, artifactView );
         }
      }
   }

   public String getArtifactClassName() {
      return artifactClassName;
   }

   public void setArtifactClassName( String artifactClassName ) {
      this.artifactClassName = artifactClassName;
   }

   public List<ArtifactViewType> getAvailableViews() {
      return availableViews;
   }

   public void setAvailableViews( List<ArtifactViewType> availableViews ) {
      this.availableViews = availableViews;
   }

   public String getBaseUri() {
      return baseUri;
   }

   public void setBaseUri( String baseUri ) {
      this.baseUri = baseUri;
   }

   public String getCaption() {
      return caption;
   }

   public void setCaption( String caption ) {
      this.caption = caption;
   }

   public boolean isCreateOnStartup() {
      return createOnStartup;
   }

   public void setCreateOnStartup( boolean createOnStartup ) {
      this.createOnStartup = createOnStartup;
   }

   public ArtifactTypeGroup getGroup() {
      return group;
   }

   public void setGroup( ArtifactTypeGroup group ) {
      this.group = group;
   }

   public boolean isSingleton() {
      return isSingleton;
   }

   public void setSingleton( boolean isSingleton ) {
      this.isSingleton = isSingleton;
   }

   public boolean isSystem() {
      return isSystem;
   }

   public void setSystem( boolean isSystem ) {
      this.isSystem = isSystem;
   }

   public boolean isVersionControlled() {
      return isVersionControlled;
   }

   public void setVersionControlled( boolean isVersionControlled ) {
      this.isVersionControlled = isVersionControlled;
   }

   public boolean isRefreshOnDocumentActivation() {
      return refreshOnDocumentActivation;
   }

   public boolean isRefreshOnViewActivation() {
      return refreshOnViewActivation;
   }

   public boolean isPessimisticLock() {
      return pessimisticLock;
   }

   public void setPessimisticLock( boolean pessimisticLock ) {
      this.pessimisticLock = pessimisticLock;
   }

   public List<PropertyDefinition> getPropertyDefinitions() { return propertyDefinitions; }

   public void setPropertyDefinitions( List<PropertyDefinition> propertyDefinitions ) {
      this.propertyDefinitions = propertyDefinitions;
   }

   public void addViewType( ArtifactViewType aView ) {
      this.availableViews.add( aView );
   }

   public ArtifactViewType getViewType( String viewTypeName ) {
      for( Iterator<ArtifactViewType> iter = availableViews.iterator(); iter.hasNext(); ){
         ArtifactViewType viewType = iter.next();
         if( viewType.getName().equals( viewTypeName ) )
            return viewType;
      }
      return null;
   }

   public List<DefaultAccessRight> getDefaultAccessRights() { return defaultAccessRights; }

   public void setDefaultAccessRights( List<DefaultAccessRight> defaultAccessRights ) {
      this.defaultAccessRights = defaultAccessRights;
   }

   public DefaultAccessRight getDefaultRightByUserRole( String roleName ) {
      for( Iterator<DefaultAccessRight> iter = this.getDefaultAccessRights().iterator(); iter.hasNext(); ){
         DefaultAccessRight defaultAccessRight = iter.next();
         if( defaultAccessRight.getUserRoleName().equals( roleName ) ){
            return defaultAccessRight;
         }
      }
      for( Iterator<DefaultAccessRight> iter = this.getGroup().getDefaultAccessRights().iterator(); iter.hasNext(); ){
         DefaultAccessRight groupRight = iter.next();
         if( groupRight.getUserRoleName().equals( roleName ) ){
            return groupRight;
         }

      }
      return null;
   }

   public String getDomainClassName() {
      return domainClassName;
   }

   public @Override <I extends DefaultIdentityExpression<ArtifactType>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   public @Override String getDescription() { return description; }

   @Override
   public String getName() { return name; }

   public String getInstanceFolderName() {
      if( instanceFolder == null ) 
         instanceFolder = artifactClassName.substring( artifactClassName.lastIndexOf( "." ) +1 ) + "Instances";
      return instanceFolder; 
   }

   public String getInstanceFolderPath() {
      String path = SystemArtifact.ARTIFACTS_FOLDER.getPath();
      path += ArtifactFolder.PATH_SEPARATOR + group.getName();
      path += ArtifactFolder.PATH_SEPARATOR + getInstanceFolderName(); 
      return path;
   }

   @Override
   public void setDescription( String description ) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public int compareTo( ResourceType o ) {
      // TODO Auto-generated method stub
      return 0;
   }

   protected @Override void defineIdentityExpressions() {
      defaultIdentity = new ArtifactTypeIdentity();
      identities.add( defaultIdentity );
   }

   private Constructor<?> determineViewConstructor( Class<? extends ArtifactView<?>> artifactViewClass, Class<?>[] argumentClasses ) throws NoSuchMethodException {
      Constructor<?> viewConstructor = null;
      try{
         viewConstructor = artifactViewClass.getConstructor( argumentClasses );
      }catch( SecurityException e ){
         throw e;
      }catch( NoSuchMethodException e ){
         RunTimeClassHierarchyAnalyser analyser = new RunTimeClassHierarchyAnalyser();
         //Class<?>[] replacedClasses = null;
         boolean replaceHappened = false;
         
         if( analyser.checkIfIsChildOf( artifactViewClass, ArtifactListView.class ) )
            replaceHappened = tryToReplaceSpecificArtifactWithMoreGenericArtifact( argumentClasses, ArtifactList.class ); 
         else
            replaceHappened = tryToReplaceSpecificArtifactWithMoreGenericArtifact( argumentClasses, Artifact.class ); 
         
         if( replaceHappened ) 
            viewConstructor = determineViewConstructor( artifactViewClass, argumentClasses );
         else throw e;
      }
      return viewConstructor;
   }

   @SuppressWarnings("unchecked")
   private boolean tryToReplaceSpecificArtifactWithMoreGenericArtifact( Class<?>[] argumentClasses, Class<? extends Artifact> artifactSubClass ) {
      //Class<?>[] replacedClasses = new Class<?>[argumentClasses.length];
      boolean replaceHappened = false;
      RunTimeClassHierarchyAnalyser classHierarchyAnalyser = new RunTimeClassHierarchyAnalyser();

      for( int i = 0; i < argumentClasses.length; i++ ){
         if( classHierarchyAnalyser.checkIfIsChildOf( argumentClasses[i], artifactSubClass )) {
            argumentClasses[i] = artifactSubClass;
            replaceHappened = true;
         }
         //else replacedClasses[i] = argumentClasses[i];
      }
      
      return replaceHappened;
   }

   private ArtifactView<?> instantiateView( Constructor<?> viewConstructor, Class<?>[] argumentClasses, Object[] arguments ) 
      throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      
      ArtifactView<?> artifactView = (ArtifactView<?>) viewConstructor.newInstance( arguments );
      return artifactView;
   }
}