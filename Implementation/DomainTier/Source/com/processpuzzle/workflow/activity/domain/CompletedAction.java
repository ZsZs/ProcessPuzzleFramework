package com.processpuzzle.workflow.activity.domain;

import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.util.domain.OPDomainStrings;

public class CompletedAction extends GenericAction {

   private ImplementedAction implementedVersion;

   public CompletedAction(Activity parentSubAction) {
      super(parentSubAction);
      statusKeyName = OPDomainStrings.ACTION_STATUS_COMPLETED;
   }

   public CompletedAction() {
      super();
   }

   public void transferState(ImplementedAction from) {
      from.setCompletedAction(this);
      this.implementedVersion = from;
   }

   public ImplementedAction getImplementedVersion() {
      return implementedVersion;
   }

   public void setImplementedVersion(ImplementedAction implementedVersion) {
      this.implementedVersion = implementedVersion;
   }

   public TimePeriod getTimePeriod() {
      return implementedVersion.getTimePeriod();
   }

   public TimePoint getRealBegin() {
      return implementedVersion.getRealBegin();
   }

   public void setRealBegin(TimePoint realBegin) {
      implementedVersion.setRealBegin(realBegin);
   }

   public TimePoint getRealEnd() {
      return implementedVersion.getRealEnd();
   }

   public void setRealEnd(TimePoint realEnd) {
      implementedVersion.setRealEnd(realEnd);
   }

   public TimePoint getProjectedBegin() {
      return implementedVersion.getProjectedBegin();
   }

   public TimePoint getProjectedEnd() {
      return implementedVersion.getProjectedEnd();
   }

   public void setProjectedBegin(TimePoint projectedBegin) {
      implementedVersion.setProjectedBegin(projectedBegin);
   }

   public void setProjectedEnd(TimePoint projectedEnd) {
      implementedVersion.setProjectedEnd(projectedEnd);
   }
}
