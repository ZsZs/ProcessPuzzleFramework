package com.processpuzzle.party.artifact;

import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class CompanyList_PrintView extends PrintView<CompanyList> {

   public CompanyList_PrintView( CompanyList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      CompanyList_ListView listView = parentArtifact.findViewByClass( CompanyList_ListView.class );
      listView.query();
      List<?> propertyViews = listView.getListedArtifactsPropertyViews();
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<propertyviews>" );
      for( Iterator<?> iterator = propertyViews.iterator(); iterator.hasNext(); ){
         CompanyDataSheet_PropertyView propertyView = (CompanyDataSheet_PropertyView) iterator.next();
         outputXml.append( "<propertyview>" );
         outputXml.append( "<companyName>" + propertyView.getCompanyName() + "</companyName>" );
         outputXml.append( "<address>" + propertyView.getGeographicAddress() + "</address>" );
         outputXml.append( "</propertyview>" );
      }
      outputXml.append( "</propertyviews>" );
      return outputXml.toString();
   }

   public void initializeView() {
   // TODO Auto-generated method stub
   }
}
