/*
Name: 
    - Activity 

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

package com.processpuzzle.workflow.activity.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.fundamental_types.domain.TimePoint;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;
import com.processpuzzle.util.domain.OPDomainStrings;
import com.processpuzzle.workflow.protocol.domain.Protocol;

public class Activity extends Action<Activity> {
   private Map<String, GenericAction> possibleLifecycleInstances = new HashMap<String, GenericAction>();
   private Set<Artifact<?>> inputs = new HashSet<Artifact<?>>();
   private Set<Artifact<?>> outputs = new HashSet<Artifact<?>>();

   protected Activity() {
      super();
   }

   public Activity( String name, Protocol theProtocol ) {
      super( name, theProtocol );
      possibleLifecycleInstances.put( OPDomainStrings.ACTION_STATUS_PROPOSED, new ProposedAction( this ) );
   }

   public Activity( String name ) {
      this( name, null );
   }

   public GenericAction getCurrentInstance() {
      return possibleLifecycleInstances.get( status.name );
   }

   public boolean isInStatus( String st ) {
      return status.getName().equals( st );
   }

   public boolean isImplementable() {
      ProposedAction proposedAction = (ProposedAction) possibleLifecycleInstances.get( OPDomainStrings.ACTION_STATUS_PROPOSED );
      return proposedAction.isImplementable();
   }

   public void canStart( boolean value ) {
      ProposedAction proposedAction = (ProposedAction) possibleLifecycleInstances.get( OPDomainStrings.ACTION_STATUS_PROPOSED );
      proposedAction.canStart( true );
   }

   public void addInputArtifact( Artifact<?> artifact ) {
      inputs.add( artifact );
   }

   public void addOutputArtifact( Artifact<?> artifact ) {
      outputs.add( artifact );
   }

   public TimePoint getProjectedBegin() {
      return getCurrentInstance().getProjectedBegin();
   }

   public TimePoint getProjectedEnd() {
      return getCurrentInstance().getProjectedEnd();
   }

   public TimePoint getRealBegin() {
      return getCurrentInstance().getRealBegin();
   }

   public TimePoint getRealEnd() {
      return getCurrentInstance().getRealEnd();
   }

   public void setProjectedBegin( TimePoint projectedBegin ) {
      getCurrentInstance().setProjectedBegin( projectedBegin );
   }

   public void setProjectedEnd( TimePoint projectedEnd ) {
      getCurrentInstance().setProjectedEnd( projectedEnd );
   }

   public void setRealBegin( TimePoint realBegin ) {
      getCurrentInstance().setRealBegin( realBegin );
   }

   public void setRealEnd( TimePoint realEnd ) {
      getCurrentInstance().setRealEnd( realEnd );
   }

   public Map<String, GenericAction> getPossibleLifecycleInstances() {
      return possibleLifecycleInstances;
   }

   public void setPossibleLifecycleInstances( Map<String, GenericAction> possibleLifecycleInstances ) {
      this.possibleLifecycleInstances = possibleLifecycleInstances;
   }

   public Set<Artifact<?>> getInputs() {
      return inputs;
   }

   public void setInputs( Set<Artifact<?>> inputs ) {
      this.inputs = inputs;
   }

   public Set<Artifact<?>> getOutputs() {
      return outputs;
   }

   public void setOutputs( Set<Artifact<?>> outputs ) {
      this.outputs = outputs;
   }

   protected void defineIdentityExpressions() {
   // TODO Auto-generated method stub

   }

   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

}
