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
