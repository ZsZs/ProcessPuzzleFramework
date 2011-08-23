package com.processpuzzle.resource.resourcetype.domain;

import com.processpuzzle.party.partytype.domain.Role;

public class ResourceTypeFactory {
   public static Role createRole( String name ) {
      return new Role( name );
   }

   public static ResourceType createResourceType( String name ) {
      return null;
   }
}
