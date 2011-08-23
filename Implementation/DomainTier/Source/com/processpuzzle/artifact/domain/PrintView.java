/*
 * Created on Apr 12, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public abstract class PrintView<A extends Artifact<?>> extends ArtifactView<A> {
   protected String xmlContent;
   protected String characterEntitiesDtd;

   public PrintView( A artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   public void initializeView() {
      // buildXml();
      convertXmlToFo();
      convertFoToPdf();
   }

   public String getCharacterEntitiesDtd() {
      return characterEntitiesDtd;
   }

   public void setCharacterEntitiesDtd( String characterEntitiesDtd ) {
      this.characterEntitiesDtd = characterEntitiesDtd;
   }

   public abstract String buildXml();

   protected void convertXmlToFo() {}

   protected void convertFoToPdf() {}
}