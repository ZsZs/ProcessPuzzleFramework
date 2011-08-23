package com.processpuzzle.workflow.activity.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.util.domain.OPDomainStrings;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;
import com.processpuzzle.workflow.protocol.domain.CompositeProtocol;

public class Plan<P extends Plan<?>> extends Action<P> {
   private Set<ActionReference> actions = new HashSet<ActionReference>();
   private Set<ProcessPlan> subProcessPlans = new HashSet<ProcessPlan>();
   private PlanType type;
   private Plan<?> parent;

   public Plan( String name, CompositeProtocol protocol ) {
      super( name, protocol );
   }

   public Plan( String name ) {
      this( name, null );
   }

   protected Plan() {
      this( null, null );
   }

   public void addSubProcessPlan( ProcessPlan subProcessPlan ) {
      for( Iterator<ProcessPlan> i = subProcessPlans.iterator(); i.hasNext(); ){
         Action<?> a = i.next();
         if( a.getName().toLowerCase().equals( subProcessPlan.getName().toLowerCase() ) )
            return;
      }
      subProcessPlans.add( subProcessPlan );
      subProcessPlan.addPlan( this );
   }

   public void processComponentActionEvent( ActionEvent event ) {
      if( event instanceof ActionImplementationEvent ){
         if( event.getNextStatus() instanceof ImplementedStatus && !(this.getStatus() instanceof ImplementedStatus) ){
            ActionEvent theEvent = new ActionImplementationEvent( OPDomainStrings.ACTION_EVENT_IMPLEMETATION, this, new ImplementedStatus(
                  OPDomainStrings.ACTION_STATUS_IMPLEMENTED ) );
            theEvent.setOccuredOn( new TimePoint( new Date( System.currentTimeMillis() ) ) );
            this.getActionEvents().add( theEvent );
            changeStatus( theEvent );
         }
      }else if( event instanceof ActionEvent ){
         if( event.getNextStatus() instanceof CompletedStatus && !(this.getStatus() instanceof CompletedStatus) ){
            if( isComplete() ){
               ActionEvent theEvent = new ActionCompletitionEvent( OPDomainStrings.ACTION_EVENT_COMPLETITION, this, new CompletedStatus(
                     OPDomainStrings.ACTION_STATUS_COMPLETED ) );
               theEvent.setOccuredOn( new TimePoint( new Date( System.currentTimeMillis() ) ) );
               this.getActionEvents().add( theEvent );
               changeStatus( theEvent );
            }
         }
      }
   }

   public boolean isComplete() {
      boolean isComplete = true;
      for( Iterator<?> iter = getSubActions().iterator(); iter.hasNext(); ){
         Action<?> anAction = (Action<?>) iter.next();
         if( !(anAction.getStatus() instanceof CompletedStatus && anAction.isMandatory()) ){
            isComplete = false;
         }
      }
      return isComplete;
   }

   public boolean hasOneSubAction() {
      return(getSubActions().size() > 0);
   }

   public Collection<Action<?>> findLeafSubActions() {
      Collection<Action<?>> leafs = new HashSet<Action<?>>();
      for( Iterator<Action<?>> iter = this.getSubActions().iterator(); iter.hasNext(); ){
         Action<?> action = (Action<?>) iter.next();
         if( action.isLeaf() )
            leafs.add( action );
      }
      return leafs;
   }

   public Collection<Action<?>> findAllLeafActions() {
      Collection<Action<?>> matches = new HashSet<Action<?>>();
      Plan<?> temp = this;
      matches.addAll( temp.findLeafSubActions() );
      for( Iterator<?> iter = temp.getSubActions().iterator(); iter.hasNext(); ){
         Action<?> action = (Action<?>) iter.next();
         if( action instanceof Plan ){
            temp = (Plan<?>) action;
            matches.addAll( temp.findLeafSubActions() );
         }else{
            matches.add( action );
         }
         continue;
      }
      return matches;
   }

   public Action<?> findFirstActionByTimePoint() {
      Action<?> firstAction = null;
      TimePoint bigTime = new TimePoint( new GregorianCalendar( 3000, 1, 1 ).getTime() );
      for( Iterator<?> iter = getSubActions().iterator(); iter.hasNext(); ){
         Action<?> action = (Action<?>) iter.next();
         if( action.getProjectedBegin().getValue().before( bigTime.getValue() ) ){
            firstAction = action;
            bigTime = action.getProjectedBegin();
         }
      }
      return firstAction;
   }

   public Action<?> findLastActionByTimePoint() {
      Action<?> lastAction = null;
      TimePoint tinyTime = new TimePoint( new GregorianCalendar( 1000, 1, 1 ).getTime() );
      for( Iterator<?> iter = getSubActions().iterator(); iter.hasNext(); ){
         Action<?> action = (Action<?>) iter.next();
         if( action.getProjectedBegin().getValue().after( tinyTime.getValue() ) ){
            lastAction = action;
            tinyTime = action.getProjectedBegin();
         }
      }
      return lastAction;
   }

   public void updateDefaultPerformerOfRole( PartyRoleType partyRoleType, Party<?> party ) {
      for( Iterator<?> iter = findAllLeafActions().iterator(); iter.hasNext(); ){
         Action<?> action = (Action<?>) iter.next();
         ActivityProtocol activityProtocol = (ActivityProtocol) action.getProtocol();
         if( activityProtocol != null && activityProtocol.getPerformerRole().equals( partyRoleType ) )
            action.setPerformer( party );
      }
   }

   public void addSubAction( Action<?> action ) {
      ActionReference anActionReference = new ActionReference( action, this );
      action.getProjectPIncludedIn().add( anActionReference );
      actions.add( anActionReference );
   }

   // Getters and setters
   public List<PartyRoleType> getPartyRoleTypes() {
      List<PartyRoleType> partyRoleTypes = new ArrayList<PartyRoleType>();
      for( Iterator<?> iter = findAllLeafActions().iterator(); iter.hasNext(); ){
         Action<?> action = (Action<?>) iter.next();
         ActivityProtocol activityProtocol = (ActivityProtocol) action.getProtocol();
         if( activityProtocol != null && !partyRoleTypes.contains( activityProtocol.getPerformerRole() ) ){
            partyRoleTypes.add( activityProtocol.getPerformerRole() );
         }
      }

      return partyRoleTypes;
   }

   public Set<Action<?>> getSubActions() {
      Set<Action<?>> subActions = new HashSet<Action<?>>();
      for( Iterator<ActionReference> iter = actions.iterator(); iter.hasNext(); ){
         ActionReference actionReference = (ActionReference) iter.next();
         subActions.add( actionReference.getAction() );
      }
      return subActions;
   }

   @SuppressWarnings("unchecked")
   public <T extends Action<?>> Set<T> getSubActionsGeneric( Class<T> c ) {
      Set<T> subActions = new HashSet<T>();
      for( Iterator<ActionReference> iter = actions.iterator(); iter.hasNext(); ){
         ActionReference actionReference = (ActionReference) iter.next();
         if( c.isInstance( actionReference.getAction() ) )
            subActions.add( (T) actionReference.getAction() );
      }
      return subActions;
   }

   public Set<Plan<?>> getSubPlans() {
      Set<Plan<?>> subActions = new HashSet<Plan<?>>();
      for( Iterator<ActionReference> iter = actions.iterator(); iter.hasNext(); ){
         ActionReference actionReference = (ActionReference) iter.next();
         if( actionReference.getAction() instanceof Plan )
            subActions.add( (Plan<?>) actionReference.getAction() );
      }
      return subActions;
   }

   public Set<Activity> getSubActivities() {
      Set<Activity> subActions = new HashSet<Activity>();
      for( Iterator<ActionReference> iter = actions.iterator(); iter.hasNext(); ){
         ActionReference actionReference = (ActionReference) iter.next();
         if( actionReference.getAction() instanceof Plan )
            subActions.add( (Activity) actionReference.getAction() );
      }
      return subActions;
   }

   public Action<?> getSubActionByProtocolName( String name ) {
      Action<?> match = null;
      if( hasOneSubAction() ){
         Plan<?> temp = this;
         for( Iterator<?> iter = temp.getSubActions().iterator(); iter.hasNext(); ){
            Action<?> action = (Action<?>) iter.next();
            if( action.getProtocol().getName().equals( name ) ){
               match = action;
               break;
            }else{
               if( action instanceof Plan ){
                  Plan<?> plan = (Plan<?>) action;
                  temp = plan;
                  match = temp.getSubActionByProtocolName( name );
                  if( match != null )
                     break;
               }
            }
         }

      }
      return match;
   }

   public Collection<GeneralResourceAllocation> getGeneralPartyRoleTypeAllocations() {
      List<GeneralResourceAllocation> partyRoleTypeAllocations = new ArrayList<GeneralResourceAllocation>();
      for( Iterator<Action<?>> iter = findAllLeafActions().iterator(); iter.hasNext(); ){
         Action<?> action = iter.next();
         Collection<GeneralResourceAllocation> genrealResources = action.getGeneralResourceAllocations();
         for( Iterator<GeneralResourceAllocation> iterator = genrealResources.iterator(); iterator.hasNext(); ){
            GeneralResourceAllocation resourceAllocation = iterator.next();
            if( resourceAllocation.getPartyRoleType() != null )
               partyRoleTypeAllocations.add( resourceAllocation );
         }
      }
      return partyRoleTypeAllocations;
   }

   public Action<?> getLeafActionByName( String name ) {
      Collection<?> leafs = this.findAllLeafActions();
      for( Iterator<?> iterator = leafs.iterator(); iterator.hasNext(); ){
         Action<?> action = (Action<?>) iterator.next();
         if( action.getName().equals( name ) ){
            return action;
         }
      }
      return null;
   }

   public Set<ActionReference> getActions() {
      return actions;
   }

   public Set<ProcessPlan> getSubProcessPlans() {
      return subProcessPlans;
   }

   @Override
   public TimePoint getProjectedBegin() {
      Action<?> firstAction = findFirstActionByTimePoint();
      return firstAction.getProjectedBegin();
   }

   @Override
   public TimePoint getProjectedEnd() {
      Action<?> lastAction = findLastActionByTimePoint();
      return lastAction.getProjectedEnd();
   }

   @Override
   public TimePoint getRealBegin() {
      Action<?> action = findFirstActionByTimePoint();
      if( action != null )
         return action.getTimePeriod().getBegin();
      else
         return null;
   }

   @Override
   public TimePoint getRealEnd() {
      Action<?> lastAction = findLastActionByTimePoint();
      if( lastAction != null )
         return lastAction.getTimePeriod().getEnd();
      else
         return null;
   }

   public void setSubActions( Set<ActionReference> actions ) {
      this.actions = actions;
   }

   public void setSubProcessPlans( Set<ProcessPlan> subProcessPlans ) {
      this.subProcessPlans = subProcessPlans;
   }

   @Override
   public void setProjectedBegin( TimePoint projectedBegin ) {
      findFirstActionByTimePoint().setProjectedBegin( projectedBegin );
   }

   @Override
   public void setProjectedEnd( TimePoint projectedEnd ) {
      findLastActionByTimePoint().setProjectedEnd( projectedEnd );
   }

   @Override
   public void setRealBegin( TimePoint realBegin ) {
      Action<?> firstAction = findFirstActionByTimePoint();
      firstAction.setRealBegin( realBegin );
   }

   @Override
   public void setRealEnd( TimePoint realEnd ) {
      Action<?> lastAction = findLastActionByTimePoint();
      lastAction.setRealEnd( realEnd );
   }

   public PlanType getType() {
      return type;
   }

   public void setType( SeparatePlan type ) {
      this.type = type;
   }

   public void setType( PartPlan type, Plan<?> p ) {
      p.addSubAction( this );
      parent = p;
      this.type = type;
   }

   public Plan<?> getParentPlan() {
      return parent;
   }

   @SuppressWarnings( "unchecked" )
   protected void defineIdentityExpressions() {
      DefaultQueryContext context = new DefaultQueryContext();
      defaultIdentity = (DefaultIdentityExpression<P>) new PlanIdentity( context );
      identities.add( defaultIdentity );

   }

   @SuppressWarnings("unchecked")
   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      return defaultIdentity;
   }
}