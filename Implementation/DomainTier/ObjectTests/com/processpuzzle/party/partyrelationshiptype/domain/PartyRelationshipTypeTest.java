package com.processpuzzle.party.partyrelationshiptype.domain;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRelationship;
import com.processpuzzle.party.domain.PartyRelationshipFactory;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.PersonFactory;
import com.processpuzzle.party.domain.RuleSet;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.party.partytype.domain.PartyTypeFactory;

public class PartyRelationshipTypeTest {
   private PartyRole partyRole;
   private PartyRoleType partyRoleType;
   private PartyRoleConstraint partyRoleConstraint;
   private PartyRelationship partyRelationship;
   private PartyRelationshipType partyRelationshipType;
   private PartyRelationshipConstraint partyRelationshipConstraint;
   private RuleSet ruleSet;
   private Person wife;
   private Person husband;
   private PartyType person;
   private ProcessPuzzleContext applicationContext;
   private PartyTypeFactory partyTypeFactory;
   private PersonFactory personFactory;

   @Before public void setUp() throws Exception {
      applicationContext = mock( ProcessPuzzleContext.class );
      partyTypeFactory = applicationContext.getEntityFactory( PartyTypeFactory.class );
      personFactory = applicationContext.getEntityFactory( PersonFactory.class );
      
      wife = personFactory.create( "Cambel", "Naomi" );
      husband = personFactory.create( "Nixon", "Richard" );
   }

   @After public void tearDown() throws Exception {}

   @Ignore @Test
   public void testCreateAPartyRoleConstraintWithTypeOfParty() {
      person = partyTypeFactory.create( "Person" );
      partyRoleConstraint = new PartyRoleConstraint( person );

      assertTrue("partyRoleConstraint is a PartyRoleConstraint object what was created with typeOfParty.",
            ((partyRoleConstraint != null) && (partyRoleConstraint.getTypeOfParty().equals("Person"))));
   }

   @Ignore @Test
   // PartyRelationshipConstraint
   public void testCreateAPartyRelationshipConstraintWithEmptyConstructor() {

      partyRelationshipConstraint = new PartyRelationshipConstraint();

      assertTrue("partyRelationshipConstraint is a PartyRelationshipConstraint object what was created with empty constructor.",
            (partyRelationshipConstraint != null));
   }

   @Ignore @Test
   public void testCreateAPartyRelationshipConstraintWithAClientRoleNameAndASuipplierRoleName() {

      partyRelationshipConstraint = new PartyRelationshipConstraint(new PartyRoleType("Husband"), new PartyRoleType("Wife"));

      assertTrue(
            "partyRelationshipConstraint is a PartyRelationshipConstraint object what was created with typeOfParty.",
            ((partyRelationshipConstraint != null) && (partyRelationshipConstraint.getClientRoleType().getName().equals("Husband")) && (partyRelationshipConstraint
                  .getSupplierRoleType().getName().equals("Wife"))));
   }

   @Ignore @Test
   public void testCreateAPartyRelationshipConstraintWithPartyRelationshipFactory() {

//      partyRelationshipConstraint = PartyRelationshipFactory.createPartyRelationshipConstraint( new PartyRoleType("Husband"), new PartyRoleType("Wife"));
//
//      assertTrue(
//            "partyRelationshipConstraint is a PartyRelationshipConstraint object what was created with PartyRelationshipFactory.",
//            ((partyRelationshipConstraint != null) && (partyRelationshipConstraint.getClientRoleType().getName().equals("Husband")) && (partyRelationshipConstraint
//                  .getSupplierRoleType().getName().equals("Wife"))));
   }

   @Ignore @Test
   // RuleSet
   public void testCreateARuleSetWithEmptyConstructor() {

      ruleSet = new RuleSet();

      assertTrue("ruleSet is a RuleSet object what was created with empty constructor.", (ruleSet != null));
   }

   @Ignore @Test
   public void testCreateARuleSetWithPartyRelationshipFactory() {

      ruleSet = PartyRelationshipFactory.createRuleSet();
      // ruleSet.setId(new Integer(1));

      assertTrue("RuleSet is a RuleSet object what was created with PartyRelationshipFactory.", (ruleSet != null));
   }

   @Ignore @Test
   // PartyRoleType
   public void testCreateAPartyRoleTypeWithEmptyConstructor() {

      partyRoleType = new PartyRoleType();

      assertTrue("partyRoleType is a PartyRoleType object what was created with empty constructor.", (partyRoleType != null));
   }

   @Ignore @Test
   public void testCreateAPartyRoleTypeWithNameAndAPartyRoleConstraintAndARuleSet() {
      partyRoleConstraint = new PartyRoleConstraint( person );
      ruleSet = PartyRelationshipFactory.createRuleSet();
      partyRoleType = PartyRoleTypeFactory.create("Wife", "bla bla", person, ruleSet);

      assertTrue(
            "partyRoleType is a PartyRoleType object what was created with name, a PartyRoleConstraint and a RuleSet.",
            ((partyRoleType != null) && (partyRoleType.getName().equals("Wife"))
                  && (((PartyRoleConstraint) partyRoleType.getValidPartyTypes().iterator().next()).getTypeOfParty().equals("Person")) && (partyRoleType
                  .getRequirementsForRole().getId().intValue() == (ruleSet.getId().intValue()))));
   }

   @Ignore @Test
   public void testCreateAPartyRoleTypeWithNameAndASetOfPartyRoleConstraintAndARuleSet() {
      partyRoleConstraint = new PartyRoleConstraint( person );
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      Set<PartyRoleConstraint> partyRoleConstraints = new HashSet<PartyRoleConstraint>();
      partyRoleConstraints.add(partyRoleConstraint);
      partyRoleType = PartyRoleTypeFactory.create("Wife", "blabla", person, ruleSet );

      assertTrue(
            "partyRoleType is a PartyRoleType object what was created with name, a Set of PartyRoleConstraint and a RuleSet.",
            ((partyRoleType != null) && (partyRoleType.getName().equals("Wife"))
                  && (((PartyRoleConstraint) partyRoleType.getValidPartyTypes().iterator().next()).getTypeOfParty().equals("Person")) && (partyRoleType
                  .getRequirementsForRole().getId().equals(ruleSet.getId()))));
   }

   @Ignore @Test
   public void testCreateAPartyRoleTypeWithPartyRelationshipFactory() {
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      partyRoleType = PartyRoleTypeFactory.create( "Wife", "Someone's wife.", person, ruleSet );

      assertTrue(
            "partyRoleType is a PartyRoleType object what was created with PartyRelationshipFactory.",
            ((partyRoleType != null) && (partyRoleType.getName().equals("Wife"))
                  && (((PartyRoleConstraint) partyRoleType.getValidPartyTypes().iterator().next()).getTypeOfParty().equals("Person")) && (partyRoleType
                  .getRequirementsForRole().getId().equals(ruleSet.getId()))));
   }

   @Ignore @Test
   public void testCreateAPartyRoleTypeWithPartyRelationshipFactoryAndSet() {
      partyRoleConstraint = new PartyRoleConstraint( person );
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      Set<PartyRoleConstraint> partyRoleConstraints = new HashSet<PartyRoleConstraint>();
      partyRoleConstraints.add( partyRoleConstraint );
      partyRoleType = PartyRoleTypeFactory.create("Wife", "bla bla", person, ruleSet );

      assertTrue(
            "partyRoleType is a PartyRoleType object what was created with PartyRelationshipFactory.",
            ((partyRoleType != null) && (partyRoleType.getName().equals("Wife"))
                  && (((PartyRoleConstraint) partyRoleType.getValidPartyTypes().iterator().next()).getTypeOfParty().equals("Person")) && (partyRoleType
                  .getRequirementsForRole().getId().equals(ruleSet.getId()))));
   }

   @Ignore @Test
   // PartyRelationshipType
   public void testCreateAPartyRelationshipTypeWithEmptyConstructor() {

      partyRelationshipType = new PartyRelationshipType();

      assertTrue("partyRelationshipType is a PartyRelationshipType object what was created with empty constructor.",
            (partyRelationshipType != null));
   }

   @Ignore @Test
   public void testCreateAPartyRelationshipTypeWithNameAndAPartyRelationshipConstraintAndARuleSet() {

      //partyRelationshipConstraint = PartyRelationshipFactory.createPartyRelationshipConstraint(new PartyRoleType("Husband"), new PartyRoleType("Wife"));
      partyRelationshipType = createPartyRelationshipType();

      assertTrue(
            "partyRelationshipType is a PartyRelationshipType object what was created with name, a PartyRelationshipConstraint and a RuleSet.",
            ((partyRelationshipType != null)
                  && (partyRelationshipType.getName().equals("Marriage"))
                  && (((PartyRelationshipConstraint) partyRelationshipType.getValidRolePairs().iterator().next()).getClientRoleType()
                        .getName().equals("Husband"))
                  && (((PartyRelationshipConstraint) partyRelationshipType.getValidRolePairs().iterator().next()).getClientRoleType()
                        .getName().equals("Husband")) && (partyRelationshipType.getRequirementsForRelationship().getId().intValue() == (ruleSet
                  .getId().intValue()))));
   }

   @Ignore @Test
   public void testCreateAPartyRelationshipTypeWithNameAndASetOfPartyRelationshipConstraintAndARuleSet() {
//      partyRelationshipConstraint = PartyRelationshipFactory.createPartyRelationshipConstraint(new PartyRoleType("Husband"), new PartyRoleType("Wife"));
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      Set<PartyRelationshipConstraint> partyRelationshipConstraints = new HashSet<PartyRelationshipConstraint>();
      partyRelationshipConstraints.add(partyRelationshipConstraint);
      partyRelationshipType = new PartyRelationshipType("Marriage", partyRelationshipConstraints, ruleSet);
      PartyRelationshipConstraint tmp;
      assertTrue(
            "partyRelationshipType is a PartyRelationshipType object what was created with name, a Set of PartyRelationshipConstraint and a RuleSet.",
            ((partyRelationshipType != null)
                  && (partyRelationshipType.getName().equals("Marriage"))
                  && ((tmp = (PartyRelationshipConstraint) partyRelationshipType.getValidRolePairs().iterator().next()).getClientRoleType()
                        .getName().equals("Husband")) && (tmp.getSupplierRoleType().getName().equals("Wife")) && (partyRelationshipType
                  .getRequirementsForRelationship().getId().intValue() == (ruleSet.getId().intValue()))));
   }

   @Ignore @Test
   public void testCreateAPartyRelationshipTypeWithPartyRelationshipFactory() {
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      partyRelationshipType = PartyRelationshipFactory.createPartyRelationshipType("Marriage", new PartyRoleType("Husband"), new PartyRoleType("Wife"), ruleSet);
      PartyRelationshipConstraint tmp;
      assertTrue("partyRelationshipType is a PartyRelationshipType object what was created with PartyRelationshipFactory.",
            ((partyRelationshipType != null)
                  && (partyRelationshipType.getName().equals("Marriage"))
                  && ((tmp = (PartyRelationshipConstraint) partyRelationshipType.getValidRolePairs().iterator().next()).getClientRoleType()
                        .getName().equals("Husband")) && (tmp.getSupplierRoleType().getName().equals("Wife")) && (partyRelationshipType
                  .getRequirementsForRelationship().getId().intValue() == (ruleSet.getId().intValue()))));
   }
   
   @Ignore @Test
   public void testCreateAPartyRelationshipTypeWithPartyRelationshipFactoryAndSet() {
      partyRelationshipType = createPartyRelationshipType();
      PartyRelationshipConstraint tmp;
      assertTrue("partyRelationshipType is a PartyRelationshipType object what was created with PartyRelationshipFactory.",
            ((partyRelationshipType != null)
                  && (partyRelationshipType.getName().equals("Marriage"))
                  && ((tmp = (PartyRelationshipConstraint) partyRelationshipType.getValidRolePairs().iterator().next()).getClientRoleType()
                        .getName().equals("Husband")) && (tmp.getSupplierRoleType().getName().equals("Wife")) && (partyRelationshipType
                  .getRequirementsForRelationship().getId().intValue() == (ruleSet.getId().intValue()))));
   }

   @Ignore @Test
   // PartyRole
   public void testCreateAPartyRoleWithEmptyConstructor() {

      partyRole = new PartyRole();

      assertTrue("partyRole is a PartyRole object what was created with empty constructor.", (partyRole != null));
   }

   @Ignore @Test
   public void testCreateAPartyRoleWithNameAndAPartyAndAPartyRoleType() {
      partyRoleConstraint = new PartyRoleConstraint( person );
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      Set<PartyRoleConstraint> partyRoleConstraints = new HashSet<PartyRoleConstraint>();
      partyRoleConstraints.add(partyRoleConstraint);
      partyRoleType = PartyRoleTypeFactory.create("Wife", "bla bla", person, ruleSet);
      Party<?> party = personFactory.create( "Benõ", "Bárczy" );
      partyRole = new PartyRole("Wife", partyRoleType);
      partyRole.setParty(party);

      assertTrue("partyRole is a PartyRole object what was created with name, a Party and a PartyRoleType.", ((partyRole != null)
            && (partyRole.getName().equals("Wife")) && (partyRole.getParty().getName().equals(wife.getName())) && (partyRole.getRoleType()
            .getName().equals("Wife"))));
      System.out.println("das");
   }

   @Ignore @Test
   public void testCreateAPartyRoleWithPartyRelationshipFactory() {
      partyRoleConstraint = new PartyRoleConstraint( person );
      ruleSet = PartyRelationshipFactory.createRuleSet();
//      ruleSet.setId(new Integer(1));
      Set<PartyRoleConstraint> partyRoleConstraints = new HashSet<PartyRoleConstraint>();
      partyRoleConstraints.add(partyRoleConstraint);
      partyRoleType = PartyRoleTypeFactory.create( "Wife", "bla bla", person, ruleSet );
      partyRole = new PartyRole("Wife", partyRoleType);
      partyRole.setParty(wife);

      assertTrue(
            "partyRole is a PartyRole object what was created with name, a Party and a PartyRoleType with PartyRelationshipFactory.",
            ((partyRole != null) && (partyRole.getName().equals("Wife")) && (partyRole.getParty().getName().equals(wife.getName())) && (partyRole
                  .getRoleType().getName().equals("Wife"))));
   }
   
   @Ignore @Test
   // PartyRelationship
   public void testCreateAPartyRelationshipWithEmptyConstructor() {

      partyRelationship = new PartyRelationship();

      assertTrue("partyRelationship is a PartyRelationship object what was created with empty constructor.", (partyRelationship != null));
   }

   @Ignore
   @Test
   public void testCreateAPartyRelationshipWithNameAndAClientPartyRoleAndASupplierPartyRole() {
      partyRoleConstraint = new PartyRoleConstraint( person );
      RuleSet clientRuleSet = PartyRelationshipFactory.createRuleSet();
//      clientRuleSet.setId(new Integer(1));
      RuleSet supplierRuleSet = PartyRelationshipFactory.createRuleSet();
//      supplierRuleSet.setId(new Integer(2));
      PartyRoleType clientPartyRoleType = PartyRoleTypeFactory.create("Husband", "blabla", person, clientRuleSet);
      PartyRoleType supplierPartyRoleType = PartyRoleTypeFactory.create("Wife", "bla bla", person, supplierRuleSet);

      ruleSet = PartyRelationshipFactory.createRuleSet();

      partyRelationshipType = PartyRelationshipFactory.createPartyRelationshipType("Marriage", new PartyRoleType("Husband"), new PartyRoleType("Wife"), clientRuleSet);

      PartyRole clientPartyRole = PartyRelationshipFactory.createPartyRole( "Husband", husband.getType(), clientPartyRoleType);
      PartyRole supplierPartyRole = PartyRelationshipFactory.createPartyRole("Wife", wife.getType(), supplierPartyRoleType);

      partyRelationship = new PartyRelationship("Marriage", clientPartyRole, supplierPartyRole, partyRelationshipType);

      // assertTrue("partyRelationship is a PartyRelationship object what was
      // created with name, a client, a supplier and a type.", ( (
      // partyRelationship != null ) && (
      // partyRelationship.getClient().getName().equals("Husband") ) && (
      // partyRelationship.getSupplier().getName().equals("Wife") ) && (
      // partyRelationship.getRelationshipType().getName().equals("Marriage") )
      // ) );
   }

   private PartyRelationshipType createPartyRelationshipType() {
      PartyRoleType husbandRole = PartyRoleTypeFactory.create( "Husband", "bla bla", person );
      PartyRoleType wifeRole = PartyRoleTypeFactory.create( "Wife", "bla bla", person );
      ruleSet = PartyRelationshipFactory.createRuleSet();
      PartyRelationshipType partyRelationshipType = PartyRelationshipTypeFactory.create( "Marriage", husbandRole, wifeRole, ruleSet );
      return partyRelationshipType;
   }

   // public void
   // testCreateAPartyRelationshipWithNameAndAClientPartyRoleAndASupplierPartyRoleWithPartyRelationshipFactory()
   // {
   // partyRoleConstraint = PartyRoleConstraint.PERSON;
   // RuleSet clientRuleSet = PartyRelationshipFactory.createRuleSet();
   // clientRuleSet.setId(new Integer(1));
   // RuleSet supplierRuleSet = PartyRelationshipFactory.createRuleSet();
   // supplierRuleSet.setId(new Integer(2));
   // PartyRoleType clientPartyRoleType =
   // PartyRelationshipFactory.createPartyRoleType("Husband",
   // partyRoleConstraint, clientRuleSet);
   // PartyRoleType supplierPartyRoleType =
   // PartyRelationshipFactory.createPartyRoleType("Wife", partyRoleConstraint,
   // supplierRuleSet);
   //		
   // partyRelationshipConstraint =
   // PartyRelationshipFactory.createPartyRelationshipConstraint(new
   // PartyRoleType("Husband"), new PartyRoleType("Wife"));
   // ruleSet = PartyRelationshipFactory.createRuleSet();
   //		
   // partyRelationshipType =
   // PartyRelationshipFactory.createPartyRelationshipType("Marriage",
   // partyRelationshipConstraint, clientRuleSet);
   //		
   // PartyRole clientPartyRole =
   // PartyRelationshipFactory.createPartyRole("Husband", husband.getClass(),
   // clientPartyRoleType);
   // PartyRole supplierPartyRole =
   // PartyRelationshipFactory.createPartyRole("Wife", wife.getClass(),
   // supplierPartyRoleType);
   //				
   // partyRelationship =
   // PartyRelationshipFactory.createPartyRelationship(partyRelationshipType,
   // clientPartyRole.getParty(), supplierPartyRole.getParty());
   //		
   // assertTrue("partyRelationship is a PartyRelationship object what was
   // created with name, a client, a supplier and a type.", ( (
   // partyRelationship != null ) && (
   // partyRelationship.getClient().getName().equals("Husband") ) && (
   // partyRelationship.getSupplier().getName().equals("Wife") ) && (
   // partyRelationship.getRelationshipType().getName().equals("Marriage") ) )
   // );
   // }
   //
   // public void testCanPlayRoleAndCanFromRelationshipInMarriageRelationship()
   // {
   // partyRoleConstraint = PartyRoleConstraint.PERSON;
   // RuleSet clientRuleSet = PartyRelationshipFactory.createRuleSet();
   // RuleSet supplierRuleSet = PartyRelationshipFactory.createRuleSet();
   // PartyRoleType clientPartyRoleType =
   // PartyRelationshipFactory.createPartyRoleType("Husband",
   // partyRoleConstraint, clientRuleSet);
   // PartyRoleType supplierPartyRoleType =
   // PartyRelationshipFactory.createPartyRoleType("Wife", partyRoleConstraint,
   // supplierRuleSet);
   //		
   // partyRelationshipConstraint =
   // PartyRelationshipFactory.createPartyRelationshipConstraint(new
   // PartyRoleType("Husband"), new PartyRoleType("Wife"));
   // ruleSet = PartyRelationshipFactory.createRuleSet();
   //		
   // partyRelationshipType =
   // PartyRelationshipFactory.createPartyRelationshipType("Marriage",
   // partyRelationshipConstraint, clientRuleSet);
   //		
   // PartyRole clientPartyRole =
   // PartyRelationshipFactory.createPartyRole("Husband", husband.getClass(),
   // clientPartyRoleType);
   // PartyRole supplierPartyRole =
   // PartyRelationshipFactory.createPartyRole("Wife", wife.getClass(),
   // supplierPartyRoleType);
   //				
   // partyRelationship =
   // PartyRelationshipFactory.createPartyRelationship(partyRelationshipType,
   // clientPartyRole.getParty(), supplierPartyRole.getParty());
   //		
   // assertTrue("The wife Party is can play role.",
   // (supplierPartyRole.getRoleType().canPlayRole(wife.getClass().getSimpleName())));
   // assertTrue("The husband Party is can play role.",
   // (clientPartyRole.getRoleType().canPlayRole(husband.getClass().getSimpleName())));
   // assertTrue("These Parties can form relationship.",
   // partyRelationship.getRelationshipType().canFormRelationship(partyRelationship.getClient(),
   // partyRelationship.getSupplier()));
   // }
   //
   // public void
   // testCanPlayRoleAndCanFromRelationshipInEmploymentRelationship() {
   // {
   //		
   // OrganizationName organizationName = new OrganizationName("IT Kodex Kft.");
   // Company company = new Company(organizationName);
   // PartyRoleConstraint partyRoleConstraintPerson =
   // PartyRoleConstraint.PERSON;
   // PartyRoleConstraint partyRoleConstraintCompany =
   // PartyRoleConstraint.COMPANY;
   // RuleSet clientRuleSet = PartyRelationshipFactory.createRuleSet();
   // RuleSet supplierRuleSet = PartyRelationshipFactory.createRuleSet();
   // PartyRoleType clientPartyRoleType =
   // PartyRelationshipFactory.createPartyRoleType("Employee",
   // partyRoleConstraintPerson, clientRuleSet);
   // PartyRoleType supplierPartyRoleType =
   // PartyRelationshipFactory.createPartyRoleType("Employer",
   // partyRoleConstraintCompany, supplierRuleSet);
   //		
   // partyRelationshipConstraint =
   // PartyRelationshipFactory.createPartyRelationshipConstraint(new
   // PartyRoleType("Employee"), new PartyRoleType("Employer"));
   //		
   // partyRelationshipType =
   // PartyRelationshipFactory.createPartyRelationshipType("Employment",
   // partyRelationshipConstraint, clientRuleSet);
   //		
   // PartyRole clientPartyRole =
   // PartyRelationshipFactory.createPartyRole("Employee", husband.getClass(),
   // clientPartyRoleType);
   // PartyRole supplierPartyRole =
   // PartyRelationshipFactory.createPartyRole("Employer", company.getClass(),
   // supplierPartyRoleType);
   //				
   // partyRelationship =
   // PartyRelationshipFactory.createPartyRelationship(partyRelationshipType,
   // clientPartyRole.getParty(), supplierPartyRole.getParty());
   //		
   // assertTrue("The company Party is can play role.",
   // (supplierPartyRole.getRoleType().canPlayRole(company.getClass().getSimpleName())));
   // assertTrue("The husband Party is can play role.",
   // (clientPartyRole.getRoleType().canPlayRole(husband.getClass().getSimpleName())));
   // assertTrue("These Parties can form relationship.",
   // partyRelationship.getRelationshipType().canFormRelationship(partyRelationship.getClient(),
   // partyRelationship.getSupplier()));
   // }

}
