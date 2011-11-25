/*
Name: 
    - ActionDataSheet_SpecificResourceAllocations 

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
 * Created on Oct 12, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.workflow.activity.domain.GeneralResourceAllocation;
import com.processpuzzle.workflow.activity.domain.SpecificResourceAllocation;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheet_SpecificResourceAllocations extends CustomFormView<ActionDataSheet<?>> {
   private String resourceType = "";
   private String resource = "";
   private Integer quantity = null;
   private String unit = "";

   public ActionDataSheet_SpecificResourceAllocations( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Collection<GeneralResourceAllocation> getGeneralResourceAllocations() {
      return parentArtifact.getGeneralResourceAllocations();
   }

   public Collection<SpecificResourceAllocation> getSpecificResourceAllocations() {
      return parentArtifact.getSpecificResourceAllocations();
   }

   public Collection<PartyRoleType> getPossibleResourceTypes() {
      List<PartyRoleType> partyRoleTypes = new ArrayList<PartyRoleType>();
      ActivityProtocol activity = (ActivityProtocol) ((ActionDataSheet<?>) parentArtifact).getAction().getProtocol();
      partyRoleTypes.add( activity.getPerformerRole() );
      return partyRoleTypes;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity( Integer quantity ) {
      this.quantity = quantity;
   }

   public String getResource() {
      return resource;
   }

   public void setResource( String resource ) {
      this.resource = resource;
   }

   public String getResourceType() {
      return resourceType;
   }

   public void setResourceType( String resourceType ) {
      this.resourceType = resourceType;
   }

   public String getUnit() {
      return unit;
   }

   public void setUnit( String unit ) {
      this.unit = unit;
   }

   @Override
   public void initializeView() {
   // TODO Auto-generated method stub

   }
}
