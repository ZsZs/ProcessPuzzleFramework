package com.processpuzzle.persistence.query.transformer.domain;


public class QueryTransformerFactory {
   public static HQLQueryTransformer createHQLQueryTransformer() {
      return new HQLQueryTransformer();
   }

   public static SQLQueryTransformer createSQLQueryTransformer() {
      return new SQLQueryTransformer();
   }
}
