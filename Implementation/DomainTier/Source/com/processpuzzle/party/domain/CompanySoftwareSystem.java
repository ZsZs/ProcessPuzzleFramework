/*
Name: 
    - CompanySoftwareSystem 

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

package com.processpuzzle.party.domain;

public class CompanySoftwareSystem {
   private Integer id;
   private Company company;
   private SoftwareSystem softwareSystem;

   public Company getCompany() {
      return company;
   }

   public void setCompany(Company company) {
      this.company = company;
   }

   public Integer getId() {
      return id;
   }

   public SoftwareSystem getSoftwareSystem() {
      return softwareSystem;
   }

   public void setSoftwareSystem(SoftwareSystem softwareSystem) {
      this.softwareSystem = softwareSystem;
   }

   public CompanySoftwareSystem() {
      super();
      // TODO Auto-generated constructor stub
   }

   public CompanySoftwareSystem(Company company, SoftwareSystem softwareSystem) {
      // TODO Auto-generated constructor stub
      super();
      this.company = company;
      this.softwareSystem = softwareSystem;
      // company.addCompanySoftwareSystem(this);
      softwareSystem.addCompanySoftwareSystem(this);
   }

}
