/*
Name: 
    - ArtifactMenu

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

package com.processpuzzle.artifact_type.domain;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.processpuzzle.commons.persistence.Entity;
import com.processpuzzle.fundamental_types.domain.GenericEntity;
import com.processpuzzle.persistence.query.domain.DefaultIdentityExpression;

public class ArtifactMenu extends GenericEntity<ArtifactMenu> implements Entity{
   private @XmlAttribute String name;
   private @XmlElement ArtifactMenuCommand command;

   public ArtifactMenuCommand getCommand() { return command; }
   public @Override <I extends DefaultIdentityExpression<ArtifactMenu>> I getDefaultIdentity() {
      // TODO Auto-generated method stub
      return null;
   }

   //Properties
   public String getName() { return name; }

   public void setCommand( ArtifactMenuCommand command) { this.command = command; }
   protected ArtifactMenu() {}

   protected @Override void defineIdentityExpressions() {
      // TODO Auto-generated method stub
   }
}
