/*
Name: 
    - HTMLText

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

package com.processpuzzle.artifact.domain;

import hu.itkodex.commons.persistence.Entity;

import java.util.Calendar;
import java.util.Date;

import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class HTMLText extends GenericEntity<HTMLText> implements Entity, Comparable<HTMLText>{
   private String divId;
   private String text;
   private Date creationTimeStamp = Calendar.getInstance().getTime();

   public HTMLText() {}

   public HTMLText(String divId, String text) {
      this.divId = divId;
      this.text = text;
   }

   public String getDivId() {
      return divId;
   }

   public void setDivId(String divId) {
      this.divId = divId;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public Date getCreationTimeStamp() {
      return creationTimeStamp;
   }

   public void setCreationTimeStamp(Date creationTimeStamp) {
      this.creationTimeStamp = creationTimeStamp;
   }

   public int compareTo( HTMLText o) {
      if (!(o instanceof HTMLText)) return -1;
      HTMLText n = (HTMLText) o;
      if (n.getCreationTimeStamp().getTime()<creationTimeStamp.getTime()) return -1;
      if (n.getCreationTimeStamp().getTime()>creationTimeStamp.getTime()) return 1;
      return 0;
   }

   protected void defineIdentityExpressions() {
      // TODO Auto-generated method stub
      
   }

   @Override
   public DefaultIdentityExpression getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }
}