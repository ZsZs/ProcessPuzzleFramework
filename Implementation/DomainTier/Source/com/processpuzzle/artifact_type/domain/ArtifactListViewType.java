/*
Name: 
    - ArtifactListViewType

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
import javax.xml.bind.annotation.XmlRootElement;

import com.processpuzzle.artifact.artifact.ArtifactList_ListView;

@XmlRootElement( name = "artifactListViewType" )
public class ArtifactListViewType extends ArtifactViewType {

   @XmlAttribute
   protected String listedArtifactType;

   @XmlAttribute
   protected String orderBy;

   @XmlAttribute( name = "orderBy" )
   protected String ord;

   ArtifactListViewType( String name, String presentationUri ) {
      super( name, presentationUri );
   }

   ArtifactListViewType( String name ) {
      super( name );
   }

   protected ArtifactListViewType() {
      super();
   }

   public String getOrd() {
      return ord;
   }

   public void setOrd( String order ) {
      this.ord = order;
   }

   public String getOrderBy() {
      return orderBy;
   }

   public void setOrderBy( String orderBy ) {
      this.orderBy = orderBy;
   }

   public String getListedArtifactType() {
      return listedArtifactType;
   }

   public void setListedArtifactType( String listedArtifactType ) {
      this.listedArtifactType = listedArtifactType;
   }

   @Override
   public String getViewClassName() {
      if( viewClassName == null ) viewClassName = ArtifactList_ListView.class.getName();
      return viewClassName;
   }
}
