/*
Name: 
    - ActionDataSheet_BaseData 

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
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.artifact;


import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.commons.persistence.RepositoryResultSet;
import com.processpuzzle.commons.persistence.UnitOfWork;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.resource.resourcetype.domain.ResourceTypeRepository;
import com.processpuzzle.util.domain.OPDomainStrings;
import com.processpuzzle.workflow.activity.domain.Action;
import com.processpuzzle.workflow.activity.domain.Activity;
import com.processpuzzle.workflow.activity.domain.ImplementedAction;
import com.processpuzzle.workflow.activity.domain.ProposedAction;

public class ActionDataSheet_BaseData extends CustomFormView<ActionDataSheet<?>> {
   protected PartyTypeRepository partyTypeRepository;
   protected ResourceTypeRepository resourceTypeRepository;

   public ActionDataSheet_BaseData( ActionDataSheet<?> artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      partyTypeRepository = applicationContext.getRepository( PartyTypeRepository.class );
      resourceTypeRepository = applicationContext.getRepository( ResourceTypeRepository.class );
   }

   public String getSubject() { return parentArtifact.getSubject(); }
   public void setSubject( String subject ) { parentArtifact.setSubject( subject ); }
   
   public String getDesignation() { return parentArtifact.getDesignation(); }
   public void setDesignation( String designation ) { parentArtifact.setDesignation( designation ); }

   public String getDescription() { return parentArtifact.getDescription(); }

   public void setDescription( String description ) { parentArtifact.setDescription( description ); }

   public String getProjectedBegin() { return parentArtifact.getProjectedBegin(); }
   public void setProjectedBegin( String begin ) throws ParseException { parentArtifact.setProjectedBegin( begin ); }

   public String getProjectedEnd() { return parentArtifact.getProjectedEnd(); }
   public void setProjectedEnd( String end ) throws ParseException { parentArtifact.setProjectedEnd( end ); }

   public String getRealBegin() { return parentArtifact.getRealBegin(); }
   public void setRealBegin( String begin ) throws ParseException { parentArtifact.setRealBegin( begin ); }

   public String getRealEnd() { return parentArtifact.getRealEnd(); }
   public void setRealEnd( String end ) throws ParseException { parentArtifact.setRealEnd( end ); }

   public String getPriority() { return parentArtifact.getPriority(); }
   public void setPriority( String priority ) { parentArtifact.setPriority( priority ); }

   public List<String> getPriorities() { return parentArtifact.getPriorities(); }

   public String getProtocol() { return parentArtifact.getProtocol(); }
   public void setProtocol( String protocol ) { parentArtifact.setProtocol( protocol ); }

   public String getStatus() { return parentArtifact.getStatus(); }
   public void setStatus( String status ) { parentArtifact.setStatus( status ); }

   public Boolean getExecutable() { return parentArtifact.getExecutable(); }

   public Party<?> getOwner() { return getOwner(); }
   public void setOwner( String ownerId ) { parentArtifact.setOwner( ownerId ); }

   public boolean getNotifyOnStart() { return parentArtifact.getNotifyOnStart(); }
   public void setNotifyOnStart( String notification ) { parentArtifact.setNotifyOnStart( notification ); }

   public boolean getNotifyOnFinish() { return parentArtifact.getNotifyOnFinish(); }
   public void setNotifyOnFinish( String notification ) { parentArtifact.setNotifyOnFinish( notification ); }

   public boolean getEnforcedAcceptance() { return parentArtifact.getEnforcedAcceptance(); }
   public void setEnforcedAcceptance( String enforcedAcceptance ) { parentArtifact.setEnforcedAcceptance( enforcedAcceptance ); }

   public Party<?> getPerformer() { return parentArtifact.getPerformer(); }
   public void setPerformer( String performerId ) { parentArtifact.setPerformer( performerId ); }

   public void performAction() {
      if( method != null && !method.equals( "" ) ){
         Action<?> action = ((ActionDataSheet<?>) parentArtifact).getAction();
         if( action instanceof Activity ){
            Activity subAction = (Activity) action;

            if( method.equals( "implement" ) ){
               if( subAction.isInStatus( OPDomainStrings.ACTION_STATUS_PROPOSED ) ){
                  ProposedAction pAction = ((ProposedAction) subAction.getCurrentInstance());
                  pAction.implement();
               }
            }else if( method.equals( "complete" ) ){
               if( subAction.isInStatus( OPDomainStrings.ACTION_STATUS_IMPLEMENTED ) ){
                  ImplementedAction iaction = ((ImplementedAction) subAction.getCurrentInstance());
                  iaction.complete();
               }
            }
         }
      }
   }

   public String getEstimatedExpenditure() { return parentArtifact.getEstimatedExpenditure(); }
   public void setEstimatedExpenditure( String expenditure ) { parentArtifact.setEstimatedExpenditure( expenditure ); }

   public String getExpenditureUnit() { return parentArtifact.getExpenditureUnit(); }
   public void setExpenditureUnit( String unit ) { parentArtifact.setExpenditureUnit( unit ); }

   public String getData( String method, Map<String, String> parameters ) {
      UnitOfWork work = new DefaultUnitOfWork( true );
      String responseXml = "";
      if( method.equals( "getPartiesByPartyTypeName" ) ){
         String selectedValue = (String) parameters.get( "par0" );
         PartyType partyType = partyTypeRepository.findById( work, new Integer( selectedValue ) );
         responseXml += "<parties>";
         RepositoryResultSet<Party> parties = partyRepository.findPartiesByPartyTypeName( work, partyType.getName() );
         work.finish();
         for( Iterator<Party> iter = parties.iterator(); iter.hasNext(); ){
            Party<?> party = iter.next();
            responseXml += "<partyName id=\"" + party.getId().toString() + "\">" + party.getPartyName().getName() + "</partyName>";
         }

         responseXml += "</parties>";
      }
      work.finish();
      return responseXml;
   }

   @SuppressWarnings("unchecked")
   public Collection<Party> getOwners() {
      UnitOfWork work = new DefaultUnitOfWork( true );
      RepositoryResultSet<Party> owners = partyRepository.findAllParty( work );
      work.finish();
      return (Collection<Party>) owners;
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
      
   }
}
