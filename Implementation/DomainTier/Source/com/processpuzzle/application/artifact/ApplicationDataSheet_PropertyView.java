package com.processpuzzle.application.artifact;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ApplicationDataSheet_PropertyView extends PropertyView<ApplicationDataSheet> {

   private Application application = ((ApplicationDataSheet) parentArtifact).getApplication();

   public ApplicationDataSheet_PropertyView( ApplicationDataSheet artifact, String name, ArtifactViewType viewType ) {
      super( artifact, name, viewType );
      // TODO Auto-generated constructor stub
   }

   public String getApplicationName() {
      return application.getApplicationName();
   }

   public String getApplicationVersion() {
      return application.getApplicationVersion();
   }

   public String getApplicationStatus() {
      return application.getExecutionStatus().toString();
   }

}
