package hu.itkodex.litest.template;

import hu.itkodex.commons.rdbms.DatabaseSpy;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.resource.domain.DataLoader;

public class DataLoaderTestTemplate<L extends DataLoader> {
   protected ProcessPuzzleContext applicationContext;
   protected DatabaseSpy databaseSpy;
   protected L dataLoader;
   
   protected DataLoaderTestTemplate( String configurationDescriptorPath ) {
      
   }
}
