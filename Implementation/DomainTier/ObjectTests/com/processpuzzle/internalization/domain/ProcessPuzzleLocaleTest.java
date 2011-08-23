package com.processpuzzle.internalization.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.processpuzzle.internalization.domain.LocaleParseException;
import com.processpuzzle.internalization.domain.ProcessPuzzleLocale;
import com.processpuzzle.internalization.domain.UnsupportedLocaleException;

public class ProcessPuzzleLocaleTest {
   
   @Test
   public void parse_ForFull() {
      ProcessPuzzleLocale locale = ProcessPuzzleLocale.parse("en_US_WIN");
      assertEquals("en", locale.getLanguage());
      assertEquals("US", locale.getCountry());
      assertEquals("WIN", locale.getVariant());
   }

   @Test
   public void parse_ForCommaSeparator() {
      ProcessPuzzleLocale locale = ProcessPuzzleLocale.parse("en, US, WIN");
      assertEquals("en", locale.getLanguage());
      assertEquals("US", locale.getCountry());
      assertEquals("WIN", locale.getVariant());
   }

   @Test
   public void parse_ForLanguageAndCountry() {
      ProcessPuzzleLocale locale = ProcessPuzzleLocale.parse("hu_HU");
      assertEquals("hu", locale.getLanguage());
      assertEquals("HU", locale.getCountry());
   }
   
   @Test
   public void parse_ForLanguageOnly() {
      ProcessPuzzleLocale locale = ProcessPuzzleLocale.parse("en");
      assertEquals("en", locale.getLanguage());
   }
   
   @Test (expected = LocaleParseException.class)
   public void parse_ForDummySpecifier() {
      ProcessPuzzleLocale.parse("Dummy");      
   }
   
   @Test (expected = UnsupportedLocaleException.class)
   public void instantiation_ForUnsupportedLocale() {
      new ProcessPuzzleLocale( "BlaBla" );
   }
}
