package com.processpuzzle.application.artifact;

import com.processpuzzle.application.domain.Application;
import com.processpuzzle.artifact.domain.CustomFormView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class ApplicationDataSheet_BaseData extends CustomFormView<ApplicationDataSheet> {

   private Application application = ((ApplicationDataSheet) parentArtifact).getApplication();
   
   public ApplicationDataSheet_BaseData( ApplicationDataSheet artifact, String viewName, ArtifactViewType type ) {
      super( artifact, viewName, type );
      // TODO Auto-generated constructor stub
   }

   @Override
   public void initializeView() {
      // TODO Auto-generated method stub
      
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
   
   public void setApplicationName(String name) {
      //application.???
   }
}
