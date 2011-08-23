/*
 * Created on Sep 14, 2006
 */
package com.processpuzzle.workflow.activity.artifact;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.fundamental_types.quantity.domain.TimeValue;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PersonRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.util.domain.OPDomainStrings;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.ActionEvent;
import com.processpuzzle.workflow.activity.domain.ActionPriorities;
import com.processpuzzle.workflow.activity.domain.ActionReference;
import com.processpuzzle.workflow.activity.domain.Activity;
import com.processpuzzle.workflow.activity.domain.GeneralResourceAllocation;
import com.processpuzzle.workflow.activity.domain.ImplementedAction;
import com.processpuzzle.workflow.activity.domain.Plan;
import com.processpuzzle.workflow.activity.domain.ProposedAction;
import com.processpuzzle.workflow.activity.domain.ResourceAllocation;
import com.processpuzzle.workflow.activity.domain.SpecificResourceAllocation;
import com.processpuzzle.workflow.protocol.domain.ActivityProtocol;

/**
 * @author zsolt.zsuffa
 */
public class ActionDataSheet<A> extends Artifact<ActionDataSheet<A>> {
   protected Action<?> action;
   protected PartyRepository partyRepository;
   protected PersonRepository personRepository;
   protected MeasurementContext unitRepository;
   protected PartyRoleTypeRepository partyRoleTypeRepository;

   protected ActionDataSheet() {
      super();
   }

   protected ActionDataSheet( String name, ArtifactType type, User responsible ) {
      this( name, type, responsible, null );
   }

   protected ActionDataSheet( String name, ArtifactType type, User responsible, Action<?> action ) {
      super( name, type, responsible );
      this.action = action;
      partyRepository = applicationContext.getRepository( PartyRepository.class );
      personRepository = applicationContext.getRepository( PersonRepository.class );
      partyRoleTypeRepository = applicationContext.getRepository( PartyRoleTypeRepository.class );
      unitRepository = applicationContext.getMeasurementContext();
   }

   public Action<?> getAction() {
      return action;
   }

   public String getActionName() {
      return action.getName();
   }
   
   public String getAsXml() {
      return null;
   }

   public Collection<ResourceAllocation> getBookedResources() {
      if( action instanceof Activity ){
         Activity subAction = ((Activity) action);
         if( subAction != null && subAction.isInStatus( OPDomainStrings.ACTION_STATUS_PROPOSED ) )
            return ((ProposedAction) subAction.getCurrentInstance()).getBookedResources();
         else if( subAction != null && subAction.isInStatus( OPDomainStrings.ACTION_STATUS_IMPLEMENTED ) ){
            return ((ImplementedAction) subAction.getCurrentInstance()).getProposedAction().getBookedResources();
         }
      }
      return null;
   }
   
   public String getDescription() {
      return action.getDescription();
   }
   
   public String getDesignation() {
      return action.getDesignation();
   }

   public boolean getEnforcedAcceptance() {
      return action.getEnforcedAcceptance();
   }
   
   public String getEstimatedExpenditure() {
      return action.getEstimatedExpenditure().toString();
   }
   
   public Boolean getExecutable() {
      return action.getExecutable();
   }
   
   public Set<ActionEvent> getEvents() {
      return action.getActionEvents();
   }

   public String getExpenditureUnit() {
      return action.getEstimatedExpenditure().getUnit().getName();
   }
   
   public Collection<GeneralResourceAllocation> getGeneralResourceAllocations() {
      return action.getGeneralResourceAllocations();
   }

   public Map<String, List<Action<?>>> getNextActions() {
      return null;
   }
   
   public boolean getNotifyOnFinish() {
      return action.getNotifyOnFinish();
   }
   
   public boolean getNotifyOnStart() {
      return action.getNotifyOnStart();
   }

   public Party<?> getOwner() {
      return action.getOwner();
   }
   
   public String getOwnerName() {
      Party<?> responsible = action.getOwner();
      if( responsible == null )
         return applicationContext.getText( "ui.generic.action.undefined", getPrefferredLanguage() );
      else{
         return responsible.getPartyName().getName();
      }
   }
   
   public Party<?> getPerformer() {
      return action.getPerformer();
   }
   
   public Collection<PartyRoleType> getPerformerRoles() {
      List<PartyRoleType> partyRoleTypes = new ArrayList<PartyRoleType>();
      ActivityProtocol activity = (ActivityProtocol) action.getProtocol();
      partyRoleTypes.add( activity.getPerformerRole() );
      return partyRoleTypes;
   }
   
   public String getPriority() {
      String priority = action.getPriority();
      if( priority != null ) return priority;
      else return null;
   }

   public List<String> getPriorities() {
      return Arrays.asList( ActionPriorities.getStateDescriptions() );
   }
   
   public Map<String, List<Action<?>>> getPreviousActions() {
      Map<String, List<Action<?>>> previousActions = new HashMap<String, List<Action<?>>>();
      for( Iterator<ActionReference> iter = action.getProjectPIncludedIn().iterator(); iter.hasNext(); ){
         Plan plan = (Plan) iter.next().getThePlan();
         List<Action<?>> dependetActions = (List<Action<?>>) action.getDependentActions( plan );
         previousActions.put( plan.getName(), dependetActions );
      }
      return previousActions;
   }
   
   public String getProjectedBegin() {
      TimePoint projectedBegin = action.getProjectedBegin();
      if( projectedBegin != null ) return projectedBegin.getAsFormattedString();
      else return null;
   }

   public String getProjectedEnd() {
      TimePoint projectedEnd = action.getProjectedEnd();
      if( projectedEnd != null ) return projectedEnd.getAsFormattedString();
      else return null;
   }
   
   public String getProtocol() {
      return action.getProtocol().getName();
   }
   
   public String getRealBegin() {
      TimePoint realBegin = action.getRealBegin();
      if( realBegin != null ) return realBegin.getAsFormattedString();
      else return null;
   }
   
   public String getRealEnd() {
      TimePoint realEnd = action.getRealEnd();
      if( realEnd != null ) return realEnd.getAsFormattedString();
      else return null;
   }
   
   public Collection<Artifact<?>> getRelatedArtifacts() {
      Set<Artifact<?>> relatedArtifacts = new HashSet<Artifact<?>>();
      relatedArtifacts.addAll( super.getRelatedArtifacts() );
      relatedArtifacts.addAll( action.getEffectedArtifacts() );
      return relatedArtifacts;
   }

   public Collection<SpecificResourceAllocation> getSpecificResourceAllocations() {
      return action.getSpecificResourceAllocations();
   }
   
   public String getStatus() {
      return action.getStatus().getName();
   }
   
   public String getSubject() {
      return action.getProcessPlan().getSubject();
   }
   
   public Collection<ResourceAllocation> getUsedResources() {
      if( action instanceof Activity ){
         Activity subAction = ((Activity) action);
         if( subAction != null && subAction.isInStatus( OPDomainStrings.ACTION_STATUS_IMPLEMENTED ) )
            return ((ImplementedAction) subAction.getCurrentInstance()).getUsedResources();
         else if( subAction != null && subAction.isInStatus( OPDomainStrings.ACTION_STATUS_PROPOSED ) ){
            return ((ProposedAction) subAction.getCurrentInstance()).getImplementedAction().getUsedResources();
         }
      }
      return null;
   }
   
   public void setDescription( String description ) {
      action.setDescription( description );
   }

   public void setDesignation( String designation ) {
      action.setDesignation( designation );
   }

   public void setEnforcedAcceptance( String enforcedAcceptance ) {
      action.setEnforcedAcceptance( Boolean.valueOf( enforcedAcceptance ).booleanValue() );
   }

   public void setEstimatedExpenditure( String expenditure ) {
      action.setEstimatedExpenditure( new TimeValue( Double.valueOf( expenditure ).doubleValue(), null ) );
   }

   public void setExpenditureUnit( String unit ) {
      TimeValue actualExpenditure = action.getEstimatedExpenditure();
      TimeValue convertedExpenditure = (TimeValue) actualExpenditure.convertTo( unitRepository.findUnitBySymbol( unit ) );
      action.setEstimatedExpenditure( convertedExpenditure );
   }

   public void setNotifyOnFinish( String notification ) {
      action.setNotifyOnFinish( Boolean.valueOf( notification ).booleanValue() );
   }

   public void setNotifyOnStart( String notification ) {
      action.setNotifyOnStart( Boolean.valueOf( notification ).booleanValue() );
   }

   public void setOwner( String ownerId ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Party<?> party = partyRepository.findPartyById( work, new Integer( ownerId ) );
      action.setOwner( party );
      work.finish();
   }

   public void setPerformer( String performerId ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      Party<?> party = partyRepository.findPartyById( work, new Integer( performerId ) );
      if( party != null ) action.setPerformer( party );
      work.finish();
   }

   public void setPriority( String priority ) {
      if( priority != null && !priority.equals( "-1" )) action.setPriority( priority );
   }

   public void setProjectedBegin( String begin ) throws ParseException {
      action.setProjectedBegin( TimePoint.parse( begin, getDefaultLocale() ) );
   }
   
   public void setProjectedEnd( String end ) throws ParseException {
      action.setProjectedEnd( TimePoint.parse( end, getDefaultLocale() ) );
   }

   public void setProtocol( String protocol ) {
      action.getProtocol().setName( protocol );
   }

   public void setRealBegin( String begin ) throws ParseException {
      action.setRealBegin( TimePoint.parse( begin, getDefaultLocale() ) );
   }

   public void setRealEnd( String end ) throws ParseException {
      action.setRealEnd( TimePoint.parse( end, getDefaultLocale() ) );
   }

   public void setStatus( String status ) {
      action.getStatus().setName( status );
   }

   public void setSubject( String subject ) {
      action.getProcessPlan().setSubject( subject );
   }
}
