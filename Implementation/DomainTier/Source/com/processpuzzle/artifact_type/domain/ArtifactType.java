/*
Name: 
    - 

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
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;
import com.processpuzzle.util.domain.GeneralService;

@XmlRootElement( name = "artifactType" )
public class ArtifactType extends GenericEntity<ArtifactType> implements AssetType {
   protected @XmlAttribute( required = true ) @XmlID String name;
   protected @XmlAttribute String artifactClassName;
   protected @XmlAttribute String domainClassName;
   protected @XmlAttribute String instanceFolder;
   protected @XmlElement( namespace="http://www.processpuzzle.com/GlobalElements" ) String description;
   protected @XmlElement String caption;
   protected @XmlElement String baseUri;
   protected @XmlAttribute boolean isSystem = false;
   protected @XmlAttribute( name = "createOnStartUp" ) boolean createOnStartup = false;
   protected @XmlAttribute boolean isSingleton = false;
   protected @XmlAttribute boolean pessimisticLock = false;
   protected @XmlAttribute boolean isVersionControlled = false;
   protected @XmlAttribute boolean refreshOnDocumentActivation = false;
   protected @XmlAttribute boolean refreshOnViewActivation = false;
   protected @XmlElementWrapper( name = "availableViews" ) @XmlElementRef List<ArtifactViewType> availableViews = new ArrayList<ArtifactViewType>();
   private @XmlElementWrapper( name = "creationProperties" ) @XmlElement( name = "creationProperty" ) List<PropertyDefinition> propertyDefinitions = new ArrayList<PropertyDefinition>();
   private @XmlElementWrapper( name = "defaultAccessRights" ) @XmlElement( name = "accessRight" ) List<DefaultAccessRight> defaultAccessRights = new ArrayList<DefaultAccessRight>();
   private @XmlElementWrapper( name = "associatedMenuItems" ) @XmlElement( name = "menuItem" ) List<ArtifactMenu> associatedMenuItems = new ArrayList<ArtifactMenu>();
   private @XmlIDREF @XmlAttribute( name = "group" ) ArtifactTypeGroup group;

   //Constructors
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

   // Public accessors and mutators
   public void addViewType( ArtifactViewType aView ) {
      this.availableViews.add( aView );
   }

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

   @Override public int compareTo( ResourceType o ) {
      return 0;
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
               Class<?>[] argumentClasses = new Class[] { artifactClass, String.class, ArtifactViewType.class };
               Object[] arguments = new Object[] { artifact, viewName, aViewType };
               Constructor<?> viewConstructor = determineViewConstructor( artifactViewClass, argumentClasses );
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

   //Properties
   public String getArtifactClassName() { return artifactClassName; }
   public List<ArtifactViewType> getAvailableViews() { return availableViews; }
   public String getBaseUri() { return baseUri; }
   public String getCaption() { return caption; }
   public List<DefaultAccessRight> getDefaultAccessRights() { return defaultAccessRights; }
   public @Override @SuppressWarnings( "unchecked" ) <I extends DefaultIdentityExpression<ArtifactType>> I getDefaultIdentity() { defineIdentityExpressions(); return (I) defaultIdentity; }
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
   
   public @Override String getDescription() { return description; }
   public String getDomainClassName() { return domainClassName; }
   public ArtifactTypeGroup getGroup() { return group; }
   @Override public String getName() { return name; }
   
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

   public List<PropertyDefinition> getPropertyDefinitions() { return propertyDefinitions; }
   public ArtifactViewType getViewType( String viewTypeName ) {
      for( Iterator<ArtifactViewType> iter = availableViews.iterator(); iter.hasNext(); ){
         ArtifactViewType viewType = iter.next();
         if( viewType.getName().equals( viewTypeName ) )
            return viewType;
      }
      return null;
   }
   
   public boolean isCreateOnStartup() { return createOnStartup; }
   public boolean isSingleton() { return isSingleton; }
   public boolean isSystem() { return isSystem; }
   public boolean isVersionControlled() { return isVersionControlled; }
   public boolean isRefreshOnDocumentActivation() { return refreshOnDocumentActivation; }
   public boolean isRefreshOnViewActivation() { return refreshOnViewActivation; }
   public boolean isPessimisticLock() { return pessimisticLock; }
   
   public void setArtifactClassName( String artifactClassName ) { this.artifactClassName = artifactClassName; }
   public void setAvailableViews( List<ArtifactViewType> availableViews ) { this.availableViews = availableViews; }
   public void setBaseUri( String baseUri ) { this.baseUri = baseUri; }
   public void setCaption( String caption ) { this.caption = caption; }
   public void setCreateOnStartup( boolean createOnStartup ) { this.createOnStartup = createOnStartup; }
   public void setDefaultAccessRights( List<DefaultAccessRight> defaultAccessRights ) { this.defaultAccessRights = defaultAccessRights; }
   @Override public void setDescription( String description ) { this.description = description; }
   public void setDomainClassName( String domainClassName ) { this.domainClassName = domainClassName; }
   public void setGroup( ArtifactTypeGroup group ) { this.group = group; }
   public void setPessimisticLock( boolean pessimisticLock ) { this.pessimisticLock = pessimisticLock; }
   public void setPropertyDefinitions( List<PropertyDefinition> propertyDefinitions ) { this.propertyDefinitions = propertyDefinitions; }
   public void setSingleton( boolean isSingleton ) { this.isSingleton = isSingleton; }
   public void setSystem( boolean isSystem ) { this.isSystem = isSystem; }
   public void setVersionControlled( boolean isVersionControlled ) { this.isVersionControlled = isVersionControlled; }
   
   //Protected, private helper methods
   protected @Override void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      defaultIdentity = new ArtifactTypeIdentity( this.name, context );
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

   private ArtifactView<?> instantiateView( Constructor<?> viewConstructor, Class<?>[] argumentClasses, Object[] arguments ) 
         throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      
      ArtifactView<?> artifactView = (ArtifactView<?>) viewConstructor.newInstance( arguments );
      return artifactView;
   }
   
   private boolean tryToReplaceSpecificArtifactWithMoreGenericArtifact( Class<?>[] argumentClasses, @SuppressWarnings( "rawtypes" ) Class<? extends Artifact> artifactSubClass ) {
      boolean replaceHappened = false;
      RunTimeClassHierarchyAnalyser classHierarchyAnalyser = new RunTimeClassHierarchyAnalyser();

      for( int i = 0; i < argumentClasses.length; i++ ){
         if( classHierarchyAnalyser.checkIfIsChildOf( argumentClasses[i], artifactSubClass )) {
            argumentClasses[i] = artifactSubClass;
            replaceHappened = true;
         }
      }
      
      return replaceHappened;
   }

}