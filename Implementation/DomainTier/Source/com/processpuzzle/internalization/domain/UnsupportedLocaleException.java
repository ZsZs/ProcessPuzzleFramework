package com.processpuzzle.internalization.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UnsupportedLocaleException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -131026769078489734L;
   private static String defaultMessagePattern = "Locale with country: ''{0}'', language: ''{1}'', variant: ''{2}'', is not supported.";
   private String language = null;
   private String country = null;
   private String variant = null;

   public UnsupportedLocaleException(String language, String country, String variant) {
      super( ExceptionHelper.defineMessage(
            UnsupportedLocaleException.class,
            new Object[] {language, country, variant},
            defaultMessagePattern));
      this.language = language;
      this.country = country;
      this.variant = variant;
   }

   // Properties
   public String getCountry() {
      return country;
   }

   public String getLanguage() {
      return language;
   }

   public String getVariant() {
      return variant;
   }
}
