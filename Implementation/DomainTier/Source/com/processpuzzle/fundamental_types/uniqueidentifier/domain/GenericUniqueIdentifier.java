package com.processpuzzle.fundamental_types.uniqueidentifier.domain;



public abstract class GenericUniqueIdentifier implements UniqueIdentifier {
   private String identifier;
   
   protected GenericUniqueIdentifier() {
   }
   
   public GenericUniqueIdentifier( String identifier ) throws InvalidUniqueIdentifier {
      validate( identifier );
      this.identifier = identifier;
   }
   
   public String getIdentifier() { return identifier; }

   protected abstract void validate( String identifier ) throws InvalidUniqueIdentifier;
}
