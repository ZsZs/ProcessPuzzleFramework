/*
Name: 
    - SoftwareSystem

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

import java.util.HashSet;
import java.util.Set;

public class SoftwareSystem {
   private Integer id;
   private String name;
   private String description;
   private Set<CompanySoftwareSystem> companySoftwareSystems = new HashSet<CompanySoftwareSystem>();

   public Set getCompanySoftwareSystems() {
      return companySoftwareSystems;
   }

   public void setCompanySoftwareSystems(Set<CompanySoftwareSystem> companySoftwareSystems) {
      this.companySoftwareSystems = companySoftwareSystems;
   }

   public Integer getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public SoftwareSystem(String name) {
      super();
      // TODO Auto-generated constructor stub
      this.name = name;
   }

   public SoftwareSystem() {
      super();
      // TODO Auto-generated constructor stub
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void addCompanySoftwareSystem(CompanySoftwareSystem system) {
      this.companySoftwareSystems.add(system);
   }

}