package com.processpuzzle.application.configuration.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RepositoryMappings {
   protected Map<Class, Class> entityAndRepositoryMappings = new HashMap<Class, Class>();
   protected Map<Class, List<Class>> repositoryAndStrategyMappings = new HashMap<Class, List<Class>>();

   protected RepositoryMappings() {
      setUpEntityAndRepositoryMappings();
      setUpRespositoryAndStrategyMappings();
   }

   protected void appendMappings(Class[] classMappings) {
      for (int i = 0; i < classMappings.length; i +=2 ) {
         //Note: the classMapping array is is a 2xn dimensional array.
         //The first column contains the repository class, the second contains the domain class object.
         entityAndRepositoryMappings.put(classMappings[i + 1], classMappings[i]) ;
      }
   }

//Properties
   public Map<Class, Class> getEntityAndRepositoryMappings() { return entityAndRepositoryMappings; }
   public Map<Class, List<Class>> getResopsitoryAndStrategyMappings() { return repositoryAndStrategyMappings; }
   
//Private helper methods
   protected abstract void setUpEntityAndRepositoryMappings();
   protected abstract void setUpRespositoryAndStrategyMappings();
   
   protected void appendEntityRepositoryMapping( Class entity, Class repository ){
      entityAndRepositoryMappings.put(entity, repository);
   }
   
   protected void appendRepositoryStrategyMapping( Class repository, List<Class> strategies ){
      repositoryAndStrategyMappings.put(repository, strategies);
   }
   
   protected List<Class> createListFromArray( Class[] repositories ) {
      List<Class> repositoryList = new ArrayList<Class>();
      for (int i = 0; i < repositories.length; i++) {
         repositoryList.add( repositories[i]);
      }
      return repositoryList;
   }
}
