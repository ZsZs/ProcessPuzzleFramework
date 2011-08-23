/*
 * Created on Dec 5, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.persistence.domain;

import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;

import com.processpuzzle.application.configuration.domain.PersistentDataInitializationStrategies;

/**
 * @author zsolt.zsuffa TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 * @uml.annotations derived_abstraction="pathmap://ObjectPuzzle/Design/Enterprise%20IT%20Design%20Model.emx#_VmRCQGSqEdqKwsHPyUuLVw"
 */
public abstract class SynchronizationProvider extends DefaultRepositoryEventHandler {

   public SynchronizationProvider( String name, HierarchicalConfiguration configuration, List<Class<?>> persistentClasses, PersistentDataInitializationStrategies databaseCreationStrategy ) {
      super( name, configuration, persistentClasses, databaseCreationStrategy );
   }
}
