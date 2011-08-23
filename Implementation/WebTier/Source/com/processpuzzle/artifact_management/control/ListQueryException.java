/*
 * Created on Jul 14, 2006
 */
package com.processpuzzle.artifact_management.control;

import com.processpuzzle.fundamental_types.domain.ExceptionHelper;
import com.processpuzzle.fundamental_types.domain.ProcessPuzzleProgrammingException;

/**
 * @author zsolt.zsuffa
 */
public class ListQueryException extends ProcessPuzzleProgrammingException {
   private static final long serialVersionUID = -1973854779648935377L;
   private static String defaultMessagePattern = "Running query: '''{0}''', start:'''{1}''', count:'''{2}''' caused error. See the log for more details.";
   private static String extendedMessagePattern = "'''{0}'''. Running query: '''{1}''', start:'''{2}''', count:'''{3}''' caused error. See the log for more details.";
   private String problem;
   private String query;
   private Integer start;
   private Integer count;

   public ListQueryException( String query, Integer start, Integer count, Throwable e ) {
      super( ExceptionHelper.defineMessage( 
            ListQueryException.class, new Object[] { query, start, count }, defaultMessagePattern ), e );
      this.query = query;
      this.start = start;
      this.count = count;
   }

   public ListQueryException( String problem, String query, Integer start, Integer count, Throwable e ) {
      super( ExceptionHelper.defineMessage( 
            ListQueryException.class, new Object[] { problem, query, start, count }, extendedMessagePattern ), e );
      this.problem = problem;
      this.query = query;
      this.start = start;
      this.count = count;
   }

   public String getProblem() {
      return problem;
   }
   
   public String getQuery() {
      return query;
   }

   public Integer getStart() {
      return start;
   }

   public Integer getCount() {
      return count;
   }
   
}