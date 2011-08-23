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
