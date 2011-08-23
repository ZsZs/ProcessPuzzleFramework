package com.processpuzzle.persistence.domain;

import hu.itkodex.commons.generics.GenericTypeParameterInvestigator;
import hu.itkodex.commons.persistence.PersistentObject;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

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
