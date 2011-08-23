package com.processpuzzle.persistence.query.domain;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
   
   private String className;
   private String alias; 
   private List subCriterias = new ArrayList();
   private List criterions = new ArrayList();
   
   public Criteria createCriteria(String className, String alias) {
      return new SubCriteria();
   }

   public String getAlias() {
      return alias;
   }

   public void setAlias(String alias) {
      this.alias = alias;
   }

   public String getClassName() {
      return className;
   }

   public void setClassName(String className) {
      this.className = className;
   }

   public List getCriterions() {
      return criterions;
   }

   public void setCriterions(List criterions) {
      this.criterions = criterions;
   }

   public List getSubCriterias() {
      return subCriterias;
   }

   public void setSubCriterias(List subCriterias) {
      this.subCriterias = subCriterias;
   }

}
