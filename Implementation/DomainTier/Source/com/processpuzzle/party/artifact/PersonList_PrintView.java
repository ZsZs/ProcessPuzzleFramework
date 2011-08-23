package com.processpuzzle.party.artifact;

import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class PersonList_PrintView extends PrintView<PersonList> {

   public PersonList_PrintView( PersonList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      PersonList_ListView listView = parentArtifact.findViewByClass( PersonList_ListView.class );
      listView.query();
      List<?> propertyViews = listView.getListedArtifactsPropertyViews();
      StringBuffer outputXml = new StringBuffer();
      outputXml.append( "<propertyviews>" );
      for( Iterator<?> iterator = propertyViews.iterator(); iterator.hasNext(); ){
         PersonDataSheet_PropertyView propertyView = (PersonDataSheet_PropertyView) iterator.next();
         outputXml.append( "<propertyview>" );
         outputXml.append( "<personName>" + propertyView.getPersonName() + "</personName>" );
         outputXml.append( "<address>" + propertyView.getGeographicAddress() + "</address>" );
         outputXml.append( "<telecom>" + propertyView.getTelecomAddress() + "</telecom>" );
         outputXml.append( "<email>" + propertyView.getEmailAddress() + "</email>" );
         outputXml.append( "</propertyview>" );
      }
      outputXml.append( "</propertyviews>" );
      return outputXml.toString();
   }

   public void initializeView() {}

}
