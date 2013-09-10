package com.processpuzzle.address.domain;


import com.processpuzzle.litest.template.DomainObjectTestEnvironment;
import com.processpuzzle.litest.template.DomainObjectTestFixture;

public class SettlementTestFixture extends DomainObjectTestFixture<Settlement>{
   public static final String OTHER_SETTLEMENT_NAME = "Gödöllõ";
   public static final Integer OTHER_ZIP_VALUE = 1015;
   public static final String SETTLEMENT_NAME = "Budapest";
   public static final Integer ZIP_VALUE = 2111;
   private int initialNumberOfZipCodes;
   private Settlement otherSettlement;
   private ZipCode otherZipCode;
   private Settlement settlement;
   private ZipCode zipCode;
   
   //Constructors
   public SettlementTestFixture( DomainObjectTestEnvironment<Settlement, ?> testEnvironment ) {
      super( testEnvironment );
   }

   //Properties
   public int getInitialNumberOfZipCodex() { return initialNumberOfZipCodes; }
   public Settlement getOtherSettlement() { return otherSettlement; }
   public ZipCode getOtherZipCode() { return otherZipCode; }
   public Settlement getSettlement() { return settlement; }
   public ZipCode getZipCode() { return zipCode; }
   
   //Protected, private helper methods
   @Override protected void configureAfterSutInstantiation() { 
   }
   
   @Override protected void configureBeforeSutInstantiation() {
      zipCode = new ZipCode( ZIP_VALUE );
      otherZipCode = new ZipCode( OTHER_ZIP_VALUE );
      super.configureBeforeSutInstantiation();
   }

   @Override protected Settlement instantiateSUT() {
      settlement = new Settlement( SETTLEMENT_NAME );
      settlement.addZipCode( zipCode );
      initialNumberOfZipCodes = settlement.getZipCodes().size();
      
      otherSettlement = new Settlement( OTHER_SETTLEMENT_NAME );
      return settlement;
   }

   @Override protected void releaseResources() { }
}
