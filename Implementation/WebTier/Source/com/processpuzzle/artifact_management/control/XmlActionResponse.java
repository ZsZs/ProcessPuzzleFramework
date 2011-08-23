/*
 * Created on May 25, 2006
 */
package com.processpuzzle.artifact_management.control;

/**
 * @author zsolt.zsuffa
 */
public class XmlActionResponse {
   public static String HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
   public static String ROOT_TAG_NAME = "actionResponse";
   public static String OUTCOME_TAG_NAME = "actionOutcomeStatus";
   private boolean outcome;
   private String body = "";

   public String getAsString() {
      String result = HEAD;
      result += "<" + ROOT_TAG_NAME + ">";
      result += "<" + OUTCOME_TAG_NAME + ">";
      result += outcome;
      result += "</" + OUTCOME_TAG_NAME + ">";
      result += "\n" + body + "\n";
      result += "</" + ROOT_TAG_NAME + ">";
      return result;
   }

   public void setOutcome(boolean outcome) {
      this.outcome = outcome;
   }

   public void addStringToBody(String string) {
      if( string.contains("<?xml")) {
         body += string.substring( string.indexOf("?>") +2 );
      }
      else body += string;
   }

   public void addErrorDescription(String description) {
      addStringToBody( "<errorDescription>" + description + "</errorDescription>" );
   }
}