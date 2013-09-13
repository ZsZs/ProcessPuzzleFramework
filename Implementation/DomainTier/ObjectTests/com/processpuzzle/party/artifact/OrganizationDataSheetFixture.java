package com.processpuzzle.party.artifact;

import com.processpuzzle.party.artifact.OrganizationUnitDataSheet;
import com.processpuzzle.party.artifact.OrganizationUnitDataSheetFactory;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.CompanyRepository;
import com.processpuzzle.party.domain.Organization;
import com.processpuzzle.party.domain.OrganizationUnit;
import com.processpuzzle.party.domain.OrganizationUnitFactory;
import com.processpuzzle.party.domain.PartyRelationship;
import com.processpuzzle.party.domain.PartyRelationshipFactory;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeFactory;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRelationshipTypeRepository;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleType;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeFactory;
import com.processpuzzle.party.partyrelationshiptype.domain.PartyRoleTypeRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.application.configuration.domain.ConfigurationSetUpException;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.artifact_type.domain.ArtifactTypeFactory;
import com.processpuzzle.artifact_type.domain.ArtifactTypeRepository;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroup;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupFactory;
import com.processpuzzle.artifact_type_group.domain.ArtifactTypeGroupRepository;

// CSAK A PIROS HIBAK LETTEK KISZEDVE!!! ATIRANDO!

public class OrganizationDataSheetFixture {
   public static String CIB_COMPANY_NAME = "CIB Rt.";
   public static String CIB_GODOLLOI_BRANCH_NAME = "Gödöllői fiók";
   public static String CIB_GYONGYOSI_BRANCH_NAME = "Gyöngyösi fiók";
   private static ProcessPuzzleContext config = null;
   private static DefaultArtifactRepository artifactRepository = null;
   private static OrganizationDataSheetFixture fixtureInstance = null;
   private static Organization organization = null;
   private static Company company = null;
   private static OrganizationUnit bankFiok1 = null;
   private static OrganizationUnit bankFiok2 = null;
   private static OrganizationUnitDataSheet organizationDataSheet = null;
   private static PartyRepository partyRepository;
   private static CompanyRepository companyRepository;
   private PartyRelationship prs1;
   private PartyRelationship prs2;
   private PartyRelationshipTypeRepository relationshipTypeRepository;
   private PartyRoleTypeRepository roleTypeRepository;
   private PartyRoleType companyRoleType;
   private PartyRoleType organizationUnitRoleType;
   private PartyRelationshipType organizationHierarchy;
   private ArtifactTypeRepository artifactTypeRepository;
   private ArtifactType type;
   private ArtifactTypeGroupFactory artifactTypeGroupFactory;
   private ArtifactTypeGroupRepository artifactTypeGroupRepository;
   private ArtifactTypeGroup group;
   private OrganizationUnitFactory organizationUnitFactory;
   private OrganizationUnitDataSheetFactory organizationUnitDataSheetFactory;
   private ArtifactTypeFactory artifactTypeFactory;

   public static OrganizationDataSheetFixture getInstance() {
      if (fixtureInstance == null) {
         return new OrganizationDataSheetFixture();
      }
      return fixtureInstance;
   }

   public void setUp() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
//      config = ProcessPuzzleContext.createInstance( ConfigurationConstants.CONFIGURATION_DESCRIPTOR_FILE );
      config = ProcessPuzzleContext.getInstance();
      try {
         config.setUp( Application.Action.start );
      } catch (ConfigurationSetUpException e) {
         e.printStackTrace();
      }
      
      organizationUnitFactory = config.getEntityFactory( OrganizationUnitFactory.class );
      organizationUnitDataSheetFactory = config.getEntityFactory( OrganizationUnitDataSheetFactory.class );

      artifactTypeFactory = config.getEntityFactory( ArtifactTypeFactory.class );
      group = artifactTypeGroupFactory.create( "Group" );
      artifactTypeGroupFactory = config.getEntityFactory( ArtifactTypeGroupFactory.class );
      artifactTypeGroupRepository = config.getRepository(ArtifactTypeGroupRepository.class );
      artifactTypeGroupRepository.addArtifactTypeGroup(work, group);

      type = artifactTypeFactory.createArtifactType("CompanyDataSheet", "Group");
      ArtifactViewType viewType = ArtifactTypeFactory.createArtifactViewType("name", "presentationUri");
      //viewType.setViewClassName("com.itcodex.objectpuzzle.party.artifact.CompanyDataSheet_PropertyView");
      type.addViewType(viewType);
      artifactTypeRepository = (ArtifactTypeRepository) config.getRepository(ArtifactTypeRepository.class);
      artifactTypeRepository.addArtifactType(work, type);
      // artifactTypeRepository.addArtifactViewType(viewType);
      artifactRepository = (DefaultArtifactRepository) config.getRepository(DefaultArtifactRepository.class);
      relationshipTypeRepository = (PartyRelationshipTypeRepository) config.getRepository(PartyRelationshipTypeRepository.class);
      roleTypeRepository = (PartyRoleTypeRepository) config.getRepository(PartyRoleTypeRepository.class);
      companyRepository = (CompanyRepository) config.getRepository(CompanyRepository.class);
      partyRepository = (PartyRepository) config.getRepository(PartyRepository.class);

      organizationDataSheet = organizationUnitDataSheetFactory.create( CIB_COMPANY_NAME );
      organization = organizationDataSheet.getOrganizationUnit();
      bankFiok1 = organizationUnitFactory.create(CIB_GODOLLOI_BRANCH_NAME);
      bankFiok2 = organizationUnitFactory.create(CIB_GYONGYOSI_BRANCH_NAME);

      companyRoleType = PartyRoleTypeFactory.create("SuperiorOrganization", "", company.getType() );
      roleTypeRepository.addPartyRoleType(work, companyRoleType);

      organizationUnitRoleType = PartyRoleTypeFactory.create("InferiorOrganization", "", organization.getType() );
      roleTypeRepository.addPartyRoleType(work, organizationUnitRoleType);

      organizationHierarchy = PartyRelationshipTypeFactory.create("OrganizationHierarchy", companyRoleType, organizationUnitRoleType);
      relationshipTypeRepository.add(work, organizationHierarchy);

      artifactRepository.add(work, organizationDataSheet);
      partyRepository.addOrganizationUnit(work, bankFiok1);
      partyRepository.addOrganizationUnit(work, bankFiok2);

      prs1 = PartyRelationshipFactory.createPartyRelationship(organizationHierarchy, /* */company, bankFiok1);
      prs2 = PartyRelationshipFactory.createPartyRelationship(organizationHierarchy, /* */company, bankFiok2);
//      partyRepository.addPartyRelationship(work, prs1);
//      partyRepository.addPartyRelationship(work, prs2);
      work.finish();

   }

   public void tearDown() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
//      try {
//         partyRepository.deletePartyRelationship(work, prs1);
//         partyRepository.deletePartyRelationship(work, prs2);
//      } catch (Exception e) {}
      partyRepository.deleteOrganizationUnit(work, bankFiok1);
      partyRepository.deleteOrganizationUnit(work, bankFiok2);
      // companyDataSheet.setCompany(null);
      artifactRepository.delete(work, organizationDataSheet);
      companyRepository.deleteCompany(work, company);
      relationshipTypeRepository.deletePartyRelationshipType(work, organizationHierarchy);
      roleTypeRepository.deletePartyRoleType(work, companyRoleType);
      roleTypeRepository.deletePartyRoleType(work, organizationUnitRoleType);
      config = null;
      artifactTypeGroupRepository.deleteArtifactTypeGroup(work, group);
      artifactTypeRepository.deleteArtifactType(work, type);

      artifactRepository = null;
      work.finish();
   }

   public static OrganizationUnitDataSheet getCompanyDataSheet() {
      return organizationDataSheet;
   }

   public static DefaultArtifactRepository getArtifactRepository() {
      return artifactRepository;
   }

   public static OrganizationUnit getBankFiok1() {
      DefaultUnitOfWork work = new DefaultUnitOfWork(true);
      OrganizationUnit oUnit = partyRepository.findOrganizationUnitById(work, bankFiok1.getId());
      work.finish();
      return oUnit;
   }
}