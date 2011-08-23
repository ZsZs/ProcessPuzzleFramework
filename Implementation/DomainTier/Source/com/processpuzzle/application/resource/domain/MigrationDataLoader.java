/*
 * Created on Feb 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.resource.domain;




/**
 * @author zsolt.zsuffa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class MigrationDataLoader extends DataLoader {

   public MigrationDataLoader() {
      super();
   }

   public void loadData() {
      super.loadData();
      
      setupDataSource();
      transformData();
   }

   public abstract void setupDataSource();

   public abstract void transformData();

}
