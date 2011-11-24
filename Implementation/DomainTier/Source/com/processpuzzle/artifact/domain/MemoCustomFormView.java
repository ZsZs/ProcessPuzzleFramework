/*
Name: 
    - MemoCustomFormView

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

import java.util.Set;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.party.domain.Party;

public class MemoCustomFormView extends CustomFormView<Memo> {

   public void initializeView() {
   }

   public MemoCustomFormView( Memo artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );

   }

   public String getSubject() {
      return parentArtifact.getSubject();
   }

   public User getResponsible() {
      return parentArtifact.getResponsible();
   }

   public Set<Party<?>> getRecipients() {
      return ((Memo) parentArtifact).getRecipients();
   }

   public String getContent() {
      return ((Memo) parentArtifact).getContent();
   }
}
