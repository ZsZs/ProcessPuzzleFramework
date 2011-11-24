/*
Name: 
    - SettlementDataSheet_PropertyView

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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.processpuzzle.address.artifact.SettlementDataSheet.DistrictPresentationHelper;
import com.processpuzzle.address.domain.ZipCode;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class SettlementDataSheet_PropertyView extends PropertyView<SettlementDataSheet> {
   public SettlementDataSheet_PropertyView() {
      super( null, null, null );
   }

   public SettlementDataSheet_PropertyView( SettlementDataSheet artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String getSettlementName() { return parentArtifact.getSettlementName(); }

   public Collection<String> getZipCodes() { 
      return parentArtifact.getZipCodes(); 
   }
   
   public Collection<DistrictPresentationHelper> getDistricts() { 
      return parentArtifact.getDistricts(); 
   }

   @SuppressWarnings("unchecked")
   public String getData( String method, Map<String, String> parameters ) {
      if( method.equals( "getSettlementData" ) ){
         String responseXml = "<settlement id=\"" + ((SettlementDataSheet) parentArtifact).getId().toString() + "\">";
         responseXml += "<name>" + ((SettlementDataSheet) parentArtifact).getSettlement().getName() + "</name>";
         List<ZipCode> zipCodes = new ArrayList<ZipCode>( ((SettlementDataSheet) parentArtifact).getSettlement().getZipCodes() );
         Collections.sort( zipCodes );
         for( Iterator iter = zipCodes.iterator(); iter.hasNext(); ){
            ZipCode zipCode = (ZipCode) iter.next();
            responseXml += "<zipCode id=\"" + zipCode.getId().toString() + "\">" + zipCode.getZipCode().toString() + "</zipCode>";
         }
         responseXml += "</settlement>";
         return responseXml;
      }
      return null;
   }

   public String getId() {
      return ((SettlementDataSheet) this.parentArtifact).getId().toString();
   }

}
