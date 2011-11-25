/*
Name: 
    - PartyRoleConstraint 

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

/*
 * Created on 2005.09.22.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.partyrelationshiptype.domain;

import hu.itkodex.commons.persistence.ValueObject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;

import org.springframework.util.Assert;

import com.processpuzzle.party.domain.Company;
import com.processpuzzle.party.domain.Department;
import com.processpuzzle.party.domain.Division;
import com.processpuzzle.party.domain.Employee;
import com.processpuzzle.party.domain.Organization;
import com.processpuzzle.party.domain.OrganizationUnit;
import com.processpuzzle.party.domain.Person;
import com.processpuzzle.party.domain.ProjectOffice;
import com.processpuzzle.party.partytype.domain.PartyType;

public class PartyRoleConstraint implements ValueObject {
   public static final String PERSON = Person.class.getSimpleName();
   public static final String EMPLOYEE = Employee.class.getSimpleName();
   public static final String ORGANIZATION = Organization.class.getSimpleName();
   public static final String ORGANIZATION_UNIT = OrganizationUnit.class.getSimpleName();
   public static final String DIVISION = Division.class.getSimpleName();
   public static final String DEPARTMENT = Department.class.getSimpleName();
   public static final String COMPANY = Company.class.getSimpleName();
   public static final String PROJECT_OFFICE = ProjectOffice.class.getSimpleName();
   private Integer id;
   @XmlIDREF @XmlAttribute(name = "typeOfParty") private PartyType typeOfParty;

   //Constructors
   protected PartyRoleConstraint() {}

   PartyRoleConstraint( PartyType partyType ) {
      Assert.notNull( partyType );
      typeOfParty = partyType;
   }

   public boolean canPlayRole( PartyType partyType ) {
      return partyType.equals( typeOfParty );
   }

   public Integer getId() { return id; }

   public PartyType getTypeOfParty() { return typeOfParty; }

}
