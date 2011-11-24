/*
Name: 
    - ArtifactService

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

package com.processpuzzle.artifact.service;

import hu.itkodex.commons.persistence.UnitOfWork;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.soap.addressing.server.annotation.Action;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactView;
import com.processpuzzle.artifact.domain.DefaultArtifactRepository;
import com.processpuzzle.persistence.domain.DefaultUnitOfWork;
import com.processpuzzle.user_session.domain.UserRequestManager;

@Endpoint
//@Address( value="http://localhost:8080/ProcessPuzzle/ServiceFacade" )
public class ArtifactService extends AbstractArtifactService {

   public ArtifactService() {
      super();
   }
   
   public FindArtifactResponse findAll( FindArtifactRequest request ) {
      return null;
   }

   @Action( value="http://www.processpuzzle.com/WorkProduct/findById" )
   //public FindByIdResponse findById( @XPathParam( "/pp:FindByIdRequest/id" ) String request ){
   public FindArtifactResponse findById( FindArtifactRequest request ){
      ProcessPuzzleContext applicationContext = UserRequestManager.getInstance().getApplicationContext();
      DefaultArtifactRepository workProductRepository = applicationContext.getRepository( DefaultArtifactRepository.class );
      UnitOfWork work = new DefaultUnitOfWork( true );
      Artifact<?> workProduct = workProductRepository.findById( work, request.getId() );
      
      ArtifactView<?> requestedView;
      if( request.getRequestedViewName() != null ) requestedView = workProduct.findViewByName( request.getRequestedViewName() );
      else requestedView = workProduct.getDefaultView();
      
      FindArtifactResponse response = new FindArtifactResponse( requestedView );
      
      return response;
   }
}
