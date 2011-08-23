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
      // anAction = new ProposedAction("feladat");
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
      assertTrue( "megtaláltuk az allokált erõforrást1", flag );
   }

   @Test
   public void testAllocateAssetGenerally() { // AssetType theAssetType,
   // Quantity theQuantity
   // boolean flag2=false;
   // AssetType programozo = new AssetType("programozo");
   // Quantity mennyiseg = new Quantity(3,new Unit("darabszam"));
   // anAction.allocateAssetGeneraly(programozo,mennyiseg);
   // Collection allocations = anAction.getResourceAllocations();
   // Iterator anIterator = allocations.iterator();
   // while(anIterator.hasNext()){
   // ResourceAllocation allocation = (ResourceAllocation)anIterator.next();
   // if (allocation!=null)
   // if (allocation.getType().getName().equals(programozo.getName())
   // && (allocation.getQuantity().getAmount()==mennyiseg.getAmount()))
   // {
   // flag2=true; break;
   // }
   // assertTrue("megtaláltuk az allokált erõforrást2",flag2);
   // }
   }

   @Test
   public void testAllocateSpecificConsumable() { // Holding theHolding,
   // Quantity theQuantity
   // boolean flag3=false;
   // HoldingType hType = new HoldingType("molBenzin98");
   // Holding molBenzin98 = new Holding("benzin", hType);
   // Quantity mennyiseg = new Quantity(5,new Unit("kanna"));
   // molBenzin98.setBalance(mennyiseg);
   // anAction.allocateSpecificConsumable(molBenzin98,mennyiseg);
   // Collection allocations = anAction.getResourceAllocations();
   // Iterator anIterator = allocations.iterator();
   // while(anIterator.hasNext()){
   // ResourceAllocation allocation = (ResourceAllocation)anIterator.next();
   // if (allocation!=null)
   // if (allocation.getType().getName().equals("molBenzin98")
   // && (allocation.getQuantity().getAmount()==mennyiseg.getAmount()))
   // {
   // flag3=true; break;
   // }
   // assertTrue("megtaláltuk az allokált erõforrást3",flag3);
   // }
   }

   // public void testAllocateAssetTemporally(){ //asset,TimePeriod
   // boolean flag4=false;
   // Person thePerson = new Person(new PersonName());
   // Calendar c1 = Calendar.getInstance();
   // Calendar c2 = Calendar.getInstance();
   // c2.add(Calendar.HOUR,3);
   // TimePeriod interval = new TimePeriod(c1.getTime(),c2.getTime());
   // anAction.allocateAssetTemporally(thePerson,interval);
   // Collection allocations = anAction.getResourceAllocations();
   // Iterator i = allocations.iterator();
   // while(i.hasNext()){
   // ResourceAllocation allocation = (ResourceAllocation)i.next();
   // if (allocation!=null)
   // if (allocation.getType().getName().equals("at1")
   // && (allocation.getQuantity().getAmount()==interval.getHour()))
   // {
   // flag4=true; break;
   // }
   // assertTrue("megtaláltuk az allokált erõforrást4",flag4);
   // }
   // }
   @Test
   public void testGenericAction() {
      // assertNotNull("proposedaction has a generic action wich represents it's generic state",
      // proposedAction.getGeneric());
      assertTrue( "proposedaction' s generic type is GenericAction", proposedAction instanceof GenericAction );

   }

   @Test
   public void testImplementAction() {
   // assertTrue("pAction's state is proposed", proposedAction.getStatus()
   // instanceof ProposedStatus);
   // assertNull("pAction has no implementation yet",
   // proposedAction.getImplementedAction());
   // ImplementedAction iAction = proposedAction.implement();
   // assertNotNull("pAction now has an implementation", iAction);
   // assertEquals("it is the same action, but implemented",
   // iAction.getGeneric().getName(), proposedAction.getName());
   // assertNotNull("iAction has state", iAction.getGeneric().getStatus());
   // assertTrue("iAction's state is implemented",
   // iAction.getGeneric().getStatus() instanceof ImplementedStatus);
   }

   @Test
   public void testCompleteAction() {
   // ImplementedAction iAction = proposedAction.implement();
   // assertTrue("iActon's state is implemented",
   // iAction.getGeneric().getStatus() instanceof ImplementedStatus);
   // assertNull("iAction has not been completed yet",
   // iAction.getCompletedAction());
   // CompletedAction cAction = iAction.complete();
   // assertNotNull("after completion of iAction we can get the completedAction sobject", cAction);
   // assertTrue("completed action's state is CompletedState",
   // cAction.getGeneric().getStatus() instanceof CompletedStatus);
   }

   @Test
   public void testSuspensionForProposedAction() {
   // assertNull("pAction's suspension object is null",
   // proposedAction.getSuspension());
   // TimePoint startDate = new TimePoint( new
   // Date(System.currentTimeMillis()));
   // proposedAction.suspend(new TimePeriod( startDate, null));
   // assertNotNull("proposedAction's suspension is not null",
   // proposedAction.getSuspension());
   // assertEquals("proposedAction is suspended from startDate",
   // proposedAction.getSuspension().getPeriod().getBegin(), startDate);
   // assertTrue("proposedAction's state is suspended",
   // proposedAction.getStatus() instanceof SuspendedStatus);
   }

   @Test
   public void testSuspendActionForImplementedAction() {
   // ImplementedAction iAction = proposedAction.implement();
   // assertNull("iAction's suspension object is null",
   // iAction.getGeneric().getSuspension());
   // TimePoint endDate = new TimePoint( new Date(System.currentTimeMillis()));
   // iAction.suspend(new TimePeriod(null, endDate));
   // assertEquals("iAction is suspended from startDate",
   // iAction.getGeneric().getSuspension().getPeriod().getEnd(), endDate);
   // assertTrue("iAction's state is suspended",
   // iAction.getGeneric().getStatus() instanceof SuspendedStatus);
   }

   @Test
   public void testSuspensionForCompletedAction() {
   // CompletedAction completedAction = implementedAction.complete();
   // try {
   // completedAction.suspend(new TimePeriod(new TimePoint("2005.05.22", new
   // Locale("hu")), null));
   // assertTrue("exception was not thrown", false);
   // } catch (UnsupportedStateTransitionException ex) {
   // assertTrue("thrown exception's type is
   // UnsupportedStateTransitionException", ex instanceof
   // UnsupportedStateTransitionException);
   // }
   }

   // public void testAbandonActionForProposedAction() {
   // Date effective = new Date(System.currentTimeMillis());
   // String cause = "csak";
   // AbandonedAction abandonedAction = proposedAction.abandone(effective,
   // cause);
   // assertNotNull("abandonedAction has a proposedAction",
   // abandonedAction.getPAction());
   // assertTrue("abandonedAction's state is abandonedState",
   // abandonedAction.getGeneric().getStatus() instanceof AbandonedStatus);
   // }

   // public void testReactivation_for_SuspendedProposedAction() {
   // ProposedAction pAction = ActionFactory.createProposedAction("pAction");
   // pAction.suspend(new TimePeriod());
   // assertNotNull("pAction's suspension is not null",
   // pAction.getSuspension());
   // assertTrue("pAction's state is suspendedState", pAction.getStatus()
   // instanceof SuspendedStatus);
   // pAction.reactivate();
   // assertTrue("pAction's state is now the original", pAction.getStatus()
   // instanceof ProposedStatus);
   // assertNull("has no suspension object", pAction.getSuspension());
   // }

   // public void testReactivation_for_nonSuspendedProposedAction() {
   // ProposedAction pAction = ActionFactory.createProposedAction("pAction");
   // try {
   // pAction.reactivate();
   // assertTrue("exception was not thrown", false);
   // } catch (UnsupportedStateTransitionException ex) {
   // assertTrue("ex is an UnsupportedStateTransitionException", ex instanceof
   // UnsupportedStateTransitionException);
   // }
   // }

   @Test
   public void testHasPlan() {
   // ProposedAction pAction = ActionFactory.createProposedAction("pAction");
   // Plan plan = ActionFactory.createPlan("aPlan");
   // assertTrue("pAction has no plan", !pAction.hasPlan());
   // pAction.addPlan(plan);
   // assertTrue("pAction has at least one plan", pAction.hasPlan());
   }

   // public void testActionStateName() {
   // ProposedAction pAction = ActionFactory.createProposedAction("pAction");
   // assertEquals("pAction's state name is
   // proposed",pAction.getStatus().getName(),
   // OPDomainStrings.ACTION_STATUS_PROPOSED);
   // ImplementedAction iAction = pAction.implement();
   // assertEquals("iAction's state name is
   // implemented",iAction.getStatus().getName(),
   // OPDomainStrings.ACTION_STATUS_IMPLEMENTED);
   // ImplementedAction cAction = iAction.complete();
   // assertEquals("cAction's state name is
   // completed",cAction.getStatus().getName(),
   // OPDomainStrings.ACTION_STATUS_COMPLETED);
   // }

   // public void testAddDependentAction() {
   // ProposedAction action1 =new ProposedAction("action1");
   // ProposedAction action2 =new ProposedAction("action2");
   // ProposedAction action3 =new ProposedAction("action3");
   // action2.addDependentAction(action1);
   //		
   // assertTrue("we can find action1 in the dependents collection",
   // action2.getDependentActions().contains(action1));
   // action2.addDependentAction(action3);
   // assertEquals("action2 has two dependent actions",
   // action2.getDependentActions().size(), 2);
   // assertTrue("action1 consequent is action2",
   // action1.getConsequentActions().contains(action2));
   // }
   //	
   // public void testCompletionForConsequent() {
   // ProposedAction action1 =new ProposedAction("action1");
   // ProposedAction action2 =new ProposedAction("action2");
   // action2.addDependentAction(action1);
   // assertTrue("action1 consequent is action2",
   // action1.getConsequentActions().contains(action2));
   //
   // ImplementedAction iAction = action1.implement();
   // iAction.complete();
   // assertTrue("after completion of action1, action2 can be started",
   // action2.isImplementable());
   // }
   //	
   // public void testClonePropertiesForProposedToImplemented() {
   // ProposedAction pAction = new ProposedAction("Building");
   // ImplementedAction iAction=
   // ActionFactory.createImplementedFromProposed(pAction);
   // assertEquals("iAction has the same name", iAction.getName(),
   // pAction.getName());
   // }

}