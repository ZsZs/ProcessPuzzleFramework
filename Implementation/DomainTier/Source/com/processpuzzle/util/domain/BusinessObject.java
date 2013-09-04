/*
Name: 
    - BusinessObject 

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


import com.processpuzzle.commons.persistence.AggregateRoot;
import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.OrganizationUnit;

public class BusinessObject implements AggregateRoot{

   private Integer id;
   private Company commissionerCompany = null;
   private OrganizationUnit commissionerOrganizationUnit = null;

   public Company getCommissionerCompany() {
      return commissionerCompany;
   }

   public void setCommissionerCompany(Company comissionerCompany) {
      this.commissionerCompany = comissionerCompany;
   }

   public OrganizationUnit getCommissionerOrganizationUnit() {
      return commissionerOrganizationUnit;
   }

   public void setCommissionerOrganizationUnit(OrganizationUnit commissionerOrganizationUnit) {
      this.commissionerOrganizationUnit = commissionerOrganizationUnit;
   }

   public BusinessObject() {}

   public Integer getId() {
      return id;
   }

}
