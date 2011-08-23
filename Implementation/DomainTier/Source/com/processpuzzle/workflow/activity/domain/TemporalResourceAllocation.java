/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.resource.domain.Asset;

public class TemporalResourceAllocation extends SpecificResourceAllocation {
   private Asset asset;
   private TimePeriod timePeriod;
   private ImplementedAction implementedAction;

   public TemporalResourceAllocation( Asset theAsset, TimeValue timeValue ) {
      super( theAsset.getType(), timeValue );
      this.asset = theAsset;
   }

   public TemporalResourceAllocation( Asset theAsset, TimeValue timeValue, TimePeriod period ) {
      super( theAsset.getType(), timeValue );
      this.asset = theAsset;
      this.timePeriod = period;
   }

   public TemporalResourceAllocation() {}

   public ImplementedAction getImplementedAction() {
      return implementedAction;
   }

   public void setImplementedAction( ImplementedAction implementedAction ) {
      this.implementedAction = implementedAction;
   }

   public Asset getAsset() {
      return asset;
   }

   public void setAsset( Asset asset ) {
      this.asset = asset;
   }

   public TimePeriod getTimePeriod() {
      return timePeriod;
   }

   public void setTimePeriod( TimePeriod timePeriod ) {
      this.timePeriod = timePeriod;
   }

   public int hashCode() {
      int assetHash = 0;
      int timePeriodHash = 0;
      if( asset.getId() != null )
         assetHash = asset.hashCode();
      if( timePeriod != null )
         timePeriodHash = timePeriod.hashCode();
      return assetHash * 1000 + timePeriodHash;
   }

   @Override
   public <I extends DefaultIdentityExpression<ResourceAllocation>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

}