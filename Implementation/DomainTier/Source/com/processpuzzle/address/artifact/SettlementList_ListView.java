/*
Name: 
    - SettlementList_ListView

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
