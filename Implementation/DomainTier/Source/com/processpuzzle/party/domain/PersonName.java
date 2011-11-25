/*
Name: 
    - PersonName

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
 * Created on 2005.08.15.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.processpuzzle.party.domain;

import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class PersonName extends PartyName {
   private String prefix;
   private String givenName;
   private String middleName;
   private String familyName;
   private String suffix;

   public PersonName() {};

   public PersonName(String theGivenName, String theFamilyName) {
      super(theFamilyName + " " + theGivenName);
      this.givenName = theGivenName;
      this.familyName = theFamilyName;
   }

   public String getName() {
      return familyName + " " + givenName;
   }
   
   public void rename( String name ) {
      setFamilyName( name );
   }

   public String getFamilyName() {
      return familyName;
   }

   public void setFamilyName(String familyName) {
      this.familyName = familyName;
   }

   public String getGivenName() {
      return givenName;
   }

   public void setGivenName(String givenName) {
      this.givenName = givenName;
   }

   public String getMiddleName() {
      return middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getPrefix() {
      return prefix;
   }

   public void setPrefix(String prefix) {
      this.prefix = prefix;
   }

   public String getSuffix() {
      return suffix;
   }

   public void setSuffix(String suffix) {
      this.suffix = suffix;
   }

   public String getFormattedName() {
      return getFamilyName() + " " + getGivenName();
   }

   public int hashCode() {
      final int PRIME = 31;
      int result = super.hashCode();
      result = PRIME * result + ((familyName == null) ? 0 : familyName.hashCode());
      result = PRIME * result + ((givenName == null) ? 0 : givenName.hashCode());
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      final PersonName other = (PersonName) obj;
      if (familyName == null) {
         if (other.familyName != null)
            return false;
      } else if (!familyName.equals(other.familyName))
         return false;
      if (givenName == null) {
         if (other.givenName != null)
            return false;
      } else if (!givenName.equals(other.givenName))
         return false;
      return true;
   }

   public int compareTo( PersonName personName ) {
      int c;
      if ((c = personName.getFamilyName().compareTo(familyName)) != 0)
         return c;
      if ((c = personName.getGivenName().compareTo(givenName)) != 0)
         return c;
      return 0;
   }

   @SuppressWarnings("unchecked")
   public DefaultIdentityExpression getDefaultIdentity() {
      return null;
   }
}
