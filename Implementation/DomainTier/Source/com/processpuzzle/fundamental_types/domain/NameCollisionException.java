package com.processpuzzle.fundamental_types.domain;


public class NameCollisionException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -6602428235844905102L;
   private static String defaultMessagePattern = "The given name: ''{0}'' is in coallision with another name. Context: ''{1}''";
   private String name = null;
   private String context = null;

   public NameCollisionException( String name, String context ) {
      super( ExceptionHelper.defineMessage(
            NameCollisionException.class,
            new Object[] {name, context},
            defaultMessagePattern ));
      this.name = name;
      this.context = context;
   }

   public String getName() { return name; }
   public String getContext() {return context; }
}
