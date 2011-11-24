/*
Name: 
    - AggregateDefinition

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

package com.processpuzzle.fundamental_types.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateDefinition {
   protected Class<?> factoryClass = null;
   protected Class<?> repositoryClass = null;
   protected Class<?> rootClass = null;
   protected List<Class<?>> managedEntities = new ArrayList<Class<?>>();
   protected List<Class<?>> managedValueObjects=new ArrayList<Class<?>>();
   protected List<Class<?>> referencedNeighbourRoots = new ArrayList<Class<?>>();
   protected List<Class<?>> anyOtherRelatedClasses = new ArrayList<Class<?>>();

   protected AggregateDefinition() {
      defineFactoryClass();
      defineRepositoryClass();
      defineRootClass();
      defineManagedEntities();
      defineReferencedNeighbourRoots();
      defineManagedValueObjects();
      defineAnyOtherRelatedClasses();
   }
//Mutators
   protected abstract void defineFactoryClass();
   protected abstract void defineRepositoryClass();
   protected abstract void defineRootClass();
   protected abstract void defineManagedEntities();
   protected abstract void defineReferencedNeighbourRoots();
   protected abstract void defineManagedValueObjects();
   protected abstract void defineAnyOtherRelatedClasses();
   
//Properties
   public Class<?> getFactoryClass(){ return factoryClass;}
   public Class<?> getRepositoryClass(){return repositoryClass;}
   public Class<?> getRootClass() { return rootClass; }
   public List<Class<?>> getManagedEntities() { return managedEntities; }
   public List<Class<?>> getManagedValueObjects(){return managedValueObjects;}
   public List<Class<?>> getReferencedNeighbourRoots() { return referencedNeighbourRoots; }
   public List<Class<?>> getAnyOtherRelatedClasses() { return anyOtherRelatedClasses; }
}
