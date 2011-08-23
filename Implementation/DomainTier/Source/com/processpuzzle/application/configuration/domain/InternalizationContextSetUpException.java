package com.processpuzzle.application.configuration.domain;

import java.text.MessageFormat;

import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class InternalizationContextSetUpException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = 3193834308271188609L;
   private static String defaultMessagePattern = "Setting up InternalizationContext with configaration descriptor: ''{0}'' caused an error.";
   private String descriptorUrl = null;

   InternalizationContextSetUpException( PropertyContext context, Throwable cause ){
      super( defineMessage( context ), cause );
      this.descriptorUrl = context.getConfigurationDescriptorUrl();
   }
   
// Properties
   public String getDescriptorUrl() { return descriptorUrl; }
   
   private static final String defineMessage( PropertyContext context ) {
      return MessageFormat.format( defaultMessagePattern, new Object[] {context.getConfigurationDescriptorUrl()} );
   }
}
