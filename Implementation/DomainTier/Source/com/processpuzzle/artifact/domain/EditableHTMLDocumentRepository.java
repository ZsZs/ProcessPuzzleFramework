/*
Name: 
    - EditableHTMLDocumentRepository

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


import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.commons.persistence.PersistenceStrategy;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.domain.GenericRepository;

public class EditableHTMLDocumentRepository extends GenericRepository<EditableHTMLDocument> {

   public EditableHTMLDocumentRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public EditableHTMLDocument findEditableHTMLDocumentById( DefaultUnitOfWork work, String id ) {
      return (EditableHTMLDocument) findById( work, EditableHTMLDocument.class, new Integer( id ) );
   }

   public void updateEditableHTMLDocument( DefaultUnitOfWork work, EditableHTMLDocument editableHTMLDocument ) {
      update( work, EditableHTMLDocument.class, editableHTMLDocument );
   }
}
