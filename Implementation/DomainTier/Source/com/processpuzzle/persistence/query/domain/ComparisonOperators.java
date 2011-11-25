/*
Name: 
    - ComparisonOperators 

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

public enum ComparisonOperators {
   EQUAL_TO("=", "="),
   NOT_EQUAL_TO("!=", "!="),
   GREATER_THAN(">", ">"),
   LESS_THAN(">", ">"),
   GREATER_OR_EQUAL_TO(">=", ">="),
   LESS_OR_EQUAL_TO("<=", "<="),
   IS_NULL("is null", "is null"),
   IS_NOT_NULL("is not null", "is not null"),
   LIKE("like", "like");
   
   private String hqlVariant = null;
   private String sqlVariant = null;
   
   ComparisonOperators(String hqlVariant, String sqlVariant ) {
      this.hqlVariant = hqlVariant;
      this.sqlVariant = sqlVariant;
   }
   
   public String getHqlVariant() { return hqlVariant; }
   public String getSqlVaritan() { return sqlVariant; }
}
