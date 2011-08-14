package com.processpuzzle.fitnesse.fundamental_types;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;

import fitlibrary.SetUpFixture;

public class SetUpDateFormat extends SetUpFixture {
   private static final Logger logger = LoggerFactory.getLogger( SetUpDateFormat.class );
   //private ProcessPuzzleContext applicationContext;
   //private InternalizationContext internationalizationContext;

   public SetUpDateFormat() {
      //applicationContext = UserRequestManager.getInstance().getApplicationContext();
      //internationalizationContext = applicationContext.getInternalizationContext();

      logger.debug( "DEFAULT: " + ((SimpleDateFormat) SimpleDateFormat.getDateTimeInstance( DateFormat.DEFAULT, DateFormat.DEFAULT )).toPattern() );
      logger.debug( "SHORT: " + ((SimpleDateFormat) SimpleDateFormat.getDateTimeInstance( DateFormat.SHORT, DateFormat.SHORT )).toPattern() );
      logger.debug( "MEDIUM: " + ((SimpleDateFormat) SimpleDateFormat.getDateTimeInstance( DateFormat.MEDIUM, DateFormat.MEDIUM )).toPattern() );
      logger.debug( "LONG: " + ((SimpleDateFormat) SimpleDateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG )).toPattern() );
      logger.debug( "FULL: " + ((SimpleDateFormat) SimpleDateFormat.getDateTimeInstance( DateFormat.FULL, DateFormat.FULL )).toPattern() );
   }

   public void languageCountryVariantFormat( String language, String country, String variant, String format ) {

      ProcessPuzzleLocale locale = new ProcessPuzzleLocale( language, country, variant );
      locale.setDateFormat( format );

      SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getDateInstance( DateFormat.LONG, locale.getJavaLocale() );

      logger.debug( locale.toString() + ":" + dateFormat.toPattern() );
   }
}