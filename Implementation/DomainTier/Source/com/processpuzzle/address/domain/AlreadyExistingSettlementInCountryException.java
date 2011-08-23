package com.processpuzzle.address.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleException;

public class AlreadyExistingSettlementInCountryException extends ProcessPuzzleException {
   private static final long serialVersionUID = 8696487007184343499L;
   protected static String defaultMessagePattern = "Country ''{0}'' already contains this settlement: ''{1}''";
   private final String countryName;
   private final String settlementName;
   
   public AlreadyExistingSettlementInCountryException( String countryName, String settlementName ) {
      super( ExceptionHelper.defineMessage(
            AlreadyExistingSettlementInCountryException.class, 
            new Object[] { countryName, settlementName }, 
            defaultMessagePattern ) );
      this.countryName = countryName;
      this.settlementName = settlementName;
   }
   
   public String getCountryName() { return countryName; }
   public String getSettlementName() { return settlementName; }
}
