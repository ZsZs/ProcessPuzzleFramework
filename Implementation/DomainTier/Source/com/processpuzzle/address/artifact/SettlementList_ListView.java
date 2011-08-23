package com.processpuzzle.address.artifact;

import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.util.Iterator;
import java.util.Map;

import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;

public class SettlementList_ListView extends ArtifactListView<SettlementList> {

   public void initializeView() {}

   public SettlementList_ListView( SettlementList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @SuppressWarnings("unchecked")
   public String getData( String method, Map parameters ) {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      StringBuffer responseXml = new StringBuffer();
      SettlementDataSheetRepository settlementDataSheetRepository = applicationContext.getRepository( SettlementDataSheetRepository.class );

      if( method.equals( "getSettlementsByStartName" ) ){
         String start = (String) parameters.get( "par0" );
         RepositoryResultSet<SettlementDataSheet> settlementsByStartName = settlementDataSheetRepository.findByNameStart( work, start );

         responseXml.append( "<settlementList id=\"" + parentArtifact.getId().toString() + "\">" );

         for( Iterator iter = settlementsByStartName.iterator(); iter.hasNext(); ){
            SettlementDataSheet s = (SettlementDataSheet) iter.next();
            responseXml.append( "<settlement id=\"" + s.getId() + "\">" );
            responseXml.append( s.getSettlement().getName() );
            responseXml.append( "</settlement>" );
         }

         responseXml.append( "</settlementList>" );

      }else if( method.equals( "getAllSettlements" ) ){
         RepositoryResultSet<SettlementDataSheet> settlements = settlementDataSheetRepository.findAll( work );
         work.finish();
         responseXml.append( "<settlementList id=\"" + parentArtifact.getId().toString() + "\">" );

         for( Iterator<SettlementDataSheet> iter = settlements.iterator(); iter.hasNext(); ){
            SettlementDataSheet settlementDataSheet = (SettlementDataSheet) iter.next();
            responseXml.append( "<settlement id=\"" + settlementDataSheet.getId() + "\">" );
            responseXml.append( settlementDataSheet.getSettlement().getName() );
            responseXml.append( "</settlement>" );
         }

         responseXml.append( "</settlementList>" );

      }
      return responseXml.toString();
   }
}
