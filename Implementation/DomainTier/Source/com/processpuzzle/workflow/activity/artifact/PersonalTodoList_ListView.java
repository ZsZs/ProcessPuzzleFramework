/*
Name: 
    - PersonalTodoList_ListView 

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

package com.processpuzzle.workflow.activity.artifact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact.domain.PropertyView;
import com.processpuzzle.artifact_type.domain.ArtifactViewType;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.workflow.activity.domain.ResourceAllocation;
import com.processpuzzle.workflow.activity.domain.TemporalResourceAllocation;
import com.processpuzzle.workflow.activity.domain.WorkflowRepository;

public class PersonalTodoList_ListView extends ArtifactListView<PersonalTodoList> {
   private WorkflowRepository actionRep;

   public PersonalTodoList_ListView( PersonalTodoList artifact, String name, ArtifactViewType type ) {
      super( artifact, name, type );
      actionRep = applicationContext.getRepository( WorkflowRepository.class );
   }

   public String getSignedInUserName() {
      return getLoggedInUser().getUserName();
   }

   // todo: replace with ActionDataSheet query
   public List getTodoList() {
      DefaultUnitOfWork work = new DefaultUnitOfWork( true );
      List<PropertyView> todoList = new ArrayList<PropertyView>();
      List allActions = (List) actionRep.findAll( work );

      for( Iterator<?> iter = allActions.iterator(); iter.hasNext(); ){
         ActionDataSheet<?> actionDataSheet = (ActionDataSheet<?>) iter.next();
         for( Iterator<?> iterator = actionDataSheet.getAction().getResourceAllocations().iterator(); iterator.hasNext(); ){
            ResourceAllocation rAlloc = (ResourceAllocation) iterator.next();
            if( rAlloc instanceof TemporalResourceAllocation ){
               TemporalResourceAllocation tAlloc = (TemporalResourceAllocation) rAlloc;
               if( tAlloc.getAsset() != null && tAlloc.getAsset().getId().equals( getLoggedInUser().getId() ) ){
                  todoList.add( actionDataSheet.getPropertyView() );
               }
            }
         }
      }
      work.finish();
      return todoList;
   }

   public void initializeView() {}

}
