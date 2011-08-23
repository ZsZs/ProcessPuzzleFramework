package com.processpuzzle.address.artifact;

import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.address.domain.ZipCodeFactory;
import com.processpuzzle.address.domain.ZipCodeRepository;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Party;
import com.processpuzzle.party.domain.PartyRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.util.domain.GeneralService;

public class SettlementDataSheet_BaseDataView extends CustomFormView<SettlementDataSheet> {
   private Integer zipCodeId = null;
   private String newZipCode = null;
   private List<String> toDelZipCodes = new ArrayList<String>();

   public SettlementDataSheet_BaseDataView( SettlementDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getSettlementName() {
      return ((SettlementDataSheet) parentArtifact).getSettlement().getName();
   }

/*   public Collection<ZipCode> getZipCodes() {
      return GeneralService.asList( parentArtifact.getSettlement().getZipCodes() );
   }*/

   public Collection<String> getZipCodes() {
      return parentArtifact.getZipCodes();
   }
   
   public void setZipCode( String zipCode ) {
      this.newZipCode = zipCode;
   }

   public void setZipCodes( String zipCodeId ) throws Exception {
      this.toDelZipCodes.add( zipCodeId );
   }

   public void setZipCodeId( String zipCodeId ) {
      this.zipCodeId = new Integer( zipCodeId );
   }

   public boolean isUnusedZipCode() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      PartyRepository partyRepository = (PartyRepository) ProcessPuzzleContext.getInstance().getRepository( PartyRepository.class );
      RepositoryResultSet<Party<?>> parties = partyRepository.findPartiesByAddressSettlementIdAndZipCodeId( work, ((SettlementDataSheet) parentArtifact).getSettlement().getId(), zipCodeId );
      if( (parties != null) && parties.size() != 0 ){
         work.finish();
         return false;
      }
      work.finish();
      return true;
   }

   public void performAction() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      ZipCodeFactory zipCodeFactory = ProcessPuzzleContext.getInstance().getEntityFactory( ZipCodeFactory.class );
      if( method.equals( "newZipcode" ) ){
         ((ZipCodeRepository) ProcessPuzzleContext.getInstance().getRepository( ZipCodeRepository.class )).addZipCode( work, zipCodeFactory
               .createZipCode( new Integer( newZipCode ), ((SettlementDataSheet) parentArtifact).getSettlement() ) );
      }else if( method.equals( "delZipcode" ) ){
         ZipCodeRepository zipCodeRepository = (ZipCodeRepository) ProcessPuzzleContext.getInstance().getRepository(
               ZipCodeRepository.class );
         for( Iterator<String> iter = toDelZipCodes.iterator(); iter.hasNext(); ){
            String toDelZipcodeId = iter.next();
            ZipCode zipCode = zipCodeRepository.findZipCodeById( work, new Integer( toDelZipcodeId ) );
            ((SettlementDataSheet) parentArtifact).getSettlement().getZipCodes().remove(
                  GeneralService.findCollectionItemByFieldName( ((SettlementDataSheet) parentArtifact).getSettlement().getZipCodes(), "id",
                        zipCode.getId() ) );

            DefaultArtifactRepository artifactRepository = (DefaultArtifactRepository) ProcessPuzzleContext.getInstance().getRepository(
                  DefaultArtifactRepository.class );
            artifactRepository.update( work, parentArtifact );
            zipCodeRepository.deleteZipCode( work, zipCode );
         }

      }
      work.finish();
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
      
   }
}
