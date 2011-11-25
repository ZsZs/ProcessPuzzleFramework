/*
Name: 
    - Criteria 

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
