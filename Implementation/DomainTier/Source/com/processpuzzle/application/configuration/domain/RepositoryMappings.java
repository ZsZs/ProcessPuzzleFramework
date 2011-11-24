/*
Name: 
    - RepositoryMappings

Description: 
    -  

Requires:
    - 
    
Provides:
    - 

Part of: ProcessPuzzle Framework, Domain and Business Model Ready Architecture. Provides content, workflow and social networking functionality. 
http://www.processpuzzle.com

ProcessPuzzle - Content and Workflow Management Integration Business Platform

Author(s): 
    - Zsolt Zsuffa

Copyright: (C) 2011 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
