/*
Name: 
    - ArtifactTypeFactory

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
 * Created on Jul 9, 2006
 */
package com.processpuzzle.artifact_type.domain;


import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class ArtifactTypeFactory extends GenericFactory<ArtifactType> {

   public ArtifactType createArtifactType( String typeName ) {
      return createArtifactType( typeName, null );
   }

   public ArtifactType createArtifactType( String typeName, String groupName ) {
      return this.createArtifactType( typeName, groupName, null );
   }
   
   public ArtifactType createArtifactType( String typeName, String groupName, Class<? extends Artifact<?>> artifactClass ) {
      ArtifactTypeGroup group = null;

      if( groupName != null ){
         ArtifactTypeGroupRepository repository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
         DefaultUnitOfWork work = new DefaultUnitOfWork( true );
         group = repository.findByName( work, groupName );
         work.finish();
      }

      ArtifactType artifactType = new ArtifactType( typeName, group, artifactClass );
      checkEntityIdentityCollition( artifactType.getDefaultIdentity() );
      return artifactType;
   }

   public ArtifactType createArtifactType( UnitOfWork work, String typeName, String groupName, Class<? extends Artifact<?>> artifactClass ) {
      ArtifactTypeGroup group = null;
      ArtifactTypeGroupRepository groupRepository = null;
      
      if( groupName != null ){
         groupRepository = applicationContext.getRepository( ArtifactTypeGroupRepository.class );
         group = groupRepository.findByName( work, groupName );
      }

      ArtifactType artifactType = new ArtifactType( typeName, group, artifactClass );
      checkEntityIdentityCollition( artifactType.getDefaultIdentity() );
      
      if( group != null ) {
         group.addType( artifactType );
         groupRepository.update( work, group );
      }
      return artifactType;
   }

   public static ArtifactViewType createArtifactViewType( String name, String presentationUri ) {
      return new ArtifactViewType( name, presentationUri );
   }
   
   public static ArtifactPropertyViewType createPropertyViewType( String name, String presentationUri ) {
      return new ArtifactPropertyViewType( name, presentationUri );
   }
}
