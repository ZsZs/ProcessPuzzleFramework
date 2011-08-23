package com.processpuzzle.artifact.artifact;

import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ArtifactList_PrintView<A extends Artifact<?>> extends PrintView<A> {

   public ArtifactList_PrintView( A artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public String buildXml() {
      ArtifactList_ListView listView = (ArtifactList_ListView) parentArtifact.getAvailableViews().get( "ListView" );
      listView.query();
      List propertyViews = listView.getListedArtifactsPropertyViews();

      StringBuffer outputXml = new StringBuffer();

      outputXml.append( "<propertyviews>" );

      for( Iterator<PropertyView<A>> iter = propertyViews.iterator(); iter.hasNext(); ){
         PropertyView<A> pView = (PropertyView<A>) iter.next();

         outputXml.append( "<propertyview>" );

         outputXml.append( "<name>" + pView.getArtifactName() + "</name>" );
         outputXml.append( "<creationdate>" + pView.getCreationDate() + "</creationdate>" );
         outputXml.append( "<lastmodificationdate>" + pView.getLastModificationDate() + "</lastmodificationdate>" );
         outputXml.append( "<responsiblename>" + pView.getResponsibleName() + "</responsiblename>" );
         outputXml.append( "<lastmodifiername>" + pView.getLastModifierName() + "</lastmodifiername>" );

         outputXml.append( "</propertyview>" );
      }

      outputXml.append( "</propertyviews>" );
      log.info( outputXml.toString() );
      return outputXml.toString();
   }

   /*
    * (non-Javadoc)
    * @see com.itcodex.objectpuzzle.artifact.domain.ArtifactView#initializeView()
    */
   public void initializeView() {
   // TODO Auto-generated method stub

   }

}
