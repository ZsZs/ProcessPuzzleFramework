/*
Name: 
    - GeneralService 

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

package com.processpuzzle.util.domain;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class GeneralService {

   public static Object checkIsNotNull( Object value, String prefferedLanguage ) {
      if( value != null && !value.equals( "" ) )
         return value;
      else{
         ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
         return applicationContext.getText( "ui.generic.undefined", prefferedLanguage );
      }
   }

   public static String getUndefinedMessage( String prefferedLanguage ) {
      return ProcessPuzzleContext.getInstance().getText( "ui.generic.undefined", prefferedLanguage );
   }

   public static String toHTMLEntityString( String string ) {
      Map<Character, String> HTMLEntites = new HashMap<Character, String>();
      HTMLEntites.put( new Character( 'á' ), "&aacute;" );
      HTMLEntites.put( new Character( 'Á' ), "&Aacute;" );
      HTMLEntites.put( new Character( 'é' ), "&eacute;" );
      HTMLEntites.put( new Character( 'É' ), "&Eacute;" );
      HTMLEntites.put( new Character( 'í' ), "&iacute;" );
      HTMLEntites.put( new Character( 'Í' ), "&Iacute;" );
      HTMLEntites.put( new Character( 'ó' ), "&oacute;" );
      HTMLEntites.put( new Character( 'Ó' ), "&Oacute;" );
      HTMLEntites.put( new Character( 'ö' ), "&ouml;" );
      HTMLEntites.put( new Character( 'Ö' ), "&Ouml;" );
      HTMLEntites.put( new Character( 'ú' ), "&uacute;" );
      HTMLEntites.put( new Character( 'Ú' ), "&Uacute;" );
      HTMLEntites.put( new Character( 'ü' ), "&uuml;" );
      HTMLEntites.put( new Character( 'Ü' ), "&Uuml;" );
      HTMLEntites.put( new Character( 'û' ), "&ucirc;" );
      HTMLEntites.put( new Character( 'Û' ), "&Ucirc;" );

      String str = "";

      for( int i = 0; i < string.length(); i++ ){
         if( HTMLEntites.containsKey( new Character( string.charAt( i ) ) ) ){
            str += HTMLEntites.get( new Character( string.charAt( i ) ) );
         }else{
            str += new Character( string.charAt( i ) );
         }
      }
      return str;
   }

   public static String encodeXml( String string ) {
      Map<Character, String> HTMLEntites = new HashMap<Character, String>();
      HTMLEntites.put( new Character( '&' ), "&amp;" );

      String str = "";

      for( int i = 0; i < string.length(); i++ ){
         if( HTMLEntites.containsKey( new Character( string.charAt( i ) ) ) ){
            str += HTMLEntites.get( new Character( string.charAt( i ) ) );
         }else{
            str += new Character( string.charAt( i ) );
         }
      }
      return str;
   }

   public static String dateToString( Date date ) {
      if( date != null ){
         String dateToString = "";
         Calendar calendar = new GregorianCalendar();
         calendar.setTime( date );
         dateToString = calendar.get( Calendar.YEAR ) + ".";
         if( calendar.get( Calendar.MONTH ) + 1 < 10 )
            dateToString += "0";
         dateToString += calendar.get( Calendar.MONTH ) + 1 + ".";
         if( calendar.get( Calendar.DAY_OF_MONTH ) < 10 )
            dateToString += "0";
         dateToString += calendar.get( Calendar.DAY_OF_MONTH );
         return dateToString;
      }else
         return "";
   }

   public static Object findCollectionItemByFieldName( Collection<?> collection, String fieldName, Object fieldValue ) {
      Iterator<?> iterator = collection.iterator();

      while( iterator.hasNext() ){
         Object object = (Object) iterator.next();

         if( fieldName != null ){
            if( invokeMethods( fieldName, object ).equals( fieldValue ) ){
               return object;
            }
         }else{
            if( object.equals( fieldValue ) ){
               return object;
            }
         }
      }
      return null;
   }

   public static Method getMethodByName( Class<?> currentClass, String methodName ) {
      Method method = null;
      try{
         method = currentClass.getDeclaredMethod( methodName, new Class[0] );
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchMethodException e ){
         if( currentClass.getSuperclass() != null ){
            method = getMethodByName( currentClass.getSuperclass(), methodName );
         }else{
            return null;
         }
      }
      return method;
   }

   public static Field getFieldByName( Class<?> currentClass, String fieldName ) {
      Field field = null;
      try{
         field = currentClass.getDeclaredField( fieldName );
      }catch( SecurityException e ){
         e.printStackTrace();
      }catch( NoSuchFieldException e ){
         if( currentClass.getSuperclass() != null ){
            field = getFieldByName( currentClass.getSuperclass(), fieldName );
         }else{
            return null;
         }
      }
      return field;
   }

   public static String getFieldValueForXml( String methods, Object object ) {
      String ret = "";
      Object obj = invokeMethods( methods, object );
      if( obj != null )
         ret = obj.toString();
      return ret;
   }

   public static Object invokeMethods( String methods, Object object ) {
      StringTokenizer methodToken = new StringTokenizer( methods, "." );
      String methodName = "";
      Method method = null;
      Object obj = object;
      while( methodToken.hasMoreTokens() ){
         methodName = methodToken.nextToken();
         try{
            method = GeneralService.getMethodByName( obj.getClass(), "get" + (methodName.substring( 0, 1 )).toUpperCase()
                  + methodName.substring( 1 ) );
            obj = method.invoke( obj, new Object[0] );
         }catch( SecurityException e ){
            e.printStackTrace();
         }catch( IllegalArgumentException e ){
            e.printStackTrace();
         }catch( IllegalAccessException e ){
            e.printStackTrace();
         }catch( InvocationTargetException e ){
            e.printStackTrace();
         }
      }
      return obj;
   }

   public static boolean contains( String fileName, Collection<?> documents ) {
      if( (documents != null) && !(documents.isEmpty()) ){
         // for (Iterator iter = documents.iterator(); iter.hasNext();)
         // {
         // Document doc = (Document) iter.next();
         // if (doc.getOriginalFileName().equals(fileName))
         // return true;
         // }
      }
      return false;
   }

   public static String getLastToken( String str, String delim ) {
      StringTokenizer stok = new StringTokenizer( str, delim );
      String thisName = new String();

      while( stok.hasMoreTokens() ){
         thisName = stok.nextToken();
      }
      return thisName;
   }

   public static boolean isValidNow( Date validFrom, Date validTo ) {
      long now = new GregorianCalendar().getTimeInMillis();
      return((validFrom != null) && (validFrom.getTime() < now) && ((validTo == null) || ((validTo != null) && (validTo.getTime() > now))));
   }

   public static String todayDateToString() {
      GregorianCalendar calendar = new GregorianCalendar();
      String dateToString = dateToString( calendar.getTime() );
      if( calendar.get( Calendar.HOUR_OF_DAY ) + 1 < 10 )
         dateToString += "0";
      dateToString += "_" + calendar.get( Calendar.HOUR_OF_DAY ) + "-";
      if( calendar.get( Calendar.MINUTE ) + 1 < 10 )
         dateToString += "0";
      dateToString += calendar.get( Calendar.MINUTE ) + 1 + "-";
      if( calendar.get( Calendar.SECOND ) < 10 )
         dateToString += "0";
      dateToString += calendar.get( Calendar.SECOND );
      return dateToString;
   }

   public static String getFirstToken( String str, String delim ) {
      StringTokenizer stok = new StringTokenizer( str, delim );
      return stok.nextToken();
   }

   public static boolean isValidFormValue( String value ) {
      Integer id = null;
      boolean isInteger = true;
      if( value == null || value.equals( "" ) )
         return false;
      try{
         id = Integer.valueOf( value );
      }catch( NumberFormatException e ){
         isInteger = false;
      }
      if( isInteger ){
         if( id.equals( new Integer( "-1" ) ) )
            return false;
      }
      return true;
   }

   public static <I> List<I> asList( Set<I> s ) {
      List<I> list = new ArrayList<I>();
      for( Iterator<I> iter = s.iterator(); iter.hasNext(); ){
         I element = iter.next();
         list.add( element );
      }
      return list;
   }
}
