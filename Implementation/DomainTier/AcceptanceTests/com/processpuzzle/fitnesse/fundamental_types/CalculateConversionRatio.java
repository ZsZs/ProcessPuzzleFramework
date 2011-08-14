package com.processpuzzle.fitnesse.fundamental_types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleParseException;
import com.processpuzzle.fundamental_types.quantity.domain.Unit;
import com.processpuzzle.user_session.domain.UserRequestManager;

import fit.ColumnFixture;

public class CalculateConversionRatio extends ColumnFixture {
   private static final Logger logger = LoggerFactory.getLogger( CalculateConversionRatio.class );
   private ProcessPuzzleContext applicationContext;
   private MeasurementContext measurementContext;
   public String from;
   public String to;

   public CalculateConversionRatio() {
      super();
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      measurementContext = applicationContext.getMeasurementContext();
      logger.debug( "New instance was created with application context:" + applicationContext );
   }

   public double ratio() {
      Unit fromUnit = null;
      Unit toUnit = null;
      try{
         fromUnit = measurementContext.findUnitBySymbol( from );
         toUnit = measurementContext.findUnitBySymbol( to );
      }catch( Exception e ){
         e.printStackTrace();
      }

      double d = fromUnit.findConversionRatio( toUnit );

      java.text.DecimalFormat df = new java.text.DecimalFormat( "#.######" );
      try{
         return df.parse( df.format( d ) ).doubleValue();

      }catch( java.text.ParseException e ){
         throw new ProcessPuzzleParseException( "??", "Round doubles", e );
      }

   }

}
