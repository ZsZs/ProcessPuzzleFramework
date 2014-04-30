/*
 * Created on Feb 12, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.application.configuration.domain;

import com.processpuzzle.persistence.domain.HibernatePersistenceProvider;
import com.processpuzzle.persistence.domain.InMemoryPersistenceProvider;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityComponent;
import com.processpuzzle.persistence.domain.TestEntityRepository;

public class TestRespositoryMappings extends RepositoryMappings {
//   private Class[] domainClasses = new Class[] { 
//         TestEntityRepository.class, TestEntity.class, 
//         TestEntityRepository.class, TestEntityComponent.class, 
//         ProcessPuzzleLocaleRepository.class, ProcessPuzzleLocale.class,
//         null, FormatSpecifier.class
//   };

   public TestRespositoryMappings() {
      super();
//      this.appendMappings(domainClasses);
   }

   @Override
   protected void setUpEntityAndRepositoryMappings() {
      appendEntityRepositoryMapping(TestEntity.class, TestEntityRepository.class);
      appendEntityRepositoryMapping(TestEntityComponent.class, TestEntityRepository.class);
   }

   @Override
   protected void setUpRespositoryAndStrategyMappings() {
      Class<?>[] strategies = {HibernatePersistenceProvider.class, InMemoryPersistenceProvider.class};
      appendRepositoryStrategyMapping(TestEntityRepository.class, createListFromArray( strategies ));
   }
}
