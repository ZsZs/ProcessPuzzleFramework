package com.processpuzzle.internalization.domain;

import java.text.MessageFormat;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class LocaleDefinitionNotFoundException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -131026769078489734L;
   private static String defaultMessagePattern = "Locale definition for the supported type: '''{0}'''_'''{1}''' not found!";
   private String language = null;
   private String country = null;

   public LocaleDefinitionNotFoundException( String language, String country ) {
      super( buildMessage( language, country ) );
      this.language = language;
      this.country = country;
   }

   // Properties
   public String getCountry() {
      return country;
   }

   public String getLanguage() {
      return language;
   }

   private static final String buildMessage(  String language, String country ) {
      return MessageFormat.format( defaultMessagePattern, new Object[] {language, country} );
   }
}
