package com.processpuzzle.workflow.activity.domain;

public class ProcessPlanName {

   private Integer id;

   private Integer year;
   private Integer count;

   public ProcessPlanName(Integer year, Integer count) {
      this.year = year;
      this.count = count;
   }

   public ProcessPlanName() {}

   public Integer getCount() {
      return count;
   }

   public void setCount(Integer count) {
      this.count = count;
   }

   public Integer getYear() {
      return year;
   }

   public void setYear(Integer year) {
      this.year = year;
   }

   public Integer getId() {
      return id;
   }

}
