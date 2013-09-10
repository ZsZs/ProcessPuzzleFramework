package com.processpuzzle.litest.template;

import java.text.MessageFormat;

public class DataRetrieveException extends RuntimeException {
   private static final long serialVersionUID = 1281793013501801489L;
   private static final String messagePattern = "Can't retrieve column: ''{0}'' of row (id: ''{1}'' from table: ''{2}''.";
   
   DataRetrieveException( String table, Integer id, Class<?> type, String columnName, Throwable cause ) {
      super( MessageFormat.format( messagePattern, new Object[] {columnName, id.toString(), table} ), cause );
   }
}
