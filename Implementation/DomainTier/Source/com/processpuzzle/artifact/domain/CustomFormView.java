/*
 * Created on Jul 6, 2006
 */
package com.processpuzzle.artifact.domain;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public abstract class CustomFormView<A extends Artifact<?>> extends ArtifactView<A> {
   protected String method;
   protected String forward;

   // Constructors
   public CustomFormView( A artifact, String viewName, ArtifactViewType type ) {
      super( artifact, viewName, type );
   }

   //Public mutators
   public void performAction() {}
   
   // Properties
   public String getForward() { return forward; }
   public void setForward( String forward ) { this.forward = forward; }

   public String getMethod() { return method; }
   public void setMethod( String method ) { this.method = method; }
}
