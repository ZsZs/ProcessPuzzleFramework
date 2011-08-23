package com.processpuzzle.business.definition.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.tools.MappingTool;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.processpuzzle.business.definition.domain.BusinessDefinition;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.workflow.protocol.domain.ProtocolDefinition;

public class CastorMappingGenerator {
   private static final String MAPPING_FILE_PATH = "classpath:com/processpuzzle/resource/integration/BusinessDefinitionMapping.xml";
   
   @Test public void generateMapping() throws MappingException, IOException {
      Resource mappingResource = new DefaultResourceLoader().getResource( MAPPING_FILE_PATH );
      Writer mappingWriter = new BufferedWriter( new FileWriter( mappingResource.getFile() ));
      
      MappingTool mappingTool = new MappingTool();
      mappingTool.setInternalContext( new org.castor.xml.BackwardCompatibilityContext() );
      
      try{
         mappingTool.addClass( BusinessDefinition.class );
         //mappingTool.addClass( ArtifactTypeGroup.class );
         mappingTool.addClass( PartyRelationshipType.class );
         mappingTool.addClass( PartyRoleType.class );
         
         mappingTool.addClass( ProtocolDefinition.class );
         //mappingTool.addClass( LifecycleProtocol.class );
         //mappingTool.addClass( LifecyclePhaseProtocol.class );
         //mappingTool.addClass( WorkflowDetailProtocol.class );
         //mappingTool.addClass( ActivityProtocol.class );
         
         mappingTool.write( mappingWriter );         
      }catch( Exception e ){
         e.printStackTrace();
      }
      
      mappingWriter.close();
   }
}
