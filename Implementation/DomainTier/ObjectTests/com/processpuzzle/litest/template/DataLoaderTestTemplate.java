package com.processpuzzle.litest.template;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.DataLoader;
import com.processpuzzle.commons.rdbms.DatabaseSpy;

public class DataLoaderTestTemplate<L extends DataLoader> {
   protected ProcessPuzzleContext applicationContext;
   protected DatabaseSpy databaseSpy;
   protected L dataLoader;
   
   protected DataLoaderTestTemplate( String configurationDescriptorPath ) {
      
   }
}
