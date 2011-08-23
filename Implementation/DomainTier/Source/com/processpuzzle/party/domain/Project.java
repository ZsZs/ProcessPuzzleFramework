package com.processpuzzle.party.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.party.partytype.domain.PartyType;

public class Project extends Organization {
   private HashMap<?, ?> responsibleOfRole = new HashMap();

   protected Project() {
      super();
   }

   public Project( OrganizationName name, PartyType projectType ) {
      super( name, projectType );
   }

   public ArrayList<?> getMembersByRole( String roleName ) {
      ArrayList membersByRole = new ArrayList();
      for( Iterator<?> iter = getRoles().iterator(); iter.hasNext(); ){}
      Collections.sort( membersByRole );
      return membersByRole;
   }

   public String getChiefByResponsible( String responsibleRoleName ) {
      for( Iterator<?> iter = this.responsibleOfRole.entrySet().iterator(); iter.hasNext(); ){
         Map.Entry element = (Map.Entry) iter.next();
         if( (element.getValue().toString().equals( responsibleRoleName )) && (element.getKey().toString().indexOf( "Chief" ) != -1) ){
            return element.getKey().toString();
         }
      }
      return null;
   }
}
