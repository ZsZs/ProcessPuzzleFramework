/*
Name: 
    - AggregatedResult

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


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import com.processpuzzle.commons.generics.GenericTypeParameterInvestigator;
import com.processpuzzle.commons.persistence.PersistentObject;
import com.processpuzzle.commons.persistence.RepositoryResultSet;

public class AggregatedResult<T extends PersistentObject> implements RepositoryResultSet<T> {
   private List<T> resultList;
   private Class<T> wrappperClass;

   public AggregatedResult( Class<T> wrapperClass, List<T> resultList ) {
      this.resultList = resultList;
      this.wrappperClass = wrapperClass;
   }

   @Override
   public List<T> getEntitiesAt( int start, int maxCount ) {
      // TODO Auto-generated method stub
      return null;
   }

   public T getEntityAt( int index ) {
      return instantiateWrapperObject( resultList.get( index ) );
   }

   @Override
   public boolean isEmpty() {
      return resultList.isEmpty();
   }

   @Override
   public Iterator<T> iterator() {
      return null;
   }

   @Override
   public int size() {
      return 1;
   }

   @SuppressWarnings("unchecked")
   @Override
   public T[] toArray() {
      return (T[]) resultList.toArray();
   }

   private T instantiateWrapperObject( Object value ) {
      Class<?> simpleType = GenericTypeParameterInvestigator.getTypeParameter( wrappperClass, 0 );
      Class<?>[] argumentClasses = new Class<?>[] { simpleType };
      Object[] arguments = new Object[] { value };
      Constructor<T> constructor = null;
      T result = null;
      try{
         constructor = wrappperClass.getConstructor( argumentClasses );
         result = (T) constructor.newInstance( arguments );
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchMethodException e ){
         e.printStackTrace();
      }catch( IllegalArgumentException e ){
         e.printStackTrace();
      }catch( InstantiationException e ){
         e.printStackTrace();
      }catch( IllegalAccessException e ){
         e.printStackTrace();
      }catch( InvocationTargetException e ){
         e.printStackTrace();
      }
      return result;
   }
}
