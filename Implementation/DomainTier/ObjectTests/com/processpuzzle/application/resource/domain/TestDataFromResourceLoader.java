package com.processpuzzle.application.resource.domain;

import com.processpuzzle.application.resource.domain.DataFromResourceLoader;

public class TestDataFromResourceLoader extends DataFromResourceLoader {

   public TestDataFromResourceLoader() {
      super();
   }
   
   public TestDataFromResourceLoader( String path ) {
      super( path );
   }

   @Override
   public void loadData() {
      super.loadData();
   }
}
