package com.processpuzzle.address.domain;

import hu.itkodex.litest.template.DomainObjectTestEnvironment;
import hu.itkodex.litest.template.DomainObjectTestFixture;

public class ZipCodeTestFixture<ZipCodex> extends DomainObjectTestFixture<ZipCode> {
   public static final Integer ANOTHER_ZIP_CODE_VALUE = 5678;
   public static final String SETTLEMENT_NAME = "Budapest";
   public static final String ZIP_CODE_TEXT = "2111";
   public static final Integer ZIP_CODE_VALUE = 2111;
   private Settlement settlement;
   
   public ZipCodeTestFixture( DomainObjectTestEnvironment<ZipCode, ?> testEnvironment ) {
      super( testEnvironment );
   }
   
   //Properties
   public Settlement getSettlement() { return settlement; }
   public ZipCode getZipCode() { return sut; }

   //Protected, private helper methods
   @Override protected void configureAfterSutInstantiation() {
      settlement = new SettlementFactory().createSettlement( SETTLEMENT_NAME );
   }

   @Override protected ZipCode instantiateSUT() {
      return new ZipCode( ZIP_CODE_VALUE );
   }

   @Override protected void releaseResources() {
   }

}
