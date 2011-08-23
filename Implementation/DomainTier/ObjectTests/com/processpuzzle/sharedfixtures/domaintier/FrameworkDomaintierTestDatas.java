package com.processpuzzle.sharedfixtures.domaintier;

import com.processpuzzle.application.resource.domain.HardCodedDataLoader;

public class FrameworkDomaintierTestDatas extends HardCodedDataLoader {

   public FrameworkDomaintierTestDatas() {
      resultInPersistentObjects = true;      
   }
   
   @Override
   public void loadData() {
      super.loadData();
   }
}
