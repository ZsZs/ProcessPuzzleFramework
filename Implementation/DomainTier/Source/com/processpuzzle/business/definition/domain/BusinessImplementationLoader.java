package com.processpuzzle.business.definition.domain;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class BusinessImplementationLoader extends BusinessDataLoader<BusinessImplementation> {
   private DefaultArtifactRepository artifactRepository;
   
   public BusinessImplementationLoader( String businessInstantiationPath ) {
      this( businessInstantiationPath, new DefaultResourceLoader() );
   }

   public BusinessImplementationLoader( String businessInstantiationPath, ResourceLoader resourceLoader ) {
      super( resourceLoader, businessInstantiationPath );
      this.resultInPersistentObjects = true;
      mappingKey = PropertyKeys.BUSINESS_IMPLEMENTATION_MAPPING.getDefaultKey();
      schemaKey = PropertyKeys.BUSINESS_IMPLEMENTATION_SCHEMA.getDefaultKey();
   }

   @Override protected void saveObjects( DefaultUnitOfWork work ) {
      artifactRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      
      for( Artifact<?> artifact : unmarshalledData.artifacts ) {
         artifactRepository.add( work, artifact );
      }
   }   
}
