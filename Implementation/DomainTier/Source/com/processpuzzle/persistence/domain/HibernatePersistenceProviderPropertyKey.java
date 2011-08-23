package com.processpuzzle.persistence.domain;

import java.text.MessageFormat;

public enum HibernatePersistenceProviderPropertyKey {
   DRIVER_CLASS( "pr:hibernate.pr:connection.pr:driver_class", "hibernate/connection/driver_class" ),
   CONNECTION_URL( "pr:hibernate.pr:connection.pr:url", "hibernate/connection/url" ),
   USER_NAME( "pr:hibernate.pr:connection.ge:username", "hibernate/connection/username" ),
   PASSWORD( "pr:hibernate.pr:connection.ge:password", "hibernate/connection/password" );

   HibernatePersistenceProviderPropertyKey( String defaultKey, String xPathKey ) { 
      this.defaultKey = defaultKey; 
      this.xPathKey = xPathKey; 
   }
   
   public static String createKey( String keyFormat, Object[] parameters ) {
      MessageFormat selector = new MessageFormat( keyFormat );      
      return selector.format( parameters );
   }
   
   public String getDefaultKey() { return this.defaultKey; }
   public String getXPathKey() { return this.xPathKey; }

   private String xPathKey;
   private String defaultKey;
}
