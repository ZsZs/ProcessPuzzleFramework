package com.processpuzzle.fundamental_types.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class ParameterDefinition {
   public static final String PARAMETER_DEFINITION_DELIMITERS = ":[]//";
   public static final String WHITE_SPACES = " ";
   private String name;
   private String description;
   private String typeName;
   private Class<?> type;
   private String multiplicity;
   
   public ParameterDefinition( String name, String type ) throws ClassNotFoundException {
      this( name, type, null, null );
   }
   
   public ParameterDefinition( String name, String type, String multiplicity, String description ) throws ClassNotFoundException {
      this.name = name;
      this.description = description;
      this.typeName = type;
      this.multiplicity = multiplicity;
      this.type = Class.forName( this.typeName );
   }
   
   public boolean equals( Object objectToCheck ) {
      if( !(objectToCheck instanceof ParameterDefinition ) ) return false;
      
      ParameterDefinition anotherQuery = (ParameterDefinition) objectToCheck;
      return name.equals( anotherQuery.name ) && 
             description.equals( anotherQuery.description ) && 
             type.equals( anotherQuery.type ) && 
             multiplicity.equals( anotherQuery.multiplicity );
   }

   public int hashCode() {
      int result = HashCodeUtil.SEED;
      result = HashCodeUtil.hash( result, name );
      result = HashCodeUtil.hash( result, description );
      result = HashCodeUtil.hash( result, type );
      result = HashCodeUtil.hash( result, multiplicity );
   
      return result;
   }

   public static ParameterDefinition parse( String definitionText ) throws ClassNotFoundException {
      Pattern namePattern = Pattern.compile( "([a-z]|[A-Z]|[0-9])+\\s*:" );
      Matcher nameFinder = namePattern.matcher( definitionText );
      String name = null;
      if( nameFinder.find() ) {
         name = StringUtils.strip( nameFinder.group().substring( 0, nameFinder.end() -1 ));
      }
      
      Pattern typePattern = Pattern.compile( ":\\s*([^]]+)(\\[|=)" );
      Matcher typeFinder = typePattern.matcher( definitionText );
      String type = null;
      if( typeFinder.find() ) {
         type = "java.lang." + StringUtils.strip( typeFinder.group().substring( 1, typeFinder.group().length() -1 ));
      }
      
      Pattern multiplicityPattern = Pattern.compile( "\\[([^]]+)\\]" );
      Matcher multiplicityFinder = multiplicityPattern.matcher( definitionText );
      String multiplicity = null;
      if( multiplicityFinder.find() ) {
         multiplicity = StringUtils.strip( multiplicityFinder.group().substring( 1, multiplicityFinder.group().length() -1 ));
      }
      
      Pattern descriptionPattern = Pattern.compile( "//([A-Z]|[a-z]|\\s|\\.)*" );
      Matcher descriptionFinder = descriptionPattern.matcher( definitionText );
      String description = null;
      if( descriptionFinder.find() ) {
         description = StringUtils.strip( descriptionFinder.group().substring( 2 ));
      }
      
      return new ParameterDefinition( name, type, multiplicity, description );
   }

   //Properties
   public String getDescription() {
      return description;
   }

   public void setDescription( String description ) {
      this.description = description;
   }

   public String getMultiplicity() {
      return multiplicity;
   }

   public void setMultiplicity( String multiplicity ) {
      this.multiplicity = multiplicity;
   }
   public String getName() {
      return name;
   }

   public void setName( String name ) {
      this.name = name;
   }

   public Class<?> getType() {
      return type;
   }

   public String getTypeName() {
      return typeName;
   }

   public void setTypeName( String type ) {
      this.typeName = type;
   }
}
