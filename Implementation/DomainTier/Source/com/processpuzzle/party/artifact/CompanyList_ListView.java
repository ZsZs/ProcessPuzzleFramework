package com.processpuzzle.party.artifact;

import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.party.domain.PartyRole;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.util.domain.BusinessObject;
import com.processpuzzle.util.domain.BusinessObjectRepository;

public class CompanyList_ListView extends ArtifactListView<CompanyList> {

   public CompanyList_ListView( CompanyList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void delete( String id ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      BusinessObjectRepository businessObjectRepository = applicationContext.getRepository( BusinessObjectRepository.class );
      PartyRepository partyRepository = applicationContext.getRepository( PartyRepository.class );
      CompanyDataSheetRepository companyDataSheetRepository = applicationContext.getRepository( CompanyDataSheetRepository.class );
      List<PartyRole> toDelownRoles = new ArrayList<PartyRole>();
      CompanyDataSheet companyDataSheet = companyDataSheetRepository.findById( work, new Integer( id ) );

      for( Iterator<PartyRole> iter = companyDataSheet.getCompany().getRoles().iterator(); iter.hasNext(); ){
         PartyRole ownRole = (PartyRole) iter.next();
         Party<?> party = partyRepository.findOtherPartyByPartyRole( work, ownRole.getPartyRelationship().getId(), ownRole.getId() );
         PartyRole partyRole = Party.getRoleFromPartyById( party, ownRole.getId() );
         // PartyRole partyRole =
         // partyRepository.findOtherPartyRole(work,ownRole.getPartyRelationship().getId().toString(),
         // ownRole.getId().toString());
         // PartyRelationship pr = ownRole.getPartyRelationship();
         try{
            toDelownRoles.add( ownRole );
            partyRole.getParty().removePartyRole( partyRole );

         }catch( Exception e ){
            e.printStackTrace();
         }

         partyRepository.updateParty( work, party );
         partyRepository.updateParty( work, ownRole.getParty() );
         // partyRepository.updatePartyRole(work,ownRole);
         // partyRepository.updatePartyRole(work,partyRole);
         // partyRepository.deletePartyRole(work,ownRole);
         // partyRepository.deletePartyRole(work,partyRole);

         // partyRepository.deletePartyRelationship(work,pr);

         ownRole.setPartyRelationship( null );
         partyRole.setPartyRelationship( null );

      }

      for( Iterator<PartyRole> iter = toDelownRoles.iterator(); iter.hasNext(); ){
         PartyRole pRole = iter.next();
         companyDataSheet.getCompany().getRoles().remove( pRole );
      }

      RepositoryResultSet<BusinessObject> bos = businessObjectRepository.findBusinessObjectsByCommissionerCompanyId( work, companyDataSheet
            .getCompany().getId().toString() );
      for( Iterator<BusinessObject> iter = bos.iterator(); iter.hasNext(); ){
         BusinessObject bo = iter.next();
         bo.setCommissionerCompany( null );
         businessObjectRepository.updateBusinessObject( work, bo );
      }

      artifactRepository.delete( work, companyDataSheet );
      work.finish();

   }

   public void initializeView() {
   // TODO Auto-generated method stub
   }
}
