package com.processpuzzle.persistence.query.domain;

public class LogicalExpression implements Criterion {
   
   private Criterion crit1;
   private Criterion crit2;
   
   public LogicalExpression(Criterion crit1, Criterion crit2) {
      this.crit1 = crit1;
      this.crit2 = crit2;
   }

   public Criterion getCrit1() {
      return crit1;
   }

   public void setCrit1(Criterion crit1) {
      this.crit1 = crit1;
   }

   public Criterion getCrit2() {
      return crit2;
   }

   public void setCrit2(Criterion crit2) {
      this.crit2 = crit2;
   }

   public String renderAsOQL(Criteria criteria) {
      return null;

   }

}
