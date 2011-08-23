package com.processpuzzle.persistence.query.domain;

public class SubCriteria extends Criteria {
   
   private Criteria parentCriteria;

   public Criteria getParentCriteria() {
      return parentCriteria;
   }

   public void setParentCriteria(Criteria parentCriteria) {
      this.parentCriteria = parentCriteria;
   }

}
