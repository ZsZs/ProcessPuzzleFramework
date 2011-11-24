/*
Name: 
    - CommentListRepository

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

import hu.itkodex.commons.persistence.PersistenceStrategy;
import hu.itkodex.commons.persistence.RepositoryResultSet;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.persistence.query.domain.DefaultQuery;

public class CommentListRepository extends GenericArtifactRepository<CommentList> {

   public CommentListRepository( PersistenceStrategy strategy, ProcessPuzzleContext applicationContext ) {
      super( strategy, applicationContext );
   }

   public Integer add( DefaultUnitOfWork work, CommentList commentList ) {
      return super.add( work, commentList );
   }

   public CommentList findById( DefaultUnitOfWork work, Integer id ) {
      return super.findById( work, id );
   }

   public CommentList findByName( DefaultUnitOfWork work, String name ) {
      return super.findByName( work, name );
   }

   public RepositoryResultSet<CommentList> findByQuery( DefaultUnitOfWork work, DefaultQuery query ) {
      return super.findByQuery( work, query );
   }

   public void update( DefaultUnitOfWork work, CommentList commentList ) {
      update( work, CommentList.class, commentList );
   }
}
