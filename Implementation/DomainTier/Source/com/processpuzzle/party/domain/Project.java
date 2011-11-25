/*
Name: 
    - Project

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
