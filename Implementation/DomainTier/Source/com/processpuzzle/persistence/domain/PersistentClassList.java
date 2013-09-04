/*
Name: 
    - PersistentClassList 

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

package com.processpuzzle.persistence.domain;


import java.util.ArrayList;
import java.util.List;

import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.commons.persistence.ValueObject;


public abstract class PersistentClassList {
//   protected List<Class<? extends AggregateRoot>> aggregateRoots = new ArrayList<Class<? extends AggregateRoot>>();
//   protected List<Class<? extends Entity>> entities = new ArrayList<Class<? extends Entity>>();
//   protected List<Class<? extends ValueObject>> valueObjects = new ArrayList<Class<? extends ValueObject>>();
   protected List<Class<? extends AggregateRoot>> aggregateRoots = new ArrayList<Class<? extends AggregateRoot>>();
   protected List<Class<? extends Entity>> entities = new ArrayList<Class<? extends Entity>>();
   protected List<Class<? extends ValueObject>> valueObjects = new ArrayList<Class<? extends ValueObject>>();

   public PersistentClassList() {
      defineAggregateRoots();
      defineEntities();
      defineValueObjects();
   }

//   public List<Class<? extends AggregateRoot>> getAggregateRoots() { return aggregateRoots; }
//   public List<Class<? extends Entity>> getEntities() { return entities; }
//   public List<Class<? extends ValueObject>> getValueObjects() { return valueObjects; }
   public List<Class<? extends AggregateRoot>> getAggregateRoots() { return aggregateRoots; }
   public List<Class<? extends Entity>> getEntities() { return entities; }
   public List<Class<? extends ValueObject>> getValueObjects() { return valueObjects; }

   protected abstract void defineAggregateRoots();
   protected abstract void defineEntities();
   protected abstract void defineValueObjects();
}
