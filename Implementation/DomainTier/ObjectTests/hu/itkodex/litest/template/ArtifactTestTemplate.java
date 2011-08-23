package hu.itkodex.litest.template;


import hu.itkodex.commons.xml.ObjectXmlBinder;

import org.dom4j.Document;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactView;

public abstract class ArtifactTestTemplate<S extends Artifact<S>, F extends ArtifactTestFixture<S>> extends GenericTestTemplate<S, F, ArtifactTestEnvironment<S, F>>{
   private ObjectXmlBinder objectXmlBinder;
   
   protected ArtifactTestTemplate( String fixtureContainerConfigurationPath ) {
      super( fixtureContainerConfigurationPath, ArtifactTestEnvironment.class );
      objectXmlBinder = new ObjectXmlBinder(); 
   }
   
   protected Document marshallView( ArtifactView<S> artifactView,  String schemaPath ) {
      return objectXmlBinder.marshallObjectToXml( artifactView, schemaPath );
   }
   
   protected org.dom4j.Document postProcessExpectedDocument( org.dom4j.Document expectedXml ) {
      return expectedXml;
   }
   
   protected org.dom4j.Document readExpectedDocument( String expectedXmlPath ) {
      org.dom4j.Document expectedXml = objectXmlBinder.readDocumentFromClassPath( expectedXmlPath );
      expectedXml = postProcessExpectedDocument( expectedXml );
      
      return expectedXml;
   }
}
