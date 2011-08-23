/*
 * Created on 2005.07.19.
 */
package com.processpuzzle.workflow.activity.domain;

import hu.itkodex.commons.persistence.AggregateRoot;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.fundamental_types.domain.TimePeriod;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.inventory.domain.Holding;
import com.processpuzzle.inventory.domain.Location;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.resource.domain.Asset;
import com.processpuzzle.resource.resourcetype.domain.AssetType;
import com.processpuzzle.resource.resourcetype.domain.ConsumableResourceType;
import com.processpuzzle.util.domain.OPDomainStrings;
import com.processpuzzle.workflow.protocol.domain.IllegalDependencyException;
import com.processpuzzle.workflow.protocol.domain.Protocol;

public abstract class Action<A extends Action<?>> extends GenericEntity<A> implements AggregateRoot {
   private Suspension suspension;
   protected String name = null;
   protected String description;
   protected Location location;
   protected Protocol protocol;
   protected Party<?> owner;
   protected ActionStatus status;
   protected String priority;
   protected String designation;
   protected Party<?> performer;
   protected TimeValue estimatedExpenditure;
   protected Collection<ResourceAllocation> resourceAllocations = new HashSet<ResourceAllocation>();
   protected Collection<ActionReference> projectPIncludedIn = new HashSet<ActionReference>();
   protected Set<ActionEvent> actionEvents = new HashSet<ActionEvent>();
   protected Set<SimpleActionDependency> simpleDependentActions = new HashSet<SimpleActionDependency>();
   protected Set<SimpleActionDependency> simpleConsequentActions = new HashSet<SimpleActionDependency>();
   protected boolean mandatory = true;
   protected boolean notifyOnStart;
   protected boolean notifyOnFinish;
   protected boolean enforcedAcceptance;
   protected boolean executable;
   TimePeriod timePeriod = new TimePeriod( new TimePoint( new Date( System.currentTimeMillis() ) ), null );

   public Action( String name, Protocol theProtocol ) {
      this.name = name;
      this.protocol = theProtocol;
      status = new ProposedStatus( OPDomainStrings.ACTION_STATUS_PROPOSED );
   }

   public Action( String name ) {
      this( name, null );
   }

   public Action() {
      this( null, null );
   }

   public abstract TimePoint getProjectedBegin();

   public abstract void setProjectedBegin( TimePoint projectedBegin );

   public abstract TimePoint getProjectedEnd();

   public abstract void setProjectedEnd( TimePoint projectedEnd );

   public abstract TimePoint getRealBegin();

   public abstract void setRealBegin( TimePoint realBegin );

   public abstract TimePoint getRealEnd();

   public abstract void setRealEnd( TimePoint realEnd );

   public void addSimpleDependentAction( Action<?> a ) {
      SimpleActionDependency pDep = new SimpleActionDependency( a, this, a.isMandatory() );
      a.getSimpleConsequentActions().add( pDep );
      simpleDependentActions.add( pDep );
   }

   public Set<Action> simpledependentActions() {
      Set<Action> depActions = new HashSet<Action>();
      for( Iterator<SimpleActionDependency> iter = simpleDependentActions.iterator(); iter.hasNext(); ){
         SimpleActionDependency sDep = iter.next();
         depActions.add( sDep.getDependentAction() );
      }
      return depActions;
   }

   public Set<Action> simpleconsequentActions() {
      Set<Action> conActions = new HashSet<Action>();
      for( Iterator<SimpleActionDependency> iter = simpleConsequentActions.iterator(); iter.hasNext(); ){
         SimpleActionDependency sDep = iter.next();
         conActions.add( sDep.getConsequentAction() );
      }
      return conActions;
   }

   public void addDependentAction( Action<A> dependentAction, Plan<?> plan ) {
      ActionReference conRef = null;
      ActionReference depRef = null;
      try{
         conRef = findActionReference( plan, this );
         depRef = findActionReference( plan, dependentAction );
      }catch( IllegalDependencyException ex ){
         throw ex;
      }
      conRef.addDependentAction( depRef );
   }

   public Collection<Action<?>> getDependentActions( Plan<?> plan ) {
      ActionReference aRef = findActionReference( plan, this );
      return aRef.dependentActions();
   }

   public void addConsequentAction( Action<A> consequentAction, Plan<?> plan ) {
      ActionReference conRef = null;
      ActionReference depRef = null;
      try{
         conRef = findActionReference( plan, consequentAction );
         depRef = findActionReference( plan, this );
      }catch( IllegalDependencyException ex ){
         throw ex;
      }
      depRef.addConsequentAction( conRef );
   }

   public Collection<Action> getConsequentActions( Plan plan ) {
      ActionReference aRef = findActionReference( plan, this );
      return aRef.consequentActions();
   }

   public void addPlan( Plan<?> plan ) {
      ActionReference reference = new ActionReference( this, plan );
      projectPIncludedIn.add( reference );
      plan.getActions().add( reference );
   }

   public Collection<Plan> getPlans() {
      Collection<Plan> plans = new HashSet<Plan>();
      for( Iterator<ActionReference> iter = projectPIncludedIn.iterator(); iter.hasNext(); ){
         ActionReference aRef = iter.next();
         plans.add( aRef.getThePlan() );
      }
      return plans;
   }

   public void setPlans( Collection<Plan> plans ) {
      for( Iterator<Plan> iter = plans.iterator(); iter.hasNext(); ){
         Plan<?> plan = iter.next();
         ActionReference reference = new ActionReference( this, plan );
         projectPIncludedIn.add( reference );
         plan.getActions().add( reference );
      }
   }

   private ActionReference findActionReference( Plan<?> plan, Action<A> action ) {
      ActionReference match = null;
      for( Iterator<ActionReference> iter = action.getProjectPIncludedIn().iterator(); iter.hasNext(); ){
         ActionReference aRef = iter.next();
         if( aRef.getThePlan().equals( plan ) ){
            match = aRef;
            break;
         }
      }
      if( match == null )
         throw new IllegalDependencyException( "dependent and consequent must share the same plan" );
      else
         return match;
   }

   public ProcessPlan getProcessPlan() {
      Action<A> subject = this;
      if( subject.hasPlan() ){
         for( Iterator<Plan> iter = subject.getPlans().iterator(); iter.hasNext(); ){
            Plan<?> plan = iter.next();
            if( plan instanceof ProcessPlan ){
               ProcessPlan pPlan = (ProcessPlan) plan;
               return pPlan;
            }else{
               return plan.getProcessPlan();
            }
         }
      }
      return null;
   }

   public void changeStatus( ActionEvent event ) {
      setStatus( event.getNextStatus() );
      if( hasPlan() )
         notifyPlans( event );
   }

   public void notifyPlans( ActionEvent event ) {
      for( Iterator<Plan> iter = event.eventSource.getPlans().iterator(); iter.hasNext(); ){
         Plan<?> plan = iter.next();
         plan.processComponentActionEvent( event );
      }
   }

   public boolean hasPlan() {
      return(getPlans().size() > 0);
   }

   public boolean isLeaf() {
      return !(this instanceof Plan);

   }

   public void suspend( TimePeriod period ) {
      status.suspend( null, period );
   }

   public AbandonedAction abandone( Date effective, String cause ) {
      return status.abandone( null, effective, cause );
   }

   public void reactivate() {

   }

   public ResourceAllocation getResouceAllocation( String name ) {
      ResourceAllocation ra = null;
      for( Iterator<ResourceAllocation> iter = resourceAllocations.iterator(); iter.hasNext(); ){
         ResourceAllocation allocation = iter.next();
         if( allocation.getType().getName().equals( name ) )
            ra = allocation;
      }
      return ra;
   }

   public void allocatePartyRoleTypeGeneraly( PartyRoleType partyRoleType, Quantity theQuantity ) {
      GeneralResourceAllocation genResAll = new GeneralResourceAllocation( partyRoleType, theQuantity );
      resourceAllocations.add( genResAll );
   }

   public void allocateConsumableResourceGeneraly( ConsumableResourceType theCRType, Quantity theQuantity ) {
      GeneralResourceAllocation genResAll = new GeneralResourceAllocation( theCRType, theQuantity );
      resourceAllocations.add( genResAll );
   }

   public void allocateAssetGeneraly( AssetType theAssetType, Quantity theQuantity ) {
      GeneralResourceAllocation genResAll = new GeneralResourceAllocation( theAssetType, theQuantity );
      resourceAllocations.add( genResAll );
   }

   public void allocateSpecificConsumable( Holding theHolding, Quantity theQuantity ) {
      ConsumableResourceAllocation conResAll = new ConsumableResourceAllocation( theHolding, theQuantity );
      resourceAllocations.add( conResAll );
   }

   public void allocateAssetTemporally( Asset theAsset, TimeValue theValue ) {
      TemporalResourceAllocation tempResAll = new TemporalResourceAllocation( theAsset, theValue );
      resourceAllocations.add( tempResAll );
   }

   public boolean isMandatory() {
      return mandatory;
   }

   public void setMandatory( boolean mandatory ) {
      this.mandatory = mandatory;
   }

   public Suspension getSuspension() {
      return suspension;
   }

   public void setSuspension( Suspension suspension ) {
      this.suspension = suspension;
   }

   public Collection<ActionReference> getProjectPIncludedIn() {
      return projectPIncludedIn;
   }

   public void setProjectPIncludedIn( Collection<ActionReference> projectPIncludedIn ) {
      this.projectPIncludedIn = projectPIncludedIn;
   }

   public Location getLocation() {
      return location;
   }

   public void setLocation( Location location ) {
      this.location = location;
   }

   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public Protocol getProtocol() {
      return protocol;
   }

   public void setProtocol( Protocol protocol ) {
      this.protocol = protocol;
   }

   public Collection<ResourceAllocation> getResourceAllocations() {
      return resourceAllocations;
   }

   public Party<?> getOwner() {
      return owner;
   }

   public void setOwner( Party<?> performer ) {
      this.owner = performer;
   }

   public void setResourceAllocations( Collection<ResourceAllocation> resourceAllocations ) {
      this.resourceAllocations = resourceAllocations;
   }

   public TimePeriod getTimePeriod() {
      return timePeriod;
   }

   public void setTimePeriod( TimePeriod timePeriod ) {
      this.timePeriod = timePeriod;
   }

   public ActionStatus getStatus() {
      return status;
   }

   public void setStatus( ActionStatus status ) {
      this.status = status;
   }

   public String getPriority() {
      return priority;
   }

   public void setPriority( String priority ) {
      this.priority = priority;
   }

   public Set<SimpleActionDependency> getSimpleConsequentActions() {
      return simpleConsequentActions;
   }

   public void setSimpleConsequentActions( Set<SimpleActionDependency> simpleConsequentActions ) {
      this.simpleConsequentActions = simpleConsequentActions;
   }

   public Set<SimpleActionDependency> getSimpleDependentActions() {
      return simpleDependentActions;
   }

   public void setSimpleDependentActions( Set<SimpleActionDependency> simpleDependentActions ) {
      this.simpleDependentActions = simpleDependentActions;
   }

   public Collection<GeneralResourceAllocation> getGeneralResourceAllocations() {
      Collection<GeneralResourceAllocation> resourceAllications = new HashSet<GeneralResourceAllocation>();

      for( Iterator<ResourceAllocation> iter = resourceAllocations.iterator(); iter.hasNext(); ){
         ResourceAllocation allocation = iter.next();
         if( allocation instanceof GeneralResourceAllocation ){
            resourceAllications.add( (GeneralResourceAllocation) allocation );
         }
      }
      return resourceAllications;
   }

   public Collection<SpecificResourceAllocation> getSpecificResourceAllocations() {
      Collection<SpecificResourceAllocation> specificResourceAllocations = new HashSet<SpecificResourceAllocation>();

      for( Iterator<ResourceAllocation> iter = resourceAllocations.iterator(); iter.hasNext(); ){
         ResourceAllocation allocation = iter.next();
         if( allocation instanceof SpecificResourceAllocation ){
            specificResourceAllocations.add( (SpecificResourceAllocation) allocation );
         }
      }
      return specificResourceAllocations;
   }

   public Collection<TemporalResourceAllocation> getTemporalResourceAllocations() {
      Collection<TemporalResourceAllocation> temporalResourceAllocations = new HashSet<TemporalResourceAllocation>();

      for( Iterator<ResourceAllocation> iter = resourceAllocations.iterator(); iter.hasNext(); ){
         ResourceAllocation allocation = iter.next();
         if( allocation instanceof TemporalResourceAllocation ){
            temporalResourceAllocations.add( (TemporalResourceAllocation) allocation );
         }
      }
      return temporalResourceAllocations;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription( String description ) {
      this.description = description;
   }

   public String getDesignation() {
      return designation;
   }

   public void setDesignation( String designation ) {
      this.designation = designation;
   }

   public Boolean getExecutable() {
      return executable;
   }

   public void setExecutable( Boolean executable ) {
      this.executable = executable;
   }

   public boolean getEnforcedAcceptance() {
      return enforcedAcceptance;
   }

   public void setEnforcedAcceptance( boolean enforcedAcceptance ) {
      this.enforcedAcceptance = enforcedAcceptance;
   }

   public boolean getNotifyOnFinish() {
      return notifyOnFinish;
   }

   public void setNotifyOnFinish( boolean notifyOnFinish ) {
      this.notifyOnFinish = notifyOnFinish;
   }

   public boolean getNotifyOnStart() {
      return notifyOnStart;
   }

   public void setNotifyOnStart( boolean notifyOnStart ) {
      this.notifyOnStart = notifyOnStart;
   }

   public Party<?> getPerformer() {
      return performer;
   }

   public void setPerformer( Party<?> performer ) {
      this.performer = performer;
      allocateAssetTemporally( performer, null );
   }

   public TimeValue getEstimatedExpenditure() {
      return estimatedExpenditure;
   }

   public void setEstimatedExpenditure( TimeValue estimatedExpenditure ) {
      this.estimatedExpenditure = estimatedExpenditure;
   }

   public Set<Artifact<?>> getEffectedArtifacts() {
      Set<Artifact<?>> inputOutputArtifacts = new HashSet<Artifact<?>>();
      if( this instanceof Activity ){
         Activity thisActivity = (Activity) this;
         inputOutputArtifacts.addAll( thisActivity.getInputs() );
         inputOutputArtifacts.addAll( thisActivity.getOutputs() );
      }
      return inputOutputArtifacts;
   }

   public Set<ActionEvent> getActionEvents() {
      return actionEvents;
   }

   public void setActionEvents( Set<ActionEvent> actionEvents ) {
      this.actionEvents = actionEvents;
   }
}