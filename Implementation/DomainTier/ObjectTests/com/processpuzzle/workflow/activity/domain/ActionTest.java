package com.processpuzzle.workflow.activity.domain;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.quantity.domain.Quantity;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.party.partytype.domain.PartyTypeFactory;
import com.processpuzzle.resource.resourcetype.domain.ConsumableResourceType;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ActionTest {

   private ProposedAction anAction;
   private ImplementedAction implementedAction;
   private ProposedAction proposedAction;

   @Before
   public void setUp() throws Exception {
      proposedAction = ActionFactory.createProposedAction( "pAction" );
      implementedAction = ActionFactory.createImplementedAction( "iAction" );
   }

   @After
   public void tearDown() throws Exception {
      anAction = null;
      proposedAction = null;
      implementedAction = null;
   }

   @Ignore
   @Test
   public void testAllocateConsumableResourceGeneraly() {
      boolean flag = false;
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      PartyTypeFactory partyTypeFactory = applicationContext.getEntityFactory( PartyTypeFactory.class );
      ConsumableResourceType benzin = new OilType( "benzin" );
      Quantity mennyiseg = new Quantity( 2, new Unit( "db", "darabszam" ) );
      ResourceAllocation foglalas = new GeneralResourceAllocation( benzin, mennyiseg );
      Set<ResourceAllocation> foglalasok = new HashSet<ResourceAllocation>();
      foglalasok.add( foglalas );
      proposedAction.setBookedResources( foglalasok );
      Collection<?> allocations = proposedAction.getBookedResources();
      Iterator<?> anIterator = allocations.iterator();
      while( anIterator.hasNext() ){
         ResourceAllocation allocation = (ResourceAllocation) anIterator.next();
         if( allocation != null )
            if( allocation.getType().getName().equals( benzin.getName() )
                  && (allocation.getQuantity().getAmount() == mennyiseg.getAmount()) ){
               flag = true;
               break;
            }
      }
      assertTrue( "megtal�ltuk az allok�lt er�forr�st1", flag );
   }

   @Test
   public void testAllocateAssetGenerally() { // AssetType theAssetType,
   }

   @Test
   public void testAllocateSpecificConsumable() { // Holding theHolding,
   }

   @Test
   public void testGenericAction() {
      assertTrue( "proposedaction' s generic type is GenericAction", proposedAction instanceof GenericAction );
   }

   @Test
   public void testImplementAction() {
   }

   @Test
   public void testCompleteAction() {
   }

   @Test
   public void testSuspensionForProposedAction() {
   }

   @Test
   public void testSuspendActionForImplementedAction() {
   }

   @Test
   public void testSuspensionForCompletedAction() {
   }


   @Test
   public void testHasPlan() {
   }

}