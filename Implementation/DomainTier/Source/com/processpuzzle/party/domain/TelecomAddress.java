/*
 * Created on 2005.08.15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

/**
 * @author zsolt.zsuffa TODO To change the template for this generated type comment go to Window - Preferences - Java -
 *         Code Style - Code Templates
 * @uml.annotations derived_abstraction="platform:/resource/ObjectPuzzle%20Pre-Implementation%20Models/Enterprise%20IT%20Design%20Model.emx#_NkFUcD5eEdq3NJ6sg2oavA"
 */
public class TelecomAddress extends Address {
   // private instance variables
   public static final String USED_FOR_FAX = "fax";
   private String countryCode;
   private String nationalDirectDialingPrefix;
   private String areaCode;
   private String number;
   private String extension;
   private String physicalType;

   // public contructors
   public TelecomAddress() {
      super();
   }

   public TelecomAddress( String number, boolean isDefault ) {
      super( isDefault );
      this.number = number;
   }

   public TelecomAddress( String theCountryCode, String theAreaCode, String theNumber ) {
      this.countryCode = theCountryCode;
      this.areaCode = theAreaCode;
      this.number = theNumber;
   }

   public String getAreaCode() {
      return areaCode;
   }

   public void setAreaCode( String areaCode ) {
      this.areaCode = areaCode;
   }

   public String getCountryCode() {
      return countryCode;
   }

   public void setCountryCode( String countryCode ) {
      this.countryCode = countryCode;
   }

   public String getExtension() {
      return extension;
   }

   public void setExtension( String extension ) {
      this.extension = extension;
   }

   public String getNationalDirectDialingPrefix() {
      return nationalDirectDialingPrefix;
   }

   public void setNationalDirectDialingPrefix( String nationalDirectDialingPrefix ) {
      this.nationalDirectDialingPrefix = nationalDirectDialingPrefix;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber( String number ) {
      this.number = number;
   }

   public String getPhysicalType() {
      return physicalType;
   }

   public void setPhysicalType( String physicalType ) {
      this.physicalType = physicalType;
   }

   public boolean equals( Object o ) {
      if( !(o instanceof TelecomAddress) )
         return false;
      TelecomAddress n = (TelecomAddress) o;
      return((this.countryCode.equals( n.countryCode )) && (this.nationalDirectDialingPrefix.equals( n.nationalDirectDialingPrefix ))
            && (this.areaCode.equals( n.areaCode )) && (this.number.equals( n.number )) && (this.extension.equals( n.extension )) && (this.physicalType
            .equals( n.physicalType )));
   }

   public int hashCode() {
      int countryCodeHash;
      int nationalDirectDialingPrefixHash;
      int areaCodeHash;
      int numberHash;
      int extensionHash;
      int physicalTypeHash;
      if( getCountryCode() == null )
         countryCodeHash = 0;
      else
         countryCodeHash = getCountryCode().hashCode();
      if( getNationalDirectDialingPrefix() == null )
         nationalDirectDialingPrefixHash = 0;
      else
         nationalDirectDialingPrefixHash = getNationalDirectDialingPrefix().hashCode();
      if( getAreaCode() == null )
         areaCodeHash = 0;
      else
         areaCodeHash = getAreaCode().hashCode();
      if( getNumber() == null )
         numberHash = 0;
      else
         numberHash = getNumber().hashCode();
      if( getExtension() == null )
         extensionHash = 0;
      else
         extensionHash = getExtension().hashCode();
      if( getPhysicalType() == null )
         physicalTypeHash = 0;
      else
         physicalTypeHash = getPhysicalType().hashCode();
      return countryCodeHash * 100000 + nationalDirectDialingPrefixHash * 10000 + areaCodeHash * 1000 + numberHash * 100 + extensionHash
            * 10 + physicalTypeHash;

   }

   public int compareTo( Object o ) {
      if( !(o instanceof TelecomAddress) )
         return -1;
      TelecomAddress n = (TelecomAddress) o;
      int c;
      if( (c = getCountryCode().compareTo( n.getCountryCode() )) != 0 )
         return c;
      if( (c = getNationalDirectDialingPrefix().compareTo( n.getNationalDirectDialingPrefix() )) != 0 )
         return c;
      if( (c = getAreaCode().compareTo( n.getAreaCode() )) != 0 )
         return c;
      if( (c = getNumber().compareTo( n.getNumber() )) != 0 )
         return c;
      if( (c = getExtension().compareTo( n.getExtension() )) != 0 )
         return c;
      if( (c = getPhysicalType().compareTo( n.getPhysicalType() )) != 0 )
         return c;
      return 0;
   }

   public String toString() {
      StringBuffer returnValue = new StringBuffer();
      if( nationalDirectDialingPrefix != null ){
         returnValue.append( nationalDirectDialingPrefix );
      }
      if( countryCode != null && !"".equals(countryCode)){
         returnValue.append( "+" ).append( countryCode );
      }
      if( areaCode != null && !"".equals( areaCode )){
         returnValue.append( " (" ).append( areaCode ).append( ")" );
      }
      if( number != null ){
         returnValue.append( " " ).append( number );
      }
      if( extension != null ){
         returnValue.append( " " ).append( extension );
      }
      if( physicalType != null ){
         returnValue.append( " " ).append( physicalType );
      }

      return returnValue.toString();
   }
   // public String getCountryCode(){}
   // public accessor methods

   // public mutator operations

   // private mutator operations
}
