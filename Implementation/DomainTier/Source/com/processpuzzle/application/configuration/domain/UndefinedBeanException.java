package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

public class UndefinedBeanException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -3463810171263032258L;
   private static String defaultMessagePattern = "Bean: ''{0}'' in container: ''{1}'' not found.";
   private final String beanName;
   private final String configurationName;

   UndefinedBeanException( String beanName, String configurationName ) {
      super( ExceptionHelper.defineMessage(
             UndefinedBeanException.class, new Object[] { beanName, configurationName}, defaultMessagePattern ));
      this.beanName = beanName;
      this.configurationName = configurationName;
   }

   public String getBeanName() {
      return beanName;
   }

   public String getConfigurationName() {
      return configurationName;
   }
}
