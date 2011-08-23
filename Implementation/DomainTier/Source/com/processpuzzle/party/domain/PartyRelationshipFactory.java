package com.processpuzzle.party.domain;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeFactory;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partytype.domain.PartyType;
import com.processpuzzle.persistence.domain.GenericFactory;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.util.domain.GeneralService;

public class PartyRelationshipFactory extends GenericFactory<PartyRelationship> {
   
   public PartyRelationshipFactory() {
      super();
   }

   public static PartyRelationship createPartyRelationship( PartyRelationshipType partyRelationshipType, Party client, Party supplier) {
      PartyRepository partyRep = (PartyRepository) ProcessPuzzleContext.getInstance().getRepository(PartyRepository.class);

      PartyRoleType clientPartyRoleType = partyRelationshipType.getSingleValidRolePair().getClientRoleType();
      PartyRoleType supplierPartyRoleType = partyRelationshipType.getSingleValidRolePair().getSupplierRoleType();

      PartyRole clientPartyRole = PartyRelationshipFactory.createPartyRole( clientPartyRoleType.getName(), client.getType(), clientPartyRoleType);
      PartyRole supplierPartyRole = PartyRelationshipFactory.createPartyRole(supplierPartyRoleType.getName(), supplier.getType(), supplierPartyRoleType);

      client.addPartyRole(clientPartyRole);
      supplier.addPartyRole(supplierPartyRole);

      PartyRelationship partyRelationship = null;
      if (partyRelationshipType.canFormRelationship(clientPartyRole, supplierPartyRole)) {
         if (partyRelationshipType.getName().equals("Replacement"))
            partyRelationship = new Replacement(partyRelationshipType.getName(), clientPartyRole, supplierPartyRole, partyRelationshipType);

         else
            partyRelationship = new PartyRelationship(partyRelationshipType.getName(), clientPartyRole, supplierPartyRole,
                  partyRelationshipType);
      }
      DefaultUnitOfWork work=new DefaultUnitOfWork(true);
      //partyRep.addPartyRelationship(work,partyRelationship);
      
      clientPartyRole.setPartyRelationship(partyRelationship);
      supplierPartyRole.setPartyRelationship(partyRelationship);

      partyRep.updateParty(work,client);
      partyRep.updateParty(work,supplier);
      work.finish();
      return partyRelationship;
   }

   public static void removePartyRelationship(Party subjectParty, String selectedPartyRoleId) {
      PartyRepository partyRep = (PartyRepository) ProcessPuzzleContext.getInstance().getRepository(PartyRepository.class);

      PartyRole ownRole = null;
      PartyRole partyRole = null;
      
      DefaultUnitOfWork work=new DefaultUnitOfWork(true);
      
      if (GeneralService.findCollectionItemByFieldName(subjectParty.getRoles(), "id", new Integer(selectedPartyRoleId)) != null) {
         Party ownParty = partyRep.findPartyByPartyRoleId(work,selectedPartyRoleId);
         ownRole=Party.getRoleFromPartyById(ownParty,new Integer(selectedPartyRoleId));
         Party party = partyRep.findOtherPartyByPartyRole(work,ownRole.getPartyRelationship().getId(), ownRole.getId());
         partyRole=Party.getRoleFromPartyById(party, new Integer(selectedPartyRoleId));
      } else {
         Party party = partyRep.findPartyByPartyRoleId(work,selectedPartyRoleId);
         partyRole=Party.getRoleFromPartyById(party,new Integer(selectedPartyRoleId));
         Party ownParty = partyRep.findOtherPartyByPartyRole(work,partyRole.getPartyRelationship().getId(), partyRole.getId());
         ownRole=Party.getRoleFromPartyById(ownParty, new Integer(selectedPartyRoleId));
      }

      PartyRelationship pr = ownRole.getPartyRelationship();
      
      ownRole.setPartyRelationship(null);
      partyRole.setPartyRelationship(null);
      
      try {
         subjectParty.removePartyRole(ownRole);
         partyRole.getParty().removePartyRole(partyRole);
      } catch (Exception e) {
         e.printStackTrace();
      }
      partyRep.updateParty(work, ownRole.getParty());
      partyRep.updateParty(work, partyRole.getParty());
//      partyRep.updatePartyRole(work,ownRole);
//      partyRep.updatePartyRole(work,partyRole);
//      partyRep.deletePartyRole(work,ownRole);
//      partyRep.deletePartyRole(work,partyRole);
//      
//      partyRep.deletePartyRelationship(work,pr);
      work.finish();
   }

   public static RuleSet createRuleSet() {
      RuleSet ruleSet = new RuleSet();
      return ruleSet;
   }

//   public static PartyRoleType createPartyRoleType(String partyRoleTypeName, PartyRoleConstraint partyRoleConstraint, RuleSet ruleSet) {
//      PartyRoleType partyRoleType = new PartyRoleType(partyRoleTypeName, partyRoleConstraint, ruleSet);
//      return partyRoleType;
//   }
//
//   public static PartyRoleType createPartyRoleType(String partyRoleTypeName, Set<PartyRoleConstraint> partyRoleConstraints, RuleSet ruleSet) {
//      PartyRoleType partyRoleType = new PartyRoleType(partyRoleTypeName, partyRoleConstraints, ruleSet);
//      return partyRoleType;
//   }
//
   public static PartyRole createPartyRole( String partyRoleName, PartyType partyType, PartyRoleType partyRoleType )  {
      PartyRole partyRole = null;
      if( partyRoleType.canPlayRole( partyType )) {
         partyRole = new PartyRole(partyRoleName, partyRoleType );
      }
      return partyRole;
   }

   public static PartyRelationshipType createPartyRelationshipType( String partyRelationshipTypeName, PartyRoleType clientRoleType, PartyRoleType supplierRoleTye, RuleSet ruleSet) {
      PartyRelationshipType partyRelationshipType = PartyRelationshipTypeFactory.create( partyRelationshipTypeName, clientRoleType, supplierRoleTye, ruleSet );
      return partyRelationshipType;
   }
   
//   public static PartyRelationshipType createPartyRelationshipType(String partyRelationshipTypeName, Set<PartyRelationshipConstraint> partyRelationshipConstraints,
//         RuleSet ruleSet) {
//      PartyRelationshipType partyRelationshipType = new PartyRelationshipType(partyRelationshipTypeName, partyRelationshipConstraints,
//            ruleSet);
//      return partyRelationshipType;
//   }
//
//   public static PartyRelationshipConstraint createPartyRelationshipConstraint(PartyRoleType clientRoleType, PartyRoleType supplierRoleType) {
//      return new PartyRelationshipConstraint(clientRoleType, supplierRoleType);
//   }

}
