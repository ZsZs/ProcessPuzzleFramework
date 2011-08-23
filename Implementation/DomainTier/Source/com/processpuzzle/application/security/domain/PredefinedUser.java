package com.processpuzzle.application.security.domain;

public enum PredefinedUser {
   ANONYMOUS( "Anonymous", "anonym" ),
   SYSTEM_ADMINISTRATOR( "admin", "admin" );
   
   
   PredefinedUser( String userName, String password ) {
      this.userName = userName;
      this.password = password;
   }
   
   private String userName;
   private String password;
   
   public String getUserName() { return userName; };
   public String getPassword() { return password; }
}
