package com.processpuzzle.persistence.domain;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class QLUtility implements QLConstants {

   public static void addWhereAndOrder() {
   // TODO Auto-generated method stub
   }

   public static boolean booleanFor( Object v ) {
      if( v == null )
         return false;
      if( v instanceof Boolean )
         return ((Boolean) v).booleanValue();
      if( v instanceof Number )
         return ((Number) v).doubleValue() != 0.0;
      if( v instanceof String ){
         String string = v.toString().trim();
         if( string.length() == 0 )
            return false;
         if( Character.isDigit( string.charAt( 0 ) ) ){
            try{
               return Double.parseDouble( string ) != 0.0;
            }catch( Exception e ){}
         }
         return "0fFnN- ".indexOf( string.charAt( 0 ) ) == -1;
      }
      if( v instanceof String[] ){
         String[] a = (String[]) v;
         if( a.length > 0 )
            return booleanFor( a[0] );
      }
      return true;
   }

   public static boolean isEmpty( Object v ) {
      return v == null || "".equals( v );
   }

   public static class ArrayMap extends AbstractMap<Object, Object> {
      protected Object array[];

      public ArrayMap( Object array[] ) {
         this.array = array;
      }

      public class ArrayMap_Entry implements Map.Entry {
         public int i;

         public ArrayMap_Entry( int i ) {
            this.i = i;
         }

         public Object getKey() {
            return array[i];
         }

         public Object getValue() {
            return array[i + 1];
         }

         public Object setValue( Object value ) {
            Object v = getValue();
            array[i + 1] = value;
            return v;
         }
      }

      public class ArrayMap_EntrySet<T> extends AbstractSet<T> implements Iterator {
         private int i;

         @SuppressWarnings( "unchecked" )
         public Iterator<T> iterator() {
            return this;
         }

         public int size() {
            return array.length >> 1;
         }

         public boolean hasNext() {
            return i < array.length;
         }

         public Map.Entry next() {
            Map.Entry e = new ArrayMap_Entry( i );
            i += 2;
            return e;
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      }

      public Set entrySet() {
         return new ArrayMap_EntrySet();
      }

      public Object get( Object key ) {
         for( int i = array.length; (i -= 2) >= 0; ){
            if( key.equals( array[i] ) )
               return array[i + 1];
         }
         return null;
      }
   }

   public static Map<?, ?> mapFor( Object array[] ) {
      return new ArrayMap( array );
   }

   public static StringBuffer addWhereAndOrder( StringBuffer b, Map<?, ?> param ) {
      String sep = " WHERE ";
      for( Iterator<?> i = param.entrySet().iterator(); i.hasNext(); ){
         Map.Entry e = (Map.Entry) i.next();
         String key = e.getKey().toString();
         if( WHERE.equals( key ) ){
            b.append( sep ).append( e.getValue() );
            sep = " AND ";
         }else if( key.startsWith( WHERE_IF ) ){
            key = key.substring( WHERE_IF.length() );
            if( booleanFor( param.get( key ) ) ){
               b.append( sep ).append( e.getValue() );
               sep = " AND ";
            }
         }else if( key.startsWith( WHERE_UNLESS ) ){
            key = key.substring( WHERE_UNLESS.length() );
            if( !booleanFor( param.get( key ) ) ){
               b.append( sep ).append( e.getValue() );
               sep = " AND ";
            }
         }else if( key.startsWith( STARTS ) ){
            key = key.substring( STARTS.length() );
            Object expression = param.get( key );
            if( !isEmpty( expression ) ){
               b.append( sep ).append( e.getValue() ).append( " LIKE CONCAT('%','" ).append( expression ).append( "')" );
               sep = " AND ";
            }
         }else if( key.startsWith( CONTAINS ) ){
            key = key.substring( CONTAINS.length() );
            Object expression = param.get( key );
            if( !isEmpty( expression ) ){
               b.append( sep ).append( e.getValue() ).append( " LIKE CONCAT('%','" ).append( expression ).append( "','%')" );
               sep = " AND ";
            }
         }
      }
      Object order = param.get( ORDER );
      if( !isEmpty( order ) )
         b.append( " ORDER BY " ).append( order );
      return b;
   }

}
