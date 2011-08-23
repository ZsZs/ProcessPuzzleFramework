package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.resource.domain.Asset;
import com.processpuzzle.resource.resourcetype.domain.ResourceType;

public class ResourceAllocationFactory {

   public ResourceAllocationFactory() {
      super();
      // TODO Auto-generated constructor stub
   }

   public static GeneralResourceAllocation createGeneralResourceAllocation(ResourceType theResourceType, Quantity theQuantity) {
      // TODO Auto-generated method stub
      return new GeneralResourceAllocation(theResourceType, theQuantity);
   }

   public static TemporalResourceAllocation createTemporalResourceAllocation(Asset asset, TimeValue timeValue) {
      // TODO Auto-generated method stub
      return new TemporalResourceAllocation(asset, timeValue);
   }
}
