package com.processpuzzle.business.definition.domain;

public enum ProcessPuzzleNamespaces {
   ARTIFACT_TYPE( "http://www.processpuzzle.com/ArtifactTypeDefinition" ),
   BUSINESS_DEFINITION( "http://www.processpuzzle.com/BusinessDefinitionsConfiguration" ),
   GLOBAL_ELEMENTS( "http://www.processpuzzle.com/GlobalElements" ),
   PARTY_ROLE_TYPE( "http://www.processpuzzle.com/PartyRelationshipTypeDefinition" ),
   PARTY_TYPE( "http://www.processpuzzle.com/PartyTypeDefinition" ),
   PROCESSPUZZLE( "http://www.processpuzzle.com" ),
   PROTOCOL( "http://www.processpuzzle.com/ProtocolDefinition" );
   
   private String namespace;
   
   private ProcessPuzzleNamespaces( final String namespace ){
      this.namespace = namespace;
   }
   
   public static String findNamespaceByUri( final String uri ){
      ProcessPuzzleNamespaces processPuzzleNamespace = ProcessPuzzleNamespaces.findByUri( uri );
      if( processPuzzleNamespace != null ) return processPuzzleNamespace.getValue();
      else return null;
   }
   
   public static ProcessPuzzleNamespaces findByUri( final String uri ){
      ProcessPuzzleNamespaces found = null;
      
      for( ProcessPuzzleNamespaces namespace : ProcessPuzzleNamespaces.values() ){
         if( namespace.getValue().equals( uri )){
            found = namespace;
            break;
         }
      }
      
      return found;
   }
   
   public String getValue(){ return namespace; }
   
}
