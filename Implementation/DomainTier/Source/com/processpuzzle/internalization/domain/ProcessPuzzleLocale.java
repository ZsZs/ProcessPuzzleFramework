/*
Name: 
    - ProcessPuzzleLocale

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.internalization.domain;


import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.processpuzzle.application.configuration.domain.MeasurementContext;
import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.fundamental_types.quantity.money.domain.Currency;
import com.processpuzzle.fundamental_types.textformat.domain.AddressFormatSpecifier;
import com.processpuzzle.fundamental_types.textformat.domain.DateFormatSpecifier;
import com.processpuzzle.fundamental_types.textformat.domain.PersonNameFormatSpecifier;
import com.processpuzzle.fundamental_types.textformat.domain.QuantityFormatSpecifier;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class ProcessPuzzleLocale implements AggregateRoot, Comparable<Object> {
   public static String SPECIFIER_DELIMITERS = ",;_-";
   public static String DEFAULT_SPECIFIER_DELIMITER = "_";
   private Integer id = null;
   private String language = null;
   private String country = null;
   private String variant = null;
   private boolean isDefault = false;
   private Currency legalTender;
   private AddressFormatSpecifier addressFormat;
   private PersonNameFormatSpecifier personNameFormat;
   private DateFormatSpecifier dateFormat = null;
   private QuantityFormatSpecifier quantityFormat = null;
   char decimalSeparator = '.';

//Constructors
   public ProcessPuzzleLocale(String language, String country, String variant) {
      if( !checkIfSupported( language, country ) )
         throw new UnsupportedLocaleException( language, country, variant );
      
      this.language = language;
      this.country = country;
      this.variant = variant;

      quantityFormat = new QuantityFormatSpecifier(this);
      dateFormat = new DateFormatSpecifier(this);
   }

   public ProcessPuzzleLocale(String language, String country) {
      this(language, country, null);
   }

   public ProcessPuzzleLocale(String language) {
	   this(language, null, null);
	   
	   this.country=language.toUpperCase();
	   
	   Locale[] locales=Locale.getAvailableLocales();
	   for (int i=0;i<locales.length;i++){
		   if (locales[i].getLanguage().equals(language) && locales[i].getCountry().length()==2){   
			   this.country=locales[i].getCountry();
			   break;
		   }
	   }
   }

   public ProcessPuzzleLocale(Locale locale) {
      this(locale.getLanguage(), locale.getCountry(), locale.getVariant());
      try {
         this.setLegalTender(new Currency("", java.util.Currency.getInstance(locale).toString()));
      } catch (IllegalArgumentException e) {}
   }

   protected ProcessPuzzleLocale() {}

   public static ProcessPuzzleLocale parse( String localeSpecifier ) {
      if( localeSpecifier == null ) throw new LocaleParseException( localeSpecifier);
      String language = null;
      String country = null;
      String variant = null;
      
      StringTokenizer tokenizer = new StringTokenizer( localeSpecifier, SPECIFIER_DELIMITERS );
      int index = 0;
      while (tokenizer.hasMoreTokens()) {
         String token = tokenizer.nextToken();
         if( index == 0 ) language = token.trim();
         if( index == 1 ) country = token.trim();
         if( index == 2 ) variant = token.trim();
         index++;
      }
      
      
      ProcessPuzzleLocale locale = null;
      try {
    	  if (country==null)
    		  locale=new ProcessPuzzleLocale(language);
    	  else if (variant==null)
    		  locale=new ProcessPuzzleLocale(language,country);
    	  else
    		  locale = new ProcessPuzzleLocale( language, country, variant );
      } catch (UnsupportedLocaleException e) {
         throw new LocaleParseException( localeSpecifier);
      }
      return locale;
   }

   //Public mutators
   public void update(ProcessPuzzleLocale newLocal) {
      if ((newLocal.getDateFormat() != null)) {
         dateFormat = newLocal.getDateFormat();
      }

      // Updating numberFormat
      if ((newLocal.getQuantityFormat() != null)) {
         quantityFormat = newLocal.getQuantityFormat();
      }

      // Updating decimalSeparator
      // if ((newLocal.getDecimalSeparator()!=decimalSeparator)){
      decimalSeparator = newLocal.getQuantityFormat().getDecimalSeparator();
      // }
      // Updating legalTender
      if ((newLocal.getLegalTender() != null)) {
         legalTender = newLocal.getLegalTender();
      }
   }

//Public accessors
   public String toString() {
      return new String(language + DEFAULT_SPECIFIER_DELIMITER + country + DEFAULT_SPECIFIER_DELIMITER + variant);
   }

   public boolean equals(ProcessPuzzleLocale other) {
      /*
       * if (language.equals(other.getLanguage()) &&
       * country.equals(other.getCountry()) &&
       * variant.equals(other.getVariant())) return true; else if
       * (language.equals(other.getLanguage()) &&
       * country.equals(other.getCountry()) && other.getVariant() == null)
       * return true; else if (language.equals(other.getLanguage()) &&
       * other.getCountry() == null && other.getVariant() == null) return true;
       * else return false;
       */
      Locale javaLocale = getJavaLocale();
      return javaLocale.equals(other.getJavaLocale());
   }

   public boolean equals(Object other) {
      Locale javaLocale = getJavaLocale();
      if (this == other) return true;
      if (!this.getClass().equals(other.getClass())) return false;
      if (!(other instanceof ProcessPuzzleLocale)) return false;
      boolean result = false;
      ProcessPuzzleLocale o = (ProcessPuzzleLocale) other;
      result = new EqualsBuilder().append(this.getLanguage(), o.getLanguage()).append(this.getCountry(), o.getCountry()).append(
            javaLocale.getVariant(), o.getVariant()).isEquals();
      return result;
   }

   public int hashCode() {
      Locale javaLocale = getJavaLocale();
      return new HashCodeBuilder().append(javaLocale.getLanguage()).append(javaLocale.getCountry()).append(javaLocale.getVariant()).toHashCode();
   }

   public int compareTo(Object other) {
      Locale javaLocale = getJavaLocale();
      int result = 0;
      if (other instanceof ProcessPuzzleLocale) {
         ProcessPuzzleLocale o = (ProcessPuzzleLocale) other;
         result = new CompareToBuilder().append(javaLocale.getLanguage(), o.getLanguage()).append(javaLocale.getCountry(), o.getCountry())
               .append(javaLocale.getVariant(), o.getVariant()).toComparison();
      }
      return result;
   }

//Public accessors
   public Locale getJavaLocale() {
      if (language != null && country != null && variant != null) return new Locale(language, country, variant);
      else if (language != null && country != null) return new Locale(language, country);
      else if (language != null) return new Locale(language);
      else return null;
   }
   
//Properties
   public Integer getId() { return id; }
   public String getLanguage() { return language; }
   public String getCountry() { return country == null ? new String() : country; }
   public String getVariant() { return variant == null ? new String() : variant; }
   public boolean isDefault() { return isDefault; }
   public void setDefault() { isDefault = true; }
   public void unsetDefault() { isDefault = false; }
   public Currency getLegalTender() { return legalTender; }

   public QuantityFormatSpecifier getQuantityFormat() { return quantityFormat; }

   public DateFormatSpecifier getDateFormat() { return dateFormat; }
   public void setDateFormat(String pattern) { dateFormat.setDatePattern(pattern); }

   public PersonNameFormatSpecifier getPersonNameFormat() { return personNameFormat; }
   public void setPersonNameFormat(PersonNameFormatSpecifier personNameFormat) { this.personNameFormat = personNameFormat; }

   public AddressFormatSpecifier getAddressFormat() { return addressFormat; }
   public void setAddressFormat(AddressFormatSpecifier addressFormat) { this.addressFormat = addressFormat; }

   public void setLegalTender(Currency cur) { legalTender = cur; }
   public void setLegalTender(String symbol) {
      ProcessPuzzleContext config = UserRequestManager.getInstance().getApplicationContext();
      MeasurementContext repository = config.getMeasurementContext();

      legalTender = (Currency)repository.findUnitBySymbol(symbol);
   }

//Private helper methods
   private boolean checkIfSupported( String language, String country ) {
      Locale subjectLocale = null;
      if( country != null ) subjectLocale = new Locale( language, country );
      else if( language != null ) subjectLocale = new Locale( language );
      else return false;
      
      Locale[] availableLocales = Locale.getAvailableLocales();
      for (int i = 0; i < availableLocales.length; i++) {
         Locale availableLocale = availableLocales[i]; 
         if( availableLocale.equals( subjectLocale )) return true;
      }
      return false;
   }
}
