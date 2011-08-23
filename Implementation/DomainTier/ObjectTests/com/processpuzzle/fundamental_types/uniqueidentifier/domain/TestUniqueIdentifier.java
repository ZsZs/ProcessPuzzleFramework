package com.processpuzzle.fundamental_types.uniqueidentifier.domain;

public class TestUniqueIdentifier extends GenericUniqueIdentifier {

   
   public TestUniqueIdentifier(String identifier) throws InvalidUniqueIdentifier {
      super(identifier);
   }
   
   @Override
   protected void validate( String identifier ) throws InvalidUniqueIdentifier {
   // TODO Auto-generated method stub

   }

}
