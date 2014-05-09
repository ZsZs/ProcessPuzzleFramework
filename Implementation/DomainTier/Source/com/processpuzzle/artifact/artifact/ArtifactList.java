/*
Name: 
    - ArtifactList

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

Copyright: (C) 2014 This program is free software: you can redistribute it and/or modify it under the terms of the 
GNU General Public License as published by the Free Software Foundation, either version 3 of the License, 
or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.processpuzzle.artifact.artifact;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.processpuzzle.application.security.domain.User;
import com.processpuzzle.artifact.domain.Artifact;
import com.processpuzzle.artifact.domain.ArtifactListView;
import com.processpuzzle.artifact.domain.PrintView;
import com.processpuzzle.artifact_type.domain.ArtifactType;

@XmlType( name = "ArtifactList", propOrder = {} )
public abstract class ArtifactList<L extends Artifact<L>> extends Artifact<L> {
   public static String PROPERTY_VIEW = "ArtifactList_PropertyView";
   public static String LIST_VIEW = "ArtifactList_ListView";
   public static String PRINT_VIEW = "ArtifactList_PrintView";
   protected @XmlTransient ArtifactListView<L> listView;
   protected @XmlTransient PrintView<?> printView;

   //Constructors
   public ArtifactList( String name, ArtifactType type, User creator ) {
      super( name, type, creator );
   }

   protected ArtifactList() {}

   //Public accessors and mutators
   @SuppressWarnings("unchecked")
   public ArtifactListView<L> getListView() {
      for( Iterator<String> iter = availableViews.keySet().iterator(); iter.hasNext(); ){
         String viewName = (String) iter.next();
         if( availableViews.get( viewName ) instanceof ArtifactListView )
            return (ArtifactListView<L>) availableViews.get( viewName );
      }
      return null;
   }

   protected void overWriteDeaultListView( ArtifactListView<L> listView ) {
      if( this.listView != null )
         availableViews.remove( this.listView.getClass().getName() );
      this.listView = listView;
      availableViews.put( listView.getClass().getName(), listView );
   }

   protected void overWriteDeaultPrintView( PrintView<?> printView ) {
      if( this.printView != null )
         availableViews.remove( this.printView.getClass().getName() );
      this.printView = printView;
      availableViews.put( printView.getClass().getName(), printView );
   }

   @SuppressWarnings("unchecked")
   public String getAsXml() {
      Class<L> artifactListClass = (Class<L>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      return super.getAsXml( artifactListClass, this );
   }
}