/*
 * Created on Oct 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.workflow.activity.artifact;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.GeneralResourceAllocation;

public class ActionDataSheet_GeneralResourceAllocations<A extends ActionDataSheet<A>> extends CustomFormView<A> {
   private String roleId = null;
   private String quantity = null;
   private String unit = null;
   private String graId = null;
   private PartyRoleTypeRepository partyRoleTypeRepository = (PartyRoleTypeRepository) ProcessPuzzleContext.getInstance().getRepository(
         PartyRoleTypeRepository.class );
   private MeasurementContext unitRepository = ProcessPuzzleContext.getInstance().getMeasurementContext();

   public ActionDataSheet_GeneralResourceAllocations( A artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public Collection<GeneralResourceAllocation> getGeneralResourceAllocations() { 
      return parentArtifact.getGeneralResourceAllocations();
   }
   
   public Collection<PartyRoleType> getPerformerRoles() { 
      return parentArtifact.getPerformerRoles(); 
   }

   public void setPerformerRole( String roleId ) {
      this.roleId = roleId;
   }

   public void setQuantity( String quantity ) {
      this.quantity = quantity;
   }

   public void setUnit( String unit ) {
      this.unit = unit;
   }

   public void setGraId( String graId ) {
      this.graId = graId;
   }

   public void performAction() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      if( method.equals( "createGra" ) ){
         if( roleId != null && quantity != null && unit != null ){
            PartyRoleType pType = partyRoleTypeRepository.findPartyRoleTypeById( work, roleId );
            Quantity q = new Quantity( Double.valueOf( quantity ).doubleValue(), unitRepository.findUnitBySymbol( unit ) );
            GeneralResourceAllocation g = new GeneralResourceAllocation( pType, q );
            ((ActionDataSheet) parentArtifact).getAction().getResourceAllocations().add( g );
         }
      }else if( method.equals( "saveGra" ) ){
         if( roleId != null && quantity != null && unit != null ){
            for( Iterator<GeneralResourceAllocation> iter = getGeneralResourceAllocations().iterator(); iter.hasNext(); ){
               GeneralResourceAllocation g = iter.next();
               if( g.getId().toString().equals( graId ) ){
                  Quantity q = new Quantity( Double.valueOf( quantity ).doubleValue(), unitRepository.findUnitBySymbol( unit ) );
                  g.setQuantity( q );
               }
            }
         }
      }else if( method.equals( "removeGra" ) ){
         for( Iterator<GeneralResourceAllocation> iter = getGeneralResourceAllocations().iterator(); iter.hasNext(); ){
            GeneralResourceAllocation g = iter.next();
            if( g.getId().toString().equals( graId ) )
               parentArtifact.getAction().getResourceAllocations().remove( g );
         }
      }
      work.finish();
   }

   public String getData( String method, Map<String, String> parameters ) {
      StringBuffer responseXml = new StringBuffer();
      String selectedValue = (String) parameters.get( "par0" );
      if( !method.equals( "" ) ){
         if( method.equals( "getGeneralResourceAllocation" ) ){
            GeneralResourceAllocation gra = null;
            for( Iterator<GeneralResourceAllocation> iter = getGeneralResourceAllocations().iterator(); iter.hasNext(); ){
               GeneralResourceAllocation ra = iter.next();
               if( ra.getId().toString().equals( selectedValue ) )
                  gra = ra;
            }
            responseXml.append( "<generalResourceAllocation id=\"" + gra.getId().toString() + "\">" );
            responseXml.append( "<partyRoleType id=\"" + gra.getPartyRoleType().getId().toString() + "\" current=\"true\">" );
            responseXml.append( ProcessPuzzleContext.getInstance().getText(
                  "ui.pantokrator.partyRoleType." + gra.getPartyRoleType().getName(), getPreferredLanguage() ) );
            responseXml.append( "</partyRoleType>" );
            responseXml.append( "<quantity>" );
            responseXml.append( gra.getQuantity().getAmount() );
            responseXml.append( "</quantity>" );
            responseXml.append( "<unit id=\"" + gra.getQuantity().getUnit().getName() + "\" current=\"true\">" );
            responseXml.append( ProcessPuzzleContext.getInstance().getText(
                  "ui.pantokrator.unitType." + gra.getQuantity().getUnit().getName(), getPreferredLanguage() ) );
            responseXml.append( "</unit>" );
            responseXml.append( "</generalResourceAllocation>" );
            return responseXml.toString();
         }else{
            return responseXml.append( "<failure></failure>" ).toString();
         }
      }
      return null;
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
      
   }
}
