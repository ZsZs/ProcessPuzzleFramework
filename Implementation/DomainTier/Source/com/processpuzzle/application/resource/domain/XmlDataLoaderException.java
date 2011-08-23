package com.processpuzzle.application.resource.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;

public class XmlDataLoaderException extends DataLoaderException {
   private static final long serialVersionUID = 1L;
   private static String defaultMessagePattern = "Problem ''{0}'' occured during loading data from resource: ''{1}''";
   private XmlDataLoader dataLoader;
   private String problem;
   
   public XmlDataLoaderException( XmlDataLoader loader, String problem ) {
      this(loader, problem, null );
   }

   public XmlDataLoaderException( XmlDataLoader loader, String problem, Throwable cause) {
      super( ExceptionHelper.defineMessage( XmlDataLoaderException.class, 
                                            new Object[] { problem, loader.getResourcePath() }, 
                                            defaultMessagePattern ),
             cause );
      this.dataLoader = loader;
      this.problem = problem;
   }

   public XmlDataLoaderException(Object[] objects, Throwable cause) {
      super(objects, cause);
   }

   public XmlDataLoaderException(ExceptionHelper helper, Throwable cause) {
     super(helper, cause);
   }
   
   public XmlDataLoader getDataLoader() { return dataLoader; }
   public String getProblem() { return problem; }
}
