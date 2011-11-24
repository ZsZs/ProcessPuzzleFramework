/*
Name: 
    - SettlementDataSheet

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

import java.util.ArrayList;
import java.util.Collection;

import com.processpuzzle.address.domain.District;
import com.processpuzzle.address.domain.Settlement;
import com.processpuzzle.address.domain.SettlementRepository;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.artifact_type.domain.ArtifactType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultQueryContext;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class SettlementDataSheet extends Artifact<SettlementDataSheet> {
   private Settlement settlement = null;
   private SettlementRepository settlementRepository = null;

   protected SettlementDataSheet() {
      super();
   }

   public SettlementDataSheet( String artifactName, ArtifactType type, User creator, Settlement settlement ) {
      super( artifactName, type, creator );
      this.settlement = settlement;

      applicationContext = UserRequestManager.getInstance().getApplicationContext();
   }

   public Settlement getSettlement() {
      return settlement;
   }

   @Override
   protected void init() {
      super.init();
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
   }

   public void delete() {
      DefaultArtifactRepository repository = ProcessPuzzleContext.getInstance().getRepository( DefaultArtifactRepository.class );
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      repository.delete( work, this );
      work.finish();
   }

   public String getAsXml() {
      return super.getAsXml( SettlementDataSheet.class, settlement );
   }

   public String getSettlementName() {
      return settlement.getName();
   }

   public Collection<String> getZipCodes() {
      Collection<String> zipCodes = new ArrayList<String>();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
      settlement = settlementRepository.findById( work, settlement.getId() );
      for( ZipCode zip : settlement.getZipCodes() ){
         zipCodes.add( zip.getZipCode().toString() );
      }
      work.finish();
      return zipCodes;
   }

   public Collection<DistrictPresentationHelper> getDistricts() {
      Collection<DistrictPresentationHelper> districts = new ArrayList<DistrictPresentationHelper>();
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      settlementRepository = applicationContext.getRepository( SettlementRepository.class );
      settlement = settlementRepository.findById( work, settlement.getId() );

      for( District dist : settlement.getDistricts() ){
         System.out.println( "dist.getName : " + dist.getName() );
         StringBuffer sb = new StringBuffer();
         for( ZipCode zip : dist.getZipCodes() ){
            sb.append( zip.getZipCode().toString() ).append( " " );
         }
         DistrictPresentationHelper dph = new DistrictPresentationHelper( dist.getName(), sb.toString() );
         districts.add( dph );
      }

      work.finish();
      return districts;
   }

   public @Override
   SettlementDataSheetIdentity getDefaultIdentity() {
      DefaultQueryContext context = new DefaultQueryContext();
      context.addTextValueFor( "pathValue", path );
      SettlementDataSheetIdentity identity = new SettlementDataSheetIdentity( context );
      return identity;
   }

   public class DistrictPresentationHelper {

      String districtName, zipCodesSeparetedBySpaces;

      DistrictPresentationHelper( String districtName, String zipCodesSeparetedBySpaces ) {
         this.districtName = districtName;
         this.zipCodesSeparetedBySpaces = zipCodesSeparetedBySpaces;
      }

      public String getDistrinctName() {
         return districtName;
      }

      public String getZipCodesSeparetedBySpaces() {
         return zipCodesSeparetedBySpaces;
      }
   }

}