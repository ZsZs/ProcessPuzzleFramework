package com.processpuzzle.application.configuration.control;


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.domain.Application;

//unused
public class OPFrameworkConfiguration extends ProcessPuzzleContext{
  public static final String HIBERNATE="hibernate";
  public static final String INMEMORY="inMemory";
  
  protected OPFrameworkConfiguration( Application application, String descriptorPath ){
     super( application, descriptorPath );
  }
}
