package com.processpuzzle.application.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsSame.*;
import static org.hamcrest.core.IsNot.*;

import com.processpuzzle.sharedfixtures.domaintier.DomainTierTestConfiguration;

public class ApplicationFactoryTest {
   private Application application;

   @Before
   public void beforeEachTests() {
      application = ApplicationFactory.create( TestApplication.class, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
   }

   @Test
   public void create_ShouldRetunNewInstance() {
      assertThat( application, notNullValue() );
   }
   
   @Test public void create_ShouldReturnAlwaysNewInstance() {
      Application anotherInstance = ApplicationFactory.create( TestApplication.class, DomainTierTestConfiguration.APPLICATION_CONFIGURATION_DESCRIPTOR_PATH );
      assertThat( anotherInstance, not( sameInstance( application )));      
   }
}
