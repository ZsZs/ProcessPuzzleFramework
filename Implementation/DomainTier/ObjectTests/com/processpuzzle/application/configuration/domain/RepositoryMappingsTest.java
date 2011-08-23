package com.processpuzzle.application.configuration.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.processpuzzle.persistence.domain.HibernatePersistenceProvider;
import com.processpuzzle.persistence.domain.InMemoryPersistenceProvider;
import com.processpuzzle.persistence.domain.TestEntity;
import com.processpuzzle.persistence.domain.TestEntityRepository;

public class RepositoryMappingsTest {
   private TestRespositoryMappings testMapping = null;
   
   @Before
   public void beforEachTest() {
      testMapping = new TestRespositoryMappings();
   }
   
   @After
   public void afterEachTest() {
      testMapping = null;
   }
   
   @Test
   public void testEntityRepositoryMappings(){
      assertTrue("TestEntity is managed by:", testMapping.getEntityAndRepositoryMappings().get( TestEntity.class).equals(TestEntityRepository.class) );
   }
   
   @Test
   public void testRepositoryStrategyMapping(){
      List<?> repositories = testMapping.getResopsitoryAndStrategyMappings().get( TestEntityRepository.class );
      assertTrue("TestEntityRepository is supported by:", repositories.contains(HibernatePersistenceProvider.class) );
      assertTrue("TestEntityRepository is supported by:", repositories.contains(InMemoryPersistenceProvider.class) );
   }
}
