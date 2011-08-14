package com.processpuzzle.fitnesse.sharedfixtures;

import java.util.HashMap;
import java.util.Map;

import fitlibrary.DoFixture;

public abstract class GenericSetUpPage extends DoFixture {
   private static Map<String, Integer> setUpPageRegister = new HashMap<String, Integer>();
   
   protected void registerTestPage( String pageName ) {
      if( setUpPageRegister.get( pageName ) != null ) {
         Integer usageCount = setUpPageRegister.get( pageName );
         usageCount += 1;
      }else setUpPageRegister.put( pageName, new Integer(1) );
   }
}
