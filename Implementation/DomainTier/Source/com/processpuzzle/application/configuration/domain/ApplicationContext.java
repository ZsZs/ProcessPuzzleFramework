package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.application.domain.Application;

public interface ApplicationContext {
   public void setUp( Application.Action applicationAction );
   public void tearDown( Application.Action applicationAction );
   public Application getApplication();
   public boolean isConfigured();
}
