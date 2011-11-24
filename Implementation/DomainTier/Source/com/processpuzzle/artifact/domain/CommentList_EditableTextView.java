/*
Name: 
    - CommentList_EditableTextView

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

import java.util.SortedSet;
import java.util.TreeSet;

import com.processpuzzle.artifact_type.domain.ArtifactViewType;

public class CommentList_EditableTextView extends EditableTextView<CommentList> {

   public CommentList_EditableTextView( CommentList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
   }

   @Override protected String retrieveObjectsAsXml() {
      return null;
   }

   @Override protected void updateObjectsFromXml( String data ) {
      // TODO Auto-generated method stub
   }

   @Override public void initializeView() {
      // TODO Auto-generated method stub
   }
   
   public SortedSet<Comment> getComments() {
      SortedSet<Comment> comments = new TreeSet<Comment>( parentArtifact.getComments() ); 
      return comments; 
   }
}
