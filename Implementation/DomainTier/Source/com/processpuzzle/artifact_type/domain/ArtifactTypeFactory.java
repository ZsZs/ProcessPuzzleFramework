/*
 * Created on Jul 9, 2006
 */
package com.processpuzzle.artifact_type.domain;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;
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

   public static ArtifactViewType createArtifactViewType( String name, String presentationUri ) {
      return new ArtifactViewType( name, presentationUri );
   }
   
   public static ArtifactPropertyViewType createPropertyViewType( String name, String presentationUri ) {
      return new ArtifactPropertyViewType( name, presentationUri );
   }
}
