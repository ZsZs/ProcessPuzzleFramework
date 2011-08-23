/*
 * Created on 2005.07.19.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.domain;

import java.util.Date;

import com.processpuzzle.fundamental_types.domain.TimePoint;

/**
 * @author zsolt.zsuffa
 */
public class AbandonedAction extends GenericAction {
   private Date effective;
   private String cause;
   private ProposedAction proposedAction;
   private ImplementedAction implementedAction;
   
   public AbandonedAction(Activity subAction) {
      super(subAction);      
   }
   
   protected AbandonedAction() {
      super();      
   }
   
   public String getCause() {return cause;}
   public void setCause(String cause) {this.cause = cause;}

   public ImplementedAction getImplementedAction() {return implementedAction;}
   public void setImplementedAction(ImplementedAction action) {implementedAction = action;}

   public ProposedAction getProposedAction() {return proposedAction;}
   public void setProposedAction(ProposedAction action) {proposedAction = action;}

   public Date getEffective() {return effective;}
   public void setEffective(Date effective) {this.effective = effective;}

   public TimePoint getProjectedBegin() { return proposedAction.getProjectedBegin();}
   public void setProjectedBegin(TimePoint projectedBegin) { proposedAction.setProjectedBegin(projectedBegin); }

   public TimePoint getProjectedEnd() { return proposedAction.getProjectedEnd(); }
   public void setProjectedEnd(TimePoint projectedEnd) { proposedAction.setProjectedEnd( projectedEnd ); }

   public TimePoint getRealBegin() { return implementedAction.getRealBegin(); }
   public void setRealBegin(TimePoint realBegin) { implementedAction.setRealBegin(realBegin); }

   public TimePoint getRealEnd() { return implementedAction.getRealEnd(); }
   public void setRealEnd(TimePoint realEnd) { implementedAction.setRealEnd( realEnd ); }
}
